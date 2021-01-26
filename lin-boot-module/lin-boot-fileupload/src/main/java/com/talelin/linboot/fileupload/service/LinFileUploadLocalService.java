package com.talelin.linboot.fileupload.service;

import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.exception.LinFileUploadException;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import com.talelin.linboot.fileupload.handler.pre.impl.LinFileUploadDatePathPreHandler;
import com.talelin.linboot.fileupload.handler.pre.impl.LinFileUploadMD5RenamePreHandler;
import com.talelin.linboot.fileupload.util.LinFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地文件上传
 *
 * @author 桔子
 * @since 2020/12/13 02:45
 */
public class LinFileUploadLocalService extends LinFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(LinFileUploadLocalService.class);

    private final String parentPath;

    private final String domain;

    public LinFileUploadLocalService(String parentPath, String domain) {
        this.parentPath = parentPath == null ? "" : parentPath;
        this.domain = domain == null ? "" : domain;

        // 配置默认的前置处理器
        List<LinFileUploadPreHandler> preHandlers = new ArrayList<>();
        preHandlers.add(new LinFileUploadDatePathPreHandler());
        preHandlers.add(new LinFileUploadMD5RenamePreHandler());
        super.setPreHandlers(preHandlers);
    }

    @Override
    public LinFileUploadOutput execute(LinFileUploadInput file) {
        String filePath = file.getSavePath();
        String filename = file.getFilename();
        byte[] fileBytes = file.getFileBytes();

        filePath = filePath == null ? "/" : filePath;
        String savePath = System.getProperty("user.dir") + this.parentPath + filePath;

        try {
            LinFileUtil.writeFile(savePath, filename, fileBytes);
        } catch (IOException e) {
            log.error("LinFileUpload === 本地存储上传失败：{}", e.getMessage(), e);
            throw new LinFileUploadException("本地存储上传失败", e);
        }

        String url = this.domain + this.parentPath + filePath + filename;
        return new LinFileUploadOutput(filename, url, null);
    }
}
