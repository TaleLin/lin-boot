package com.talelin.linboot.autoconfigure.fileupload.tencentyun;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 桔子
 * @since 2020/12/16 22:58
 */
@ConfigurationProperties("linboot.fileupload.qiniu")
public class TencentyunProperties {

    /**
     * 域名
     */
    private String domain;

    /**
     * secretId
     */
    private String secretId;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 区域
     */
    private String region;

    /**
     * 存储空间
     */
    private String bucket;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
