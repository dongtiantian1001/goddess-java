package com.bjike.goddess.competitorsmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.competitorsmanagement.dto.CollectDTO;
import com.bjike.goddess.competitorsmanagement.entity.Collect;

/**
* 竞争对手管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-15 04:55 ]
* @Description:	[ 竞争对手管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectRep extends JpaRep<Collect ,CollectDTO> { 

 }