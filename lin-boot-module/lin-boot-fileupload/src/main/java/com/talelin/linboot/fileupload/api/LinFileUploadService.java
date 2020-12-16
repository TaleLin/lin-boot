package com.talelin.linboot.fileupload.api;

import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;

import java.util.List;

/**
 * 文件上传
 *
 * @author 桔子
 * @since 2020/12/10 13:42
 */
public abstract class LinFileUploadService {

    private List<LinFileUploadPreHandler> preHandlers;

    private List<LinFileUploadPostHandler> postHandlers;

    public LinFileUploadOutput upload(LinFileUploadInput linFile) {
        this.preHandlers.stream().anyMatch(preHandler -> !preHandler.handle(linFile));
        LinFileUploadOutput result = this.execute(linFile);
        this.postHandlers.stream().anyMatch(preHandler -> !preHandler.handle(result));

        return result;
    }

    /**
     * 不同对象存储的文件上传过程
     *
     * @param file 文件数据
     * @return 文件上传结果
     */
    public abstract LinFileUploadOutput execute(LinFileUploadInput file);

    public List<LinFileUploadPreHandler> getPreHandlers() {
        return preHandlers;
    }

    public void setPreHandlers(List<LinFileUploadPreHandler> preHandlers) {
        this.preHandlers = preHandlers;
    }

    public List<LinFileUploadPostHandler> getPostHandlers() {
        return postHandlers;
    }

    public void setPostHandlers(List<LinFileUploadPostHandler> postHandlers) {
        this.postHandlers = postHandlers;
    }
}
