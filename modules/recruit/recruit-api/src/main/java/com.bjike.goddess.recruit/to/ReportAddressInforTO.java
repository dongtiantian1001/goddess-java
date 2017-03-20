package com.bjike.goddess.recruit.to;

import javax.persistence.Column;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 10:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ReportAddressInforTO {

    /**
     * 报道地址
     */
    private String reportAddress;

    /**
     * 备注
     */
    private String comment;

    public String getReportAddress() {
        return reportAddress;
    }

    public void setReportAddress(String reportAddress) {
        this.reportAddress = reportAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}