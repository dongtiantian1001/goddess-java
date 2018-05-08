package com.bjike.goddess.staffshares.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 简单的数据状态
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status1 {
    /**
     * 已提交待审核
     */
    @ExcelValue(name = "已提交待审核")
    SUBMIT(0),
    /**
     * 审核通过待支付流程
     */
    @ExcelValue(name = "审核通过待支付流程")
    EXAMINE(1),
    /**
     * 删除
     */
    @ExcelValue(name = "删除")
    DELETE(2),
    /**
     * 审核不通过购买失败
     */
    @ExcelValue(name = "审核不通过购买失败")
    NOEXAMINE(3),
    /**
     * 购买成功
     */
    @ExcelValue(name = "购买成功")
    ISSUED(4)
    ;

    private int code;

    Status1(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
