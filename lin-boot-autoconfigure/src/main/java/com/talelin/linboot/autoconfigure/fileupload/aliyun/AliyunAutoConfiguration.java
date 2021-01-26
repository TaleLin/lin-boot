package com.talelin.linboot.autoconfigure.fileupload.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.talelin.linboot.autoconfigure.fileupload.core.AbstractFileUploadAutoConfiguration;
import com.talelin.linboot.autoconfigure.fileupload.core.LinFileUploadAutoConfigurationPostProcessor;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadAliyunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云对象存储上传自动配置类
 *
 * @author 桔子
 * @since 2020/12/16 21:58
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan("com.talelin.linboot")
@EnableConfigurationProperties(AliyunProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.aliyun", name = "enabled", havingValue = "true")
public class AliyunAutoConfiguration extends AbstractFileUploadAutoConfiguration {

    @Autowired(required = false)
    private LinFileUploadAutoConfigurationPostProcessor postProcessor;

    public AliyunAutoConfiguration(AliyunProperties properties) {
        super(properties);
    }

    @Bean
    public LinFileUploadService linFileUploadAliyunService(AliyunProperties properties) {
        String domain = properties.getDomain();
        String endpoint = properties.getEndpoint();
        String bucketName = properties.getBucketName();
        String accessKeyId = properties.getAccessKeyId();
        String accessKeySecret = properties.getAccessKeySecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        LinFileUploadAliyunService fileUploadService = new LinFileUploadAliyunService(domain, ossClient, bucketName);
        fileUploadService.setPreHandlers(super.getPreHandlers());
        fileUploadService.setPostHandlers(super.getPostHandlers());

        if (this.postProcessor != null) {
            this.postProcessor.process(fileUploadService, properties);
        }

        return fileUploadService;
    }
}
