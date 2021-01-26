package com.talelin.linboot.fileupload.handler.pre.impl;

import com.talelin.linboot.fileupload.domain.LinFileUploadInput;
import com.talelin.linboot.fileupload.handler.pre.LinFileUploadPreHandler;
import com.talelin.linboot.fileupload.util.LinDateUtil;

/**
 * 将日期设置为存储路径
 *
 * @author 桔子
 * @since 2020/12/27 19:13
 */
public class LinFileUploadDatePathPreHandler implements LinFileUploadPreHandler {

    @Override
    public boolean handle(LinFileUploadInput linFileUploadInput) {
        String savePath = "/" + LinDateUtil.getCurrentYear() + "/" + LinDateUtil.getCurrentMonthFull() + "/" + LinDateUtil.getCurrentDayFull() + "/";
        linFileUploadInput.setSavePath(savePath);
        return true;
    }
}
