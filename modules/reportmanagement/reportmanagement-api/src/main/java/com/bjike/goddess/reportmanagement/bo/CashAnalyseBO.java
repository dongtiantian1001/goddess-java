package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 现金流量分析业务传输对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-23 11:43 ]
* @Description:	[ 现金流量分析业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CashAnalyseBO extends BaseBO { 

/**
* 项目
*/
 private String  project; 

/**
* 金额
*/
 private String  money; 

/**
* 比率
*/
 private String  rate; 

/**
* 分析
*/
 private String  analyse; 

/**
* 管理建议
*/
 private String  advice;

 public CashAnalyseBO() {
 }

 public CashAnalyseBO(String project, String money, String rate, String analyse, String advice, String id) {
  this.project = project;
  this.money = money;
  this.rate = rate;
  this.analyse = analyse;
  this.advice = advice;
  this.id = id;
 }

 public String getProject () {
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public String getMoney () { 
 return money;
 } 
 public void setMoney (String money ) { 
 this.money = money ; 
 } 
 public String getRate () { 
 return rate;
 } 
 public void setRate (String rate ) { 
 this.rate = rate ; 
 } 
 public String getAnalyse () { 
 return analyse;
 } 
 public void setAnalyse (String analyse ) { 
 this.analyse = analyse ; 
 } 
 public String getAdvice () { 
 return advice;
 } 
 public void setAdvice (String advice ) { 
 this.advice = advice ; 
 } 
 }