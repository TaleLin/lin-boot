package com.talelin.linboot.fileupload.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectResult;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.exception.LinFileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

/**
 * @author 桔子
 * @since 2020/12/16 22:45
 */
public class LinFileUploadTencentyunService extends LinFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(LinFileUploadTencentyunService.class);

    private String domain;

    private String bucket;

    private COSClient cosClient;

    public LinFileUploadTencentyunService(String domain, String bucket, COSClient cosClient) {
        this.domain = domain;
        this.bucket = bucket;
        this.cosClient = cosClient;
    }

    @Override
    public LinFileUploadOutput execute(LinFileUploadInput file) {
        String filename = file.getFilename();
        byte[] fileBytes = file.getFileBytes();
        String fullFilePath = file.getSavePath() + "/" + filename;

        PutObjectResult result;
        try {
            result = cosClient.putObject(bucket, fullFilePath, new ByteArrayInputStream(fileBytes), null);
        } catch (CosClientException e) {
            log.error("LinFileUpload === 腾讯云对象存储上传失败：{}", e.getMessage(), e);
            throw new LinFileUploadException("腾讯云对象存储上传失败", e);
        }

        String url = domain + fullFilePath;
        return new LinFileUploadOutput(filename, url, result);
    }
}
