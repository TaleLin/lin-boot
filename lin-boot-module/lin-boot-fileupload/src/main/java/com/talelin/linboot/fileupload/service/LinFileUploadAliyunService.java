package com.talelin.linboot.fileupload.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.exception.LinFileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

/**
 * @author 桔子
 * @since 2020/12/16 22:01
 */
public class LinFileUploadAliyunService extends LinFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(LinFileUploadAliyunService.class);

    /**
     * 域名
     */
    private final String domain;

    /**
     * 阿里云上传客户端
     */
    private final OSS ossClient;

    private final String bucketName;

    public LinFileUploadAliyunService(String domain, OSS ossClient, String bucketName) {
        this.domain = domain;
        this.ossClient = ossClient;
        this.bucketName = bucketName;
    }

    @Override
    public LinFileUploadOutput execute(LinFileUploadInput file) {
        String filename = file.getFilename();
        byte[] fileBytes = file.getFileBytes();
        String fullFileName = file.getSavePath() + "/" + filename;

        PutObjectResult result;
        try {
            result = this.ossClient.putObject(this.bucketName, fullFileName, new ByteArrayInputStream(fileBytes));
        } catch (OSSException | ClientException e) {
            log.error("LinFileUpload === 阿里云对象存储上传失败：{}", e.getMessage(), e);
            throw new LinFileUploadException("阿里云对象存储上传失败", e);
        }

        String url = domain + fullFileName;
        return new LinFileUploadOutput(filename, url, result);
    }
}
