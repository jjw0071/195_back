package com._hackaton._back.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Entity
public class JobPosting {

    @Id
    @GeneratedValue
    private Long id;
    private String companyName; //회사이름
    private String phoneNumber; //전화번호
    private String profileImage; //기업이미지
    private String sector; //
    private String region;
    private List<String> companyImages;
    private String detailAddress;
    private String employmentPeriod;
    private String workDays;
    private String workHours;
    private String details;
    private int salary;
    private boolean provideAccommodation;


}
