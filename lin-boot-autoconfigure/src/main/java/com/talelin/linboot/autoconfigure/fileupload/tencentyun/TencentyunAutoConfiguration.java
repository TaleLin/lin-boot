package com.talelin.linboot.autoconfigure.fileupload.tencentyun;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadTencentyunService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 桔子
 * @since 2020/12/16 23:00
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TencentyunProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.tencentyun", name = "enabled", havingValue = "true")
public class TencentyunAutoConfiguration {

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

        return new LinFileUploadTencentyunService(domain, bucket, cosClient);
    }

}
