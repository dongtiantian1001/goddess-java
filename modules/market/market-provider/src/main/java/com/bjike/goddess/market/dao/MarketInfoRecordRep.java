package com.bjike.goddess.market.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.market.entity.MarketInfoRecord;

/**
* 市场信息记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-20 11:50 ]
* @Description:	[ 市场信息记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MarketInfoRecordRep extends JpaRep<MarketInfoRecord ,MarketInfoRecordDTO> { 

 }