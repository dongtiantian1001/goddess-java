package com.bjike.goddess.festival.type;

/**
 * 导航权限
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
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
     * 删除
     */
    DELETE(3),
    /**
     * 查看
     */
    SEE(4);


    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
