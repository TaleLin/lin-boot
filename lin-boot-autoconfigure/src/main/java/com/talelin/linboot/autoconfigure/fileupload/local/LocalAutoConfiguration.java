package com.talelin.linboot.autoconfigure.fileupload.local;

import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadLocalService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 桔子
 * @since 2020/12/13 16:11
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LocalProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.local", name = "enabled", havingValue = "true")
public class LocalAutoConfiguration {

    @Bean
    public LinFileUploadService linFileUploadLocalService(LocalProperties properties) {
        String domain = properties.getDomain();
        String relativePath = properties.getRelativePath();

        return new LinFileUploadLocalService(relativePath, domain);

    }

}
