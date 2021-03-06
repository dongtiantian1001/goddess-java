package com.bjike.goddess.staffentry.vo;

/**
 * 家庭成员表出层对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 10:35]
 * @Description: [家庭成员表出层对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FamilyMemberVO {

    /**
     * id
     */
    private String id;

    /**
     * 称谓
     */
    private String title;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 单位
     */
    private String unit;

    /**
     * 职务
     */
    private String position;

    /**
     * 联系方式
     */
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
