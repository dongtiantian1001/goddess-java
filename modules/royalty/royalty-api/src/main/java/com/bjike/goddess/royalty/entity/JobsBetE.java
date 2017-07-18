package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 岗位间对赌表E
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表E ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_jobsbete")
public class JobsBetE extends BaseEntity {


    /**
     * 未达标分配岗位
     */
    @Column(name = "unmetAllocationJobs", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未达标分配岗位'")
    private String unmetAllocationJobs;

    /**
     * 未达标分配
     */
    @Column(name = "unmetAllocation", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '未达标分配'")
    private Double unmetAllocation;

    /**
     * 岗位间对赌表D
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "jobsBetD_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位间对赌表D'")
    private JobsBetD jobsBetD;

    public String getUnmetAllocationJobs() {
        return unmetAllocationJobs;
    }

    public void setUnmetAllocationJobs(String unmetAllocationJobs) {
        this.unmetAllocationJobs = unmetAllocationJobs;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }

    public JobsBetD getJobsBetD() {
        return jobsBetD;
    }

    public void setJobsBetD(JobsBetD jobsBetD) {
        this.jobsBetD = jobsBetD;
    }
}