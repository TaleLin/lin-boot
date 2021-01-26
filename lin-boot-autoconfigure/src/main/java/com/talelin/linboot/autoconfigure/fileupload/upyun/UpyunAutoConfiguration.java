package com.talelin.linboot.autoconfigure.fileupload.upyun;

import com.talelin.linboot.autoconfigure.fileupload.core.AbstractFileUploadAutoConfiguration;
import com.talelin.linboot.autoconfigure.fileupload.core.LinFileUploadAutoConfigurationPostProcessor;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadUpyunService;
import com.upyun.RestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 又拍云对象存储文件上传自动配置类
 *
 * @author 桔子
 * @since 2020/12/13 02:12
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(UpyunProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.upyun", name = "enabled", havingValue = "true")
public class UpyunAutoConfiguration extends AbstractFileUploadAutoConfiguration {

    @Autowired(required = false)
    private LinFileUploadAutoConfigurationPostProcessor postProcessor;

    public UpyunAutoConfiguration(UpyunProperties properties) {
        super(properties);
    }

    @Bean
    public LinFileUploadService linFileUploadUpyunService(UpyunProperties properties) {
        String bucket = properties.getBucket();
        String username = properties.getUsername();
        String password = properties.getPassword();

        RestManager restManager = new RestManager(bucket, username, password);

        LinFileUploadUpyunService linFileUploadUpyunService = new LinFileUploadUpyunService(restManager, properties.getDomain());
        linFileUploadUpyunService.setPreHandlers(super.getPreHandlers());
        linFileUploadUpyunService.setPostHandlers(super.getPostHandlers());

        if (this.postProcessor != null) {
            this.postProcessor.process(linFileUploadUpyunService, properties);
        }

        return linFileUploadUpyunService;
    }

}
