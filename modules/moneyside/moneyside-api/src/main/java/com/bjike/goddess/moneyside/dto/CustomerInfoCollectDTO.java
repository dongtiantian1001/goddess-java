package com.bjike.goddess.moneyside.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 客户信息汇总数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoCollectDTO extends BaseDTO {
    /**
     * 投资人
     */
    private String[] investor;

    public String[] getInvestor() {
        return investor;
    }

    public void setInvestor(String[] investor) {
        this.investor = investor;
    }
}