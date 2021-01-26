package com.talelin.linboot.autoconfigure.fileupload.core;

import com.talelin.linboot.fileupload.handler.post.LinFileUploadPostHandler;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 桔子
 * @since 2021/1/23 18:34
 */
public class AbstractFileUploadAutoConfiguration {

    /**
     * 文件上传配置
     */
    private final FileUploadProperties properties;

    public AbstractFileUploadAutoConfiguration(FileUploadProperties properties) {
        this.properties = properties;
    }

    /**
     * 获取前置处理器
     *
     * @return LinFileUploadPreHandler
     */
    protected List<LinFileUploadPreHandler> getPreHandlers() {

        if (this.properties == null || this.properties.getPreHandlers() == null) {
            return new ArrayList<>();
        }

        return this.properties.getPreHandlers().stream().map(preHandler -> {
            try {
                return preHandler.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 获取后置处理器
     *
     * @return LinFileUploadPostHandler
     */
    protected List<LinFileUploadPostHandler> getPostHandlers() {

        if (this.properties == null || this.properties.getPostHandlers() == null) {
            return new ArrayList<>();
        }

        return this.properties.getPostHandlers().stream().map(preHandler -> {
            try {
                return preHandler.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

}
