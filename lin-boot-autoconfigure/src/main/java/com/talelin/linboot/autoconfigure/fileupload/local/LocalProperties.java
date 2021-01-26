package com.talelin.linboot.autoconfigure.fileupload.local;

import com.talelin.linboot.autoconfigure.fileupload.core.FileUploadProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本地文件上传配置
 *
 * @author 桔子
 * @since 2020/12/13 03:00
 */
@ConfigurationProperties("linboot.fileupload.local")
public class LocalProperties extends FileUploadProperties {

    /**
     * 文件存储相对路径
     * 起点是你的项目路径，以斜杠开头和结尾
     * 例如：/asserts/file/
     */
    private String relativePath = "/asserts/";

    /**
     * 访问域名
     */
    private String domain;

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
