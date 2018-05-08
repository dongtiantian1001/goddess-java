package com.bjike.goddess.financeinit.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-29 16:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CategoryName {

    /**
     * 资产类
     */
    @ExcelValue(name = "资产类")
    ASSETS(0),
    /**
     * 负债类
     */
    @ExcelValue(name = "负债类")
    LIABILITIES(1),
    /**
     * 共同类
     */
    @ExcelValue(name = "共同类")
    COMMON(2),
    /**
     * 权益类
     */
    @ExcelValue(name = "权益类")
    RIGHTSINTERESTS(3),
    /**
     * 成本类
     */
    @ExcelValue(name = "成本类")
    COST(4),
    /**
     * 损益类
     */
    @ExcelValue(name = "损益类")
    PROFITLOSS(5);

    private int code;

    CategoryName(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static String enumToString(CategoryName categoryName) {
        String name = "";
        switch (categoryName) {
            case ASSETS:
                name = "资产类";
                break;
            case LIABILITIES:
                name = "负债类";
                break;
            case COMMON:
                name = "共同类";
                break;
            case RIGHTSINTERESTS:
                name = "权益类";
                break;
            case COST:
                name = "成本类";
                break;
            case PROFITLOSS:
                name = "损益类";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }
}
