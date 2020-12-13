package com.talelin.linboot.fileupload.domain;

/**
 * @author 桔子
 * @since 2020/12/12 23:20
 */
public class LinFileUploadOutput {

    /**
     * 文件名
     */
    private String filename;

    /**
     * 访问网址
     */
    private String url;

    /**
     * 不同对象存储的返回信息
     */
    private Object payload;

    public LinFileUploadOutput(String filename, String url, Object payload) {
        this.filename = filename;
        this.url = url;
        this.payload = payload;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
