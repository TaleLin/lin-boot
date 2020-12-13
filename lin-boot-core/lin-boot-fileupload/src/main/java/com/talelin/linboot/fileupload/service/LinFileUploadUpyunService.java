package com.talelin.linboot.fileupload.service;

import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.exception.LinFileUploadException;
import com.upyun.RestManager;
import com.upyun.UpException;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author 桔子
 * @since 2020/12/13 02:15
 */
public class LinFileUploadUpyunService extends LinFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(LinFileUploadUpyunService.class);

    private final RestManager restManager;

    private final String domain;

    public LinFileUploadUpyunService(RestManager restManager, String domain) {
        this.restManager = restManager;
        this.domain = domain;
    }

    @Override
    public LinFileUploadOutput execute(LinFileUploadInput file) {
        // 拼接完成存储路径
        String savePath = file.getSavePath();
        savePath = savePath.endsWith("/") ? savePath : savePath + "/";
        String fullName = savePath + file.getFilename();

        Response response;
        try {
            response = this.restManager.writeFile(fullName, file.getFileBytes(), null);
        } catch (IOException | UpException e) {
            log.error("LinFileUpload === 又拍云对象存储上传失败：{}", e.getMessage(), e);
            throw new LinFileUploadException("又拍云对象存储上传失败", e);
        }
        String url = this.domain + "/" + fullName;
        return new LinFileUploadOutput(file.getFilename(), url, response);
    }
}
