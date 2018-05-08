package com.bjike.goddess.abilitydisplay.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 能力展示汇总
* @Author:			[ wanyi ]
* @Date:			[  2018-01-08 02:04 ]
* @Description:	[ 能力展示汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SummaryTO extends BaseTO { 

/**
* 公司名称
*/
 private String  name; 

/**
* 公司资质数量
*/
 private Integer  qualifiNum; 

/**
* 管理资质数量
*/
 private Integer  adminNum; 

/**
* 专业资质数量
*/
 private Integer  majorNum; 

/**
* 中兴证书数量
*/
 private Integer  zteNum; 

/**
* 华为证书数量
*/
 private Integer  huaweiNum; 

/**
* 已完成项目
*/
 private Integer  finishProNum; 

/**
* 尚在进行中的项目
*/
 private Integer  impProNum; 



 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public Integer getQualifiNum () { 
 return qualifiNum;
 } 
 public void setQualifiNum (Integer qualifiNum ) { 
 this.qualifiNum = qualifiNum ; 
 } 
 public Integer getAdminNum () { 
 return adminNum;
 } 
 public void setAdminNum (Integer adminNum ) { 
 this.adminNum = adminNum ; 
 } 
 public Integer getMajorNum () { 
 return majorNum;
 } 
 public void setMajorNum (Integer majorNum ) { 
 this.majorNum = majorNum ; 
 } 
 public Integer getZteNum () { 
 return zteNum;
 } 
 public void setZteNum (Integer zteNum ) { 
 this.zteNum = zteNum ; 
 } 
 public Integer getHuaweiNum () { 
 return huaweiNum;
 } 
 public void setHuaweiNum (Integer huaweiNum ) { 
 this.huaweiNum = huaweiNum ; 
 } 
 public Integer getFinishProNum () { 
 return finishProNum;
 } 
 public void setFinishProNum (Integer finishProNum ) { 
 this.finishProNum = finishProNum ; 
 } 
 public Integer getImpProNum () { 
 return impProNum;
 } 
 public void setImpProNum (Integer impProNum ) { 
 this.impProNum = impProNum ; 
 } 
 }