package com.bjike.goddess.organize.enums;

/**
 * 代理类型
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 15:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AgentType {
    /**
     * 临时代理
     */
    CARETAKER(0),
    /**
     * 轮岗
     */
    ROTATION(1),
    /**
     * 工作交接
     */
    HANDOVER(2);
    private int code;

    AgentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String s = null;
        switch (code) {
            case 0:
                s = "临时代理";
                break;
            case 1:
                s = "轮岗";
                break;
            case 2:
                s = "工作交接";
                break;
        }
        return s;
    }
}
