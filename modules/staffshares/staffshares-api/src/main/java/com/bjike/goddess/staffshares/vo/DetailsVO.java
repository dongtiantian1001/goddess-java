package com.bjike.goddess.staffshares.vo;

/**
 * 交易详情表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DetailsVO {

    /**
     * id
     */
    private String id;
    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 出售/发行人
     */
    private String publisher;

    /**
     * 出售/发行数量
     */
    private Long number;

    /**
     * 出售/发行价格
     */
    private Double price;

    /**
     * 出售/发行时间
     */
    private String time;

    /**
     * 剩余出售量
     */
    private Long sharesNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(Long sharesNum) {
        this.sharesNum = sharesNum;
    }
}