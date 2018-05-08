package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkRangeListBO extends BaseBO {

//    /**
//     * 工作范围
//     */
//    private String workRange;

    /**
     * 工作范围
     */
    private String[] workRanges;

    /**
     * 工作界面(节点)
     */
    private String[] node;

    /**
     * 创建时间
     */
    private String createTime;

    public String[] getWorkRanges() {
        return workRanges;
    }

    public void setWorkRanges(String[] workRanges) {
        this.workRanges = workRanges;
    }

    public String[] getNode() {
        return node;
    }

    public void setNode(String[] node) {
        this.node = node;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

//    public String getWorkRange() {
//        return workRange;
//    }
//
//    public void setWorkRange(String workRange) {
//        this.workRange = workRange;
//    }
}
