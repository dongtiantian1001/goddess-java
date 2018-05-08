package com.bjike.goddess.assistance.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 补助状态
 *
 * @Author: [lijuntao]
 * @Date: [2017-03-20 19:57]
 * @Description: [补助状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Usage {

    /**
     * 用自己的电脑
     */
    @ExcelValue(name="用自己的电脑")
    USEMYSELFCOMPUTER(0),
    /**
     * 用公司的电脑
     */
    @ExcelValue(name="用公司的电脑")
    USECOMPANYCOMPURTER(1);

    private int code;

    Usage(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public static String exportStrConvert(Usage usage) {
        String name = "";
        if (usage.equals(Usage.USEMYSELFCOMPUTER)) {
            name = "用自己的电脑";
        }
        if (usage.equals(Usage.USECOMPANYCOMPURTER)) {
            name = "用公司的电脑";
        }
        return name;
    }
}
