package com.talelin.linboot.fileupload.util;

import com.talelin.linboot.fileupload.exception.LinFileUploadException;

import java.io.*;

/**
 * 文件工具
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
     * @throws LinFileUploadException 创建文件夹失败
     */
    public static String getExtByFilenameWithDot(String filename) {
        return "." + getExtByFilename(filename);
    }

    /**
     * 写入文件到本地硬盘
     *
     * @param path      文件路径
     * @param filename  文件名
     * @param fileBytes 文件字节码
     * @throws IOException 文件写入失败时
     */
    public static void writeFile(String path, String filename, byte[] fileBytes) throws IOException {
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            if (!pathFile.mkdirs()) {
                throw new LinFileUploadException("创建本地文件夹失败，请检查您是否有权限创建文件夹！");
            }
        }
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path + "/" + filename))) {
            outputStream.write(fileBytes);
        }
    }

    /**
     * 将文件路径中可能存在的多个 / 替换为一个 /
     *
     * @param path 文件路径
     * @return 规范化后的文件路径
     */
    public static String normalizePath(String path) {
        if (path == null || path.length() <= 1) {
            return path;
        }

        return path.replaceAll("/+", "/");
    }
}
