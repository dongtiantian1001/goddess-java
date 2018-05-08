package com.bjike.goddess.abilitydisplay.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 公司规划
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:24 ]
* @Description:	[ 公司规划 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CompanyPlanTO extends BaseTO { 

/**
* 组织架构规划
*/
 private String  planningORG; 

/**
* 公司发展规划
*/
 private String  planningCD; 

/**
* 项目发展规划
*/
 private String  planningPD; 

/**
* 经营计划
*/
 private String  planningBN; 



 public String getPlanningORG () { 
 return planningORG;
 } 
 public void setPlanningORG (String planningORG ) { 
 this.planningORG = planningORG ; 
 } 
 public String getPlanningCD () { 
 return planningCD;
 } 
 public void setPlanningCD (String planningCD ) { 
 this.planningCD = planningCD ; 
 } 
 public String getPlanningPD () { 
 return planningPD;
 } 
 public void setPlanningPD (String planningPD ) { 
 this.planningPD = planningPD ; 
 } 
 public String getPlanningBN () { 
 return planningBN;
 } 
 public void setPlanningBN (String planningBN ) { 
 this.planningBN = planningBN ; 
 } 
 }