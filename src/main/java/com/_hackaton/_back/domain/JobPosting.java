package com._hackaton._back.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
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

    @ElementCollection //기본 데이터 타입(String,Integer)의 리스트 일 때 붙여줘야 함
    private List<String> profileImage; //기업이미지
    private String sector; //
    private String region;

    @ElementCollection
    private List<String> companyImages;
    private String detailAddress;
    private String employmentPeriod;
    private String workDays;
    private String workHours;

    @ElementCollection
    private List<String> details;
    private int salary;
    private boolean provideAccommodation;


}
