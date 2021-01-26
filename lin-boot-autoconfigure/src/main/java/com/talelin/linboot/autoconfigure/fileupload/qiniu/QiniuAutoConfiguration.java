package com.talelin.linboot.autoconfigure.fileupload.qiniu;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.talelin.linboot.autoconfigure.fileupload.core.AbstractFileUploadAutoConfiguration;
import com.talelin.linboot.autoconfigure.fileupload.core.LinFileUploadAutoConfigurationPostProcessor;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.service.LinFileUploadQiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云对象存储文件上传自动配置类
 *
 * @author 桔子
 * @since 2020/12/12 21:23
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(QiniuProperties.class)
@ConditionalOnProperty(prefix = "linboot.fileupload.qiniu", name = "enabled", havingValue = "true")
public class QiniuAutoConfiguration extends AbstractFileUploadAutoConfiguration {

    @Autowired(required = false)
    private LinFileUploadAutoConfigurationPostProcessor postProcessor;

    public QiniuAutoConfiguration(QiniuProperties properties) {
        super(properties);
    }

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

        LinFileUploadQiniuService fileUploadQiniuService = new LinFileUploadQiniuService(domain, auth, uploadManager, bucket);
        fileUploadQiniuService.setPreHandlers(super.getPreHandlers());
        fileUploadQiniuService.setPostHandlers(super.getPostHandlers());

        if (this.postProcessor != null) {
            this.postProcessor.process(fileUploadQiniuService, properties);
        }

        return fileUploadQiniuService;
    }
}
