package com.talelin.linboot.fileupload.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.talelin.linboot.fileupload.api.LinFileUploadService;
import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.domain.LinFileUploadOutput;
import com.talelin.linboot.fileupload.exception.LinFileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 七牛云 Kodo 对象存储
 *
 * @author 桔子
 * @since 2020/12/11 08:05
 */
public class LinFileUploadQiniuService extends LinFileUploadService {

    private static final Logger log = LoggerFactory.getLogger(LinFileUploadQiniuService.class);

    /**
     * 七牛云对象存储域名
     */
    private final String domain;

    /**
     * 七牛云上传管理器
     */
    private final UploadManager uploadManager;

    /**
     * 上传 Token
     */
    private final String uploadToken;

    public LinFileUploadQiniuService(String domain, Auth auth, UploadManager uploadManager, String bucket) {
        this.domain = domain;
        this.uploadManager = uploadManager;
        // 因为该 token 仅在后端使用，不会透出到前端，不存在安全问题，所以将过期时间设置到了2038年
        this.uploadToken = auth.uploadTokenWithDeadline(bucket, null, Integer.MAX_VALUE, null, true);
    }

    @Override
    public LinFileUploadOutput execute(LinFileUploadInput linFile) {
        String fileName = linFile.getFilename();
        byte[] fileBytes = linFile.getFileBytes();

        Response response = null;
        try {
            response = uploadManager.put(fileBytes, fileName, this.uploadToken);
        } catch (QiniuException e) {
            log.error("LinFileUpload === 七牛云对象存储上传失败：{}", e.getMessage(), e);
            throw new LinFileUploadException("七牛云对象存储上传失败", e);
        }

        String url = domain + "/" + linFile.getFilename();
        return new LinFileUploadOutput(fileName, url, response);
    }
}
