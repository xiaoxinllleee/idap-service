package org.cmms.config.oss;

import org.cmms.common.util.oss.OssBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssBootConfiguration {

    @Value("${common.oss.endpoint}")
    private String endpoint;
    @Value("${common.oss.accessKey}")
    private String accessKeyId;
    @Value("${common.oss.secretKey}")
    private String accessKeySecret;
    @Value("${common.oss.bucketName}")
    private String bucketName;
    @Value("${common.oss.staticDomain}")
    private String staticDomain;


    @Bean
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endpoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
    }
}