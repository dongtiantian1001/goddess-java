package com.bjike.goddess.fundrecords.enums;

/**
 * 导航栏类型
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-20 19:57]
 * @Description: [导航栏类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GuideAddrStatus {

    /**
     * 列表
     */
    LIST(0),
    /**
     * 添加
     */
    ADD(1),
    /**
     * 编辑
     */
    EDIT(2),
    /**
     * 组织结构
     */
    ORGANIZE(3),
    /**
     * 删除
     */
    DELETE(4),
    /**
     * 冻结
     */
    CONGEL(5),
    /**
     * 解冻
     */
    THAW(6),
    /**
     * 汇总
     */
    COLLECT(7),
    /**
     * 上传附件
     */
    UPLOAD(8),
    /**
     * 下载附件
     */
    DOWNLOAD(9),
    /**
     * 导入
     */
    IMPORT(10),
    /**
     * 导出
     */
    EXPORT(11),
    /**
     * 查看
     */
    SEE(12),
    /**
     * 查看附件
     */
    SEEFILE(13),
    /**
     * 月汇总
     */
    MONTH(14),
    /**
     * 条件汇总
     */
    CONDITION(15),
    /**
     * 地区分析
     */
    AREA(16),
    /**
     * 项目分析
     */
    PROJECT(17),
    /**
     * 项目组分析
     */
    GROUP(18);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
