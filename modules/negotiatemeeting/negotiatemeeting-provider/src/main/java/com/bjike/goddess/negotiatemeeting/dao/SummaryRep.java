package com.bjike.goddess.negotiatemeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.negotiatemeeting.dto.SummaryDTO;
import com.bjike.goddess.negotiatemeeting.entity.Summary;

/**
* 协商会议纪要持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-31 03:49 ]
* @Description:	[ 协商会议纪要持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SummaryRep extends JpaRep<Summary ,SummaryDTO> { 

 }