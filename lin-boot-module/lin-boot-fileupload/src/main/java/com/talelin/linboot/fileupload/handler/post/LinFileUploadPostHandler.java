package com.talelin.linboot.fileupload.handler.post;

import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;

/**
 * 文件上传后置处理器
 *
 * @author 桔子
 * @since 2020/12/12 23:21
 */
public interface LinFileUploadPostHandler {
    /**
     * 处理逻辑
     *
     * @param linFileUploadOutput 文件数据
     * @return 整个处理器链是否继续向下执行
     */
    boolean handle(LinFileUploadOutput linFileUploadOutput);
}
