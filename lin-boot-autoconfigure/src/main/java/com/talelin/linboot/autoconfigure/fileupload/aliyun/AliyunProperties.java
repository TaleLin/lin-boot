package com.talelin.linboot.autoconfigure.fileupload.aliyun;

import com.talelin.linboot.autoconfigure.fileupload.core.FileUploadProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云对象存储上传配置
 *
 * @author 桔子
 * @since 2020/12/16 21:56
 */
@ConfigurationProperties("linboot.fileupload.aliyun")
public class AliyunProperties extends FileUploadProperties {

    /**
     * 地区
     */
    private String endpoint;

    /**
     * accessKey
     */
    private String accessKeyId;

    /**
     * secret
     */
    private String accessKeySecret;

    /**
     * 存储空间
     */
    private String bucketName;

    /**
     * 域名
     */
    private String domain;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
