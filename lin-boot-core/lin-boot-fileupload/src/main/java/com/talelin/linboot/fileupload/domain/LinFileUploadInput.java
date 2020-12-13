package com.talelin.linboot.fileupload.domain;

/**
 * @author 桔子
 * @since 2020/12/12 23:19
 */
public class LinFileUploadInput {

    /**
     * 包含扩展名的文件名称
     * 例如：talelin.txt
     */
    private String filename;

    /**
     * 文件的存储路径，不是所有对象存储都支持
     * 不支持的将会被忽略（如七牛云就不支持）
     * 格式：以 / 开头，以 / 结尾
     * 例如：/asserts/file/
     */
    private String savePath;

    /**
     * 文件字节数组
     */
    private byte[] fileBytes;

    /**
     * 自定义数据，按需设置
     */
    private Object payload;

    public LinFileUploadInput(String filename, byte[] fileBytes) {
        this.filename = filename;
        this.fileBytes = fileBytes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
