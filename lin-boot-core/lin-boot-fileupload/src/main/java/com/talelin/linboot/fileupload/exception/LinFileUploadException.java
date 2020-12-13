package com.talelin.linboot.fileupload.exception;

/**
 * @author 桔子
 * @since 2020/12/13 00:39
 */
public class LinFileUploadException extends RuntimeException {

    public LinFileUploadException() {
    }

    public LinFileUploadException(String message) {
        super(message);
    }

    public LinFileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinFileUploadException(Throwable cause) {
        super(cause);
    }
}
