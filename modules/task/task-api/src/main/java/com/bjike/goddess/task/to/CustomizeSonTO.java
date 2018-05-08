package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.CollectSuitation;

/**
 * 自定义汇总子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 11:09 ]
 * @Description: [ 自定义汇总子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomizeSonTO extends BaseTO {

    /**
     * 自定义汇总id
     */
    private String customizeId;

    /**
     * 汇总字段
     */
    private String title;

    /**
     * 汇总条件
     */
    private CollectSuitation collectSuitation;

    /**
     * 任务类型
     */
    private String type;

    /**
     * 汇总表头字段
     */
    private String tableTitle;


    public String getCustomizeId() {
        return customizeId;
    }

    public void setCustomizeId(String customizeId) {
        this.customizeId = customizeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CollectSuitation getCollectSuitation() {
        return collectSuitation;
    }

    public void setCollectSuitation(CollectSuitation collectSuitation) {
        this.collectSuitation = collectSuitation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }
}