package com.bjike.goddess.checkhost.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 宿舍信息管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DormitoryInfoTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 宿舍地址
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "宿舍地址不能为空")
    private String address;

    /**
     * 宿舍钥匙数量（把）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "宿舍钥匙数量（把）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "宿舍钥匙数量（把）不能小于0")
    private Integer keysNumber;

    /**
     * 宿舍钥匙分配情况
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "宿舍钥匙分配情况不能为空")
    private String keysDistribution;

    /**
     * 宿舍负责人
     */
    private String head;

    /**
     * 负责人联系方式
     */
    private String headContact;

    /**
     * 宿舍床位数量（个）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "宿舍床位数量（个）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "宿舍床位数量（个）不能小于0")
    private Integer berth;

    /**
     * 已入住床位（个）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "已入住床位（个）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "已入住床位（个）不能小于0")
    private Integer beeBerth;

    /**
     * 闲置床位（个）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "闲置床位（个）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "闲置床位（个）不能小于0")
    private Integer idleBerth;

    /**
     * 宿舍床上3件套数量(件)
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "宿舍床上3件套数量(件)不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "宿舍床上3件套数量(件)不能小于0")
    private Integer suit;

    /**
     * 已领用数量（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "已领用数量（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "已领用数量（件）不能小于0")
    private Integer recipientsSuit;

    /**
     * 闲置数量（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "闲置数量（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "闲置数量（件）不能小于0")
    private Integer idleSuit;

    /**
     * 宿舍被褥数量（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "宿舍被褥数量（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "宿舍被褥数量（件）不能小于0")
    private Integer bedding;

    /**
     * 已领用数量（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "已领用数量（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "已领用数量（件）不能小于0")
    private Integer recipientsBedding;

    /**
     * 闲置数量（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "闲置数量（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "闲置数量（件）不能小于0")
    private Integer idleBedding;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getKeysNumber() {
        return keysNumber;
    }

    public void setKeysNumber(Integer keysNumber) {
        this.keysNumber = keysNumber;
    }

    public String getKeysDistribution() {
        return keysDistribution;
    }

    public void setKeysDistribution(String keysDistribution) {
        this.keysDistribution = keysDistribution;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHeadContact() {
        return headContact;
    }

    public void setHeadContact(String headContact) {
        this.headContact = headContact;
    }

    public Integer getBerth() {
        return berth;
    }

    public void setBerth(Integer berth) {
        this.berth = berth;
    }

    public Integer getBeeBerth() {
        return beeBerth;
    }

    public void setBeeBerth(Integer beeBerth) {
        this.beeBerth = beeBerth;
    }

    public Integer getIdleBerth() {
        return idleBerth;
    }

    public void setIdleBerth(Integer idleBerth) {
        this.idleBerth = idleBerth;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }

    public Integer getRecipientsSuit() {
        return recipientsSuit;
    }

    public void setRecipientsSuit(Integer recipientsSuit) {
        this.recipientsSuit = recipientsSuit;
    }

    public Integer getIdleSuit() {
        return idleSuit;
    }

    public void setIdleSuit(Integer idleSuit) {
        this.idleSuit = idleSuit;
    }

    public Integer getBedding() {
        return bedding;
    }

    public void setBedding(Integer bedding) {
        this.bedding = bedding;
    }

    public Integer getRecipientsBedding() {
        return recipientsBedding;
    }

    public void setRecipientsBedding(Integer recipientsBedding) {
        this.recipientsBedding = recipientsBedding;
    }

    public Integer getIdleBedding() {
        return idleBedding;
    }

    public void setIdleBedding(Integer idleBedding) {
        this.idleBedding = idleBedding;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}