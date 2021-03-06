package com.bjike.goddess.socialinsurance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 客户权限配置操作对象业务传输对象
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-12 05:53 ]
* @Description:	[ 客户权限配置操作对象业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CusPermissionOperateBO extends BaseBO { 

/**
* 操作对象
*/
 private String  operator; 

/**
* 客户权限id,一对多关系
*/
 private String  cuspermissionId; 



 public String getOperator () { 
 return operator;
 } 
 public void setOperator (String operator ) { 
 this.operator = operator ; 
 } 
 public String getCuspermissionId () { 
 return cuspermissionId;
 } 
 public void setCuspermissionId (String cuspermissionId ) { 
 this.cuspermissionId = cuspermissionId ; 
 } 
 }