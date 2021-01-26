package com.talelin.linboot.autoconfigure.fileupload.upyun;

import com.talelin.linboot.autoconfigure.fileupload.core.FileUploadProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 又拍云对象存储文件上传配置
 *
 * @author 桔子
 * @since 2020/12/13 02:07
 */
@ConfigurationProperties("linboot.fileupload.upyun")
public class UpyunProperties extends FileUploadProperties {

    /**
     * 云存储空间名称
     */
    private String bucket;

    /**
     * 操作员账号
     */
    private String username;

    /**
     * 操作员密码
     */
    private String password;

    /**
     * 又拍云云存储绑定的域名
     */
    private String domain;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
