package com.talelin.linboot.fileupload.handler.pre.impl;

import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;

/**
 * @author 桔子
 * @since 2021/1/26 13:42
 */
public class LinFileUploadFileTypePreHandler implements LinFileUploadPreHandler {

    private String[] extensions;

    public LinFileUploadFileTypePreHandler() {
    }

    public LinFileUploadFileTypePreHandler(String[] extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean handle(LinFileUploadInput linFileUploadInput) {
        return false;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }
}
