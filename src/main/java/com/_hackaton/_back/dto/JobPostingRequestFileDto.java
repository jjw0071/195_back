package com._hackaton._back.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class JobPostingRequestFileDto {
    private List<MultipartFile> profileImage;
    private List<MultipartFile> companyImages;
    private List<MultipartFile> details;
}
