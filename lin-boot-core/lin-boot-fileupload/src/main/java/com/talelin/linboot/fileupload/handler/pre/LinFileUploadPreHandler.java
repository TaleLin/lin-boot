package com.talelin.linboot.fileupload.handler.pre;

import com.talelin.linboot.fileupload.domain.LinFileUploadInput;

/**
 * 文件上传前置处理器
 *
 * @author 桔子
 * @since 2020/12/12 23:10
 */
public interface LinFileUploadPreHandler {

    /**
     * 处理逻辑
     *
     * @param linFileUploadInput 文件数据
     * @return 整个处理器链是否继续向下执行
     */
    boolean handle(LinFileUploadInput linFileUploadInput);

}
