package com.talelin.linboot.fileupload.util;

import java.time.LocalDate;

/**
 * 日期工具
 *
 * @author 桔子
 * @since 2021/1/23 21:10
 */
public class LinDateUtil {

    /**
     * 获取当前年份，例如 2021
     *
     * @return 当前年份
     */
    public static String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    /**
     * 获取当前月份，两位数，例如 01
     *
     * @return 当前月份
     */
    public static String getCurrentMonthFull() {
        String month = String.valueOf(LocalDate.now().getMonth().getValue());
        return month.length() == 1 ? "0" + month : month;
    }

    /**
     * 获取当前是几号，两位数，例如 02
     *
     * @return 当前几号
     */
    public static String getCurrentDayFull() {
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        return day.length() == 1 ? "0" + day : day;
    }
}
