package com.bjike.goddess.workjoin.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 任务交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskJoinTO extends BaseTO {
    /**
     * 工作交接编号
     */
    @NotBlank(message = "工作交接不能为空",groups = {ADD.class, EDIT.class})
    private String workJoinNum;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务要求
     */
    private String taskRequire;

    /**
     * 任务节点
     */
    private String taskNode;

    /**
     * 完成情况
     */
    private String completion;

    /**
     * 任务时间
     */
    private String taskTime;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 汇报对象
     */
    private String reportObject;

    /**
     * 任务来源路径
     */
    private String taskPath;

    public String getWorkJoinNum() {
        return workJoinNum;
    }

    public void setWorkJoinNum(String workJoinNum) {
        this.workJoinNum = workJoinNum;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskRequire() {
        return taskRequire;
    }

    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    public String getTaskNode() {
        return taskNode;
    }

    public void setTaskNode(String taskNode) {
        this.taskNode = taskNode;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getReportObject() {
        return reportObject;
    }

    public void setReportObject(String reportObject) {
        this.reportObject = reportObject;
    }

    public String getTaskPath() {
        return taskPath;
    }

    public void setTaskPath(String taskPath) {
        this.taskPath = taskPath;
    }
}