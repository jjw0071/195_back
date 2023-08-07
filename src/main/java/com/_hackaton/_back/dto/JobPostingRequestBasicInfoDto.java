package com._hackaton._back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostingRequestBasicInfoDto {

    private String companyName;
    private String phoneNumber;
    private String sector;
    private String region;
    private String detailAddress;
    private String employmentPeriod;
    private String workDays;
    private String workHours;
    private int salary;
    private boolean provideAccommodation;
}
