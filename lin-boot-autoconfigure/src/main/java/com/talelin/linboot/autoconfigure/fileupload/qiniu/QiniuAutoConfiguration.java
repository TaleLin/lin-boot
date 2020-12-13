package com.talelin.linboot.autoconfigure.fileupload.qiniu;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import com.talelin.linboot.fileupload.service.LinFileUploadQiniuService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 桔子
 * @since 2020/12/12 21:23
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(QiniuProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.qiniu", name = "enabled", havingValue = "true")
public class QiniuAutoConfiguration {

    @Bean
    public LinFileUploadService linFileUploadQiniuService(QiniuProperties properties) {
        String domain = properties.getDomain();
        String accessKey = properties.getAccessKey();
        String secretKey = properties.getSecretKey();
        String bucket = properties.getBucket();
        QiniuRegionEnum regionEnum = properties.getRegion();

        com.qiniu.storage.Configuration cfg;
        switch (regionEnum) {
            case HUADONG:
                cfg = new com.qiniu.storage.Configuration(Region.huadong());
                break;
            case HUABEI:
                cfg = new com.qiniu.storage.Configuration(Region.huabei());
                break;
            case HUANAN:
                cfg = new com.qiniu.storage.Configuration(Region.huanan());
                break;
            case BEIMEI:
                cfg = new com.qiniu.storage.Configuration(Region.beimei());
                break;
            case DONGNANYA:
                cfg = new com.qiniu.storage.Configuration(Region.xinjiapo());
                break;
            default:
                throw new RuntimeException("七牛云 Region 区域不存在");
        }

        Auth auth = Auth.create(accessKey, secretKey);
        UploadManager uploadManager = new UploadManager(cfg);

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

        LinFileUploadQiniuService fileUploadQiniuService = new LinFileUploadQiniuService(domain, auth, uploadManager, bucket);
        fileUploadQiniuService.setPreHandlers(preHandlers);
        fileUploadQiniuService.setPostHandlers(postHandlers);

        return fileUploadQiniuService;
    }
}
