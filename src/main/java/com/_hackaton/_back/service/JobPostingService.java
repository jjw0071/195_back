package com._hackaton._back.service;

import com._hackaton._back.domain.JobPosting;
import com._hackaton._back.dto.JobPostingRequestBasicInfoDto;
import com._hackaton._back.dto.JobPostingRequestFileDto;
import com._hackaton._back.exception.EmptyImageListException;
import com._hackaton._back.exception.ImageUploadException;
import com._hackaton._back.repository.JobPostingRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final AmazonS3 amazonS3;
    private final JobPostingRepository jobPostingRepository;


    @Value("${aws.s3.endPoint}")
    private String endPoint;

    @Value("${aws.s3.regionName}")
    private String regionName;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private String urlTemplate;

    @PostConstruct
    public void initUrlTemplate() {
        urlTemplate = endPoint + "/" + bucketName + "/";
    }


    public List<String> listImagesInFolder(String folderName) {
        // 폴더에 해당하는 Prefix로 객체 리스트를 가져옴
        ObjectListing objectListing = amazonS3.listObjects(bucketName, folderName);

        // 폴더 내의 모든 이미지 정보를 저장할 리스트
        List<String> imageUrls = new ArrayList<>();

        String imageUrlTemplate =  endPoint + "/" + bucketName + "/";

        // 객체 리스트에서 각 객체의 키(파일 경로)를 가져와 URL을 생성
        objectListing.getObjectSummaries().forEach(objectSummary -> {
            String imageUrl = imageUrlTemplate + objectSummary.getKey();
            imageUrls.add(imageUrl);
        });

        return imageUrls;
    }

    /**
     * 새로운 구직 공고를 데이터베이스에 저장합니다.
     *
     * @param requestDto 구직 공고 생성에 필요한 정보를 담고 있는 DTO
     * @return 저장된 구직 공고 엔터티
     */
    @Transactional
    public JobPosting createJobPosting(JobPostingRequestBasicInfoDto requestDto, JobPostingRequestFileDto fileDto){
        JobPosting jobPosting = new JobPosting();
        jobPosting.setCompanyName(requestDto.getCompanyName());
        jobPosting.setPhoneNumber(requestDto.getPhoneNumber());
        jobPosting.setSector(requestDto.getSector());
        jobPosting.setRegion(requestDto.getRegion());
        jobPosting.setDetailAddress(requestDto.getDetailAddress());
        jobPosting.setEmploymentPeriod(requestDto.getEmploymentPeriod());
        jobPosting.setWorkDays(requestDto.getWorkDays());
        jobPosting.setWorkHours(requestDto.getWorkHours());
        jobPosting.setSalary(requestDto.getSalary());
        jobPosting.setProvideAccommodation(requestDto.isProvideAccommodation());
        // to-do
        // ProfileImage, CompanyImages, Details 처리

        //데이터베이스에 들어가기 전에 공고 별로 각각의 유일한 폴더를 생성하기 위한 폴더명 생성
        String folderName = UUID.randomUUID().toString() + "/";
        jobPosting.setProfileImage(uploadImagesToCloud(fileDto.getProfileImage(), folderName + "ProfileImage/"));
        jobPosting.setCompanyImages(uploadImagesToCloud(fileDto.getCompanyImages(), folderName + "CompanyImages/"));
        jobPosting.setDetails(uploadImagesToCloud(fileDto.getDetails(), folderName + "Details/"));

        return jobPostingRepository.save(jobPosting);
    }

    /**
     * AWS S3에 이미지를 업로드하는 메서드입니다.
     *
     * @param images 업로드할 이미지 리스트
     * @param specificFolderName 각 이미지 유형별 저장될 폴더명
     * @return 업로드된 이미지의 URL 리스트
     * @throws EmptyImageListException 이미지 리스트가 비어있는 경우 발생합니다.
     */
    private List<String> uploadImagesToCloud(List<MultipartFile> images,String specificFolderName){
        List<String> imageUrls = new ArrayList<>();
        if(images.isEmpty()){
            throw new EmptyImageListException("입력된 이미지가 없습니다.");
        }

        for (MultipartFile image : images) {
            String imageUrl = uploadToCloud(image, specificFolderName);
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }

    /**
     * AWS S3를 이용해 네이버 클라우드 Object Storage에 개별 이미지를 업로드하는 메서드입니다.
     *
     * @param file 업로드할 이미지 파일
     * @param specificFolderName 이미지가 저장될 폴더명
     * @return 업로드된 이미지의 URL
     * @throws ImageUploadException 이미지 업로드 중 발생한 예외
     */
    private String uploadToCloud(MultipartFile file, String specificFolderName) throws ImageUploadException {
        try {
            String fileName = file.getOriginalFilename();
            String key = specificFolderName + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), metadata));

            return urlTemplate + key;
        } catch (IOException e) {
            throw new ImageUploadException("이미지 업로드 중 에러 발생: " + e.getMessage(), e);
        }
    }
}
