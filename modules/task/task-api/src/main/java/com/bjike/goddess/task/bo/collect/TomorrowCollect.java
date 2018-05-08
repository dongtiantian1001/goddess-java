package com.bjike.goddess.task.bo.collect;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 11:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TomorrowCollect {
    @ExcelHeader(name = "人员")
    private String username; //人员
    @ExcelHeader(name = "模块")
    private String module; //模块
    @ExcelHeader(name = "岗位")
    private String post; //岗位
    @ExcelHeader(name = "合同外部项目名称")
    private String outProject; //合同外部项目名称
    @ExcelHeader(name = "内部项目名称")
    private String innerProject; //内部项目名称
    @ExcelHeader(name = "任务类型")
    private String taskType; //任务类型
    @ExcelHeader(name = "工作内容")
    private String content; //工作内容
    @ExcelHeader(name = "计划任务时长")
    private String planDuration; //计划任务时长
    @ExcelHeader(name = "计划工作量")
    private String planNum; //计划工作量
    @ExcelHeader(name = "备注")
    private String remark; //备注

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getOutProject() {
        return outProject;
    }

    public void setOutProject(String outProject) {
        this.outProject = outProject;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlanDuration() {
        return planDuration;
    }

    public void setPlanDuration(String planDuration) {
        this.planDuration = planDuration;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
