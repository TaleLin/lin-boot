package com.talelin.linboot.autoconfigure.fileupload.local;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 桔子
 * @since 2020/12/13 03:00
 */
@ConfigurationProperties("linboot.fileupload.local")
public class LocalProperties {

    /**
     * 文件存储相对路径，该配置优先级低于 LinFileUploadInput 中设置的路径
     * 起点是你的项目路径，以斜杠开头和结尾
     * 例如：/asserts/file/
     */
    private String relativePath;

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
