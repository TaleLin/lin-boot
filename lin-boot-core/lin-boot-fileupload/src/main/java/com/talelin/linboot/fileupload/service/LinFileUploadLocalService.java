package com.talelin.linboot.fileupload.service;

import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.exception.LinFileUploadException;
import com.talelin.linboot.fileupload.util.LinFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author 桔子
 * @since 2020/12/13 02:45
 */
public class LinFileUploadLocalService extends LinFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(LinFileUploadLocalService.class);

    private final String path;

    private final String domain;

    public LinFileUploadLocalService(String path, String domain) {
        this.path = path;
        this.domain = domain;
    }

    @Override
    public LinFileUploadOutput execute(LinFileUploadInput file) {
        String savePath = file.getSavePath();
        String filename = file.getFilename();
        byte[] fileBytes = file.getFileBytes();

        savePath = savePath == null ? path : savePath;
        String fullFilename = System.getProperty("user.dir") + "/" + savePath + "/" + filename;

        try {
            LinFileUtil.writeFile(fullFilename, fileBytes);
        } catch (IOException e) {
            log.error("LinFileUpload === 本地存储上传失败：{}", e.getMessage(), e);
            throw new LinFileUploadException("本地存储上传失败", e);
        }

        String url = domain + "/" + fullFilename;
        return new LinFileUploadOutput(filename, url, null);
    }
}
