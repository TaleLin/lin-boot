package com.talelin.linboot.autoconfigure.fileupload.tencentyun;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.talelin.linboot.autoconfigure.fileupload.core.AbstractFileUploadAutoConfiguration;
import com.talelin.linboot.autoconfigure.fileupload.core.LinFileUploadAutoConfigurationPostProcessor;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadTencentyunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云对象存储文件上传自动配置类
 *
 * @author 桔子
 * @since 2020/12/16 23:00
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TencentyunProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.tencentyun", name = "enabled", havingValue = "true")
public class TencentyunAutoConfiguration extends AbstractFileUploadAutoConfiguration {

    @Autowired(required = false)
    private LinFileUploadAutoConfigurationPostProcessor postProcessor;

    public TencentyunAutoConfiguration(TencentyunProperties properties) {
        super(properties);
    }

    @Bean
    public LinFileUploadService linFileUploadTencentyunService(TencentyunProperties properties) {
        String domain = properties.getDomain();
        String bucket = properties.getBucket();
        String secretId = properties.getSecretId();
        String secretKey = properties.getSecretKey();
        String regionString = properties.getRegion();

        COSCredentials cosCredentials = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(regionString);
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cosCredentials, clientConfig);

        LinFileUploadTencentyunService fileUploadService = new LinFileUploadTencentyunService(domain, bucket, cosClient);
        fileUploadService.setPreHandlers(super.getPreHandlers());
        fileUploadService.setPostHandlers(super.getPostHandlers());

        if (this.postProcessor != null) {
            this.postProcessor.process(fileUploadService, properties);
        }

        return fileUploadService;
    }

}
