package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-03 15:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ToolTipBO extends BaseBO{

    private String trigger;

    private String formatter;

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
