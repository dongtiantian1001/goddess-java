package com.bjike.goddess.contractcommunicat.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 历史评价业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:56 ]
 * @Description: [ 历史评价业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HistoryAppraiseBO extends BaseBO {

    /**
     * 历史评价
     */
    private String historyAppraise;


    public String getHistoryAppraise() {
        return historyAppraise;
    }

    public void setHistoryAppraise(String historyAppraise) {
        this.historyAppraise = historyAppraise;
    }
}