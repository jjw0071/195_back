package com._hackaton._back.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Posting {

    @Id
    @GeneratedValue
    private Long id;
    private String company_name; //회사이름
    private String phone_number; //전화번호
    private String profile_image; //기업이미지
    private String sector; //
    private String region;
    private String company_images;
    private String detail_address;
    private String employment_period;
    private String workdays;
    private String workhour;
    private String details;
    private int salary;
    private boolean provide_accommodation;


}
