package com.talelin.linboot.fileupload.handler.pre.impl;

import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import com.talelin.linboot.fileupload.util.LinFileUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 将文件重命名为文件的 MD5 值
 *
 * @author 桔子
 * @since 2020/12/13 00:56
 */
public class LinFileUploadMD5RenamePreHandler implements LinFileUploadPreHandler {

    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean handle(LinFileUploadInput linFileUploadInput) {
        messageDigest.update(linFileUploadInput.getFileBytes());
        byte[] digestResult = messageDigest.digest();

        StringBuilder md5Filename = new StringBuilder();
        for (byte b : digestResult) {
            md5Filename.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
        }

        String extName = LinFileUtil.getExtByFilenameWithDot(linFileUploadInput.getFilename());
        md5Filename.append(extName);
        linFileUploadInput.setFilename(md5Filename.toString());
        return true;
    }
}
