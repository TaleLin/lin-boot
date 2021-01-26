package com.talelin.linboot.autoconfigure.fileupload.qiniu;

/**
 * 七牛云对象存储文件上传地区配置
 *
 * @author 桔子
 * @since 2020/12/12 22:23
 */
public enum QiniuRegionEnum {

    /**
     * 机房区域
     */
    HUADONG(0,"华东"),
    HUABEI(0,"华北"),
    HUANAN(0,"华南"),
    BEIMEI(0,"北美"),
    DONGNANYA(0,"东南亚"),
    ;

    private final Integer value;

    private final String desc;

    QiniuRegionEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
