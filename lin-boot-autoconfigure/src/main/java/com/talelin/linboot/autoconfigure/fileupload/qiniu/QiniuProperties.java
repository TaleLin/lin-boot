package com.talelin.linboot.autoconfigure.fileupload.qiniu;

import com.talelin.linboot.autoconfigure.fileupload.core.FileUploadProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七牛云对象存储文件上传配置
 *
 * @author 桔子
 * @since 2020/12/12 21:24
 */
@ConfigurationProperties("linboot.fileupload.qiniu")
public class QiniuProperties extends FileUploadProperties {

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

}
