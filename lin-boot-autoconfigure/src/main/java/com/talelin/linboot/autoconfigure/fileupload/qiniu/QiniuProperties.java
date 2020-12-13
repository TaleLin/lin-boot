package com.talelin.linboot.autoconfigure.fileupload.qiniu;

import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 桔子
 * @since 2020/12/12 21:24
 */
@ConfigurationProperties("linboot.fileupload.qiniu")
public class QiniuProperties {

    /**
     * 七牛云对象存储绑定的域名
     */
    private String domain;

    /**
     * access key
     */
    private String accessKey;

    /**
     * secret key
     */
    private String secretKey;

    /**
     * bucket
     */
    private String bucket;

    /**
     * bucket 区域
     */
    private QiniuRegionEnum region;

    private List<Class<LinFileUploadPreHandler>> preHandlers;

    private List<Class<LinFileUploadPostHandler>> postHandlers;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public QiniuRegionEnum getRegion() {
        return region;
    }

    public void setRegion(QiniuRegionEnum region) {
        this.region = region;
    }

    public List<Class<LinFileUploadPreHandler>> getPreHandlers() {
        return preHandlers;
    }

    public void setPreHandlers(List<Class<LinFileUploadPreHandler>> preHandlers) {
        this.preHandlers = preHandlers;
    }

    public List<Class<LinFileUploadPostHandler>> getPostHandlers() {
        return postHandlers;
    }

    public void setPostHandlers(List<Class<LinFileUploadPostHandler>> postHandlers) {
        this.postHandlers = postHandlers;
    }
}
