package com.bjike.goddess.analysis.vo;

/**
 * Created by ike on 17-5-31.
 */
public class CollectVO {
    /**
     * 时间
     */
    private String date;

    /**
     * 地区
     */
    private String area;
    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 出车司机数
     */
    private Integer carNum;

    /**
     * 司机出车费
     */
    private Double driverFee;

    /**
     * 油卡充值
     */
    private Double oilRecharge;

    /**
     * 房租
     */
    private Double rent;

    /**
     * 社保
     */
    private Double socialSecurity;

    /**
     * 员工工资
     */
    private Double staffWage;

    /**
     * 办公费
     */
    private Double office;

    /**
     * 市场费
     */
    private Double marketCost;

    /**
     * 税金
     */
    private Double tax;

    /**
     * 合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）
     */
    private Double total;

    /**
     * 员工人数
     */
    private Integer staffNum;

    /**
     * 人均工资
     */
    private Double perCapitaWage;

    /**
     * 税后余额收入
     */
    private Double incomeAfterTax;
    /**
     * 差额（税后余额收入-合计）
     */
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Double getDriverFee() {
        return driverFee;
    }

    public void setDriverFee(Double driverFee) {
        this.driverFee = driverFee;
    }

    public Double getOilRecharge() {
        return oilRecharge;
    }

    public void setOilRecharge(Double oilRecharge) {
        this.oilRecharge = oilRecharge;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Double socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Double getStaffWage() {
        return staffWage;
    }

    public void setStaffWage(Double staffWage) {
        this.staffWage = staffWage;
    }

    public Double getOffice() {
        return office;
    }

    public void setOffice(Double office) {
        this.office = office;
    }

    public Double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Double marketCost) {
        this.marketCost = marketCost;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

    public Double getPerCapitaWage() {
        return perCapitaWage;
    }

    public void setPerCapitaWage(Double perCapitaWage) {
        this.perCapitaWage = perCapitaWage;
    }

    public Double getIncomeAfterTax() {
        return incomeAfterTax;
    }

    public void setIncomeAfterTax(Double incomeAfterTax) {
        this.incomeAfterTax = incomeAfterTax;
    }

}