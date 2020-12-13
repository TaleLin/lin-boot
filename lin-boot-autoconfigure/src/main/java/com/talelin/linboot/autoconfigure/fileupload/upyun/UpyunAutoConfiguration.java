package com.talelin.linboot.autoconfigure.fileupload.upyun;

import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import com.talelin.linboot.fileupload.service.LinFileUploadUpyunService;
import com.upyun.RestManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 桔子
 * @since 2020/12/13 02:12
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(UpyunProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.upyun", name = "enabled", havingValue = "true")
public class UpyunAutoConfiguration {

    @Bean
    public LinFileUploadService linFileUploadUpyunService(UpyunProperties properties) {
        String bucket = properties.getBucket();
        String username = properties.getUsername();
        String password = properties.getPassword();

        RestManager restManager = new RestManager(bucket, username, password);

        List<LinFileUploadPreHandler> preHandlers = properties.getPreHandlers().stream().map(preHandler -> {
            try {
                return preHandler.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        List<LinFileUploadPostHandler> postHandlers = properties.getPostHandlers().stream().map(postHandler -> {
            try {
                return postHandler.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        LinFileUploadUpyunService linFileUploadUpyunService = new LinFileUploadUpyunService(restManager, properties.getDomain());
        linFileUploadUpyunService.setPreHandlers(preHandlers);
        linFileUploadUpyunService.setPostHandlers(postHandlers);
        return linFileUploadUpyunService;
    }

}
