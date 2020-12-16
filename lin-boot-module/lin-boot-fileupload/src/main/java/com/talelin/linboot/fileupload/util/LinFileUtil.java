package com.talelin.linboot.fileupload.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件上传时使用的工具类
 *
 * @author 桔子
 * @since 2020/12/13 01:45
 */
public class LinFileUtil {

    /**
     * 获取文件名中的扩展名
     * talelin.txt ==> txt
     *
     * @param filename 文件名
     * @return 不带点的扩展名
     */
    public static String getExtByFilename(String filename) {
        String[] split = filename.split("\\.");
        return split[split.length - 1];
    }

    /**
     * 获取文件名中的扩展名
     * talelin.txt ==> .txt
     *
     * @param filename 文件名
     * @return 带点的扩展名
     */
    public static String getExtByFilenameWithDot(String filename) {
        return "," + getExtByFilename(filename);
    }

    public static void writeFile(String filename, byte[] fileBytes) throws IOException {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename))) {
            outputStream.write(fileBytes);
        }
    }
}
