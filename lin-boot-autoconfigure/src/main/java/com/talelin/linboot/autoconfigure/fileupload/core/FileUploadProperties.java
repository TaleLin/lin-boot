package com.talelin.linboot.autoconfigure.fileupload.core;

import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;

import java.util.List;

/**
 * @author 桔子
 * @since 2021/1/23 18:36
 */
public class FileUploadProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = false;

    /**
     * 文件上传前置处理器
     */
    private List<Class<LinFileUploadPreHandler>> preHandlers;

    /**
     * 文件上传后置处理器
     */
    private List<Class<LinFileUploadPostHandler>> postHandlers;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
