package com.talelin.linboot.autoconfigure.fileupload.local;

import com.talelin.linboot.autoconfigure.fileupload.core.AbstractFileUploadAutoConfiguration;
import com.talelin.linboot.autoconfigure.fileupload.core.LinFileUploadAutoConfigurationPostProcessor;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import com.talelin.linboot.fileupload.service.LinFileUploadLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 本地文件上传自动配置类
 *
 * @author 桔子
 * @since 2020/12/13 16:11
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LocalProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.local", name = "enabled", havingValue = "true")
public class LocalAutoConfiguration extends AbstractFileUploadAutoConfiguration {

    public LocalAutoConfiguration(LocalProperties properties) {
        super(properties);
    }

    @Autowired(required = false)
    private LinFileUploadAutoConfigurationPostProcessor postProcessor;

    @Bean
    @Primary
    public LinFileUploadService linFileUploadLocalService(LocalProperties properties) {
        String domain = properties.getDomain();
        String relativePath = properties.getRelativePath();

        LinFileUploadLocalService fileUploadService = new LinFileUploadLocalService(relativePath, domain);

        // 配置前置处理器
        List<LinFileUploadPreHandler> preHandlers = super.getPreHandlers();
        if (!CollectionUtils.isEmpty(preHandlers)) {
            fileUploadService.setPreHandlers(preHandlers);
        }

        // 配置后置处理器
        List<LinFileUploadPostHandler> postHandlers = super.getPostHandlers();
        if (!CollectionUtils.isEmpty(postHandlers)) {
            fileUploadService.setPostHandlers(postHandlers);
        }

        if (this.postProcessor != null) {
            this.postProcessor.process(fileUploadService, properties);
        }

        return fileUploadService;

    }

}
