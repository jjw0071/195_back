package com._hackaton._back.config.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.s3")
public class S3Properties {

    private String endPoint;
    private String regionName;
    private String accessKey;
    private String secretKey;

    // ... Getters and Setters
}
