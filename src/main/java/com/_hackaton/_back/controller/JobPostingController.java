package com._hackaton._back.controller;

import com._hackaton._back.domain.JobPosting;
import com._hackaton._back.dto.JobPostingRequestBasicInfoDto;
import com._hackaton._back.dto.JobPostingRequestFileDto;
import com._hackaton._back.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    /**
     * 새로운 구직 공고를 생성합니다.
     *
     * @param basicInfoDto 구직 공고 생성에 필요한 기본 정보를 담고 있는 DTO
     * @param fileDto 구직 공고와 관련된 파일 정보들을 포함한 DTO
     * @return 생성된 구직 공고의 정보와 함께 상태 코드 200을 반환. 실패 시 에러 메시지와 함께 다른 상태 코드를 반환.
     * @throws IOException 구직 공고와 관련된 파일들을 처리하면서 발생할 수 있는 입출력 예외
     */
    @PostMapping(value = "/job-postings", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JobPosting> createJobPosting(
            @ModelAttribute JobPostingRequestBasicInfoDto basicInfoDto,
            @ModelAttribute JobPostingRequestFileDto fileDto) throws IOException {

        JobPosting jobPosting = jobPostingService.createJobPosting(basicInfoDto, fileDto);

        return ResponseEntity.ok(jobPosting);
    }
}
