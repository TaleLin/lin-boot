package com.talelin.linboot.autoconfigure.fileupload.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadAliyunService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 桔子
 * @since 2020/12/16 21:58
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AliyunProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.aliyun", name = "enabled", havingValue = "true")
public class AliyunAutoConfiguration {

    @Bean
    public LinFileUploadService linFileUploadAliyunService(AliyunProperties properties) {
        String domain = properties.getDomain();
        String endpoint = properties.getEndpoint();
        String bucketName = properties.getBucketName();
        String accessKeyId = properties.getAccessKeyId();
        String accessKeySecret = properties.getAccessKeySecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        return new LinFileUploadAliyunService(domain, ossClient, bucketName);
    }
}
