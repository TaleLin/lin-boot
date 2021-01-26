package com.talelin.linboot.autoconfigure.fileupload.core;

import com.talelin.linboot.fileupload.api.LinFileUploadService;

/**
 * 自动配置后置处理器
 * 请勿注入多个该实例到 Spring 容器中，否则会报错
 *
 * @author 桔子
 * @since 2021/1/26 21:49
 */
public interface LinFileUploadAutoConfigurationPostProcessor {

    /**
     * 构造文件上传服务实例成功后的回调函数
     *
     * @param linFileUploadService 文件上传服务示例
     * @param fileUploadProperties 配置信息
     */
    void process(LinFileUploadService linFileUploadService, FileUploadProperties fileUploadProperties);
}
