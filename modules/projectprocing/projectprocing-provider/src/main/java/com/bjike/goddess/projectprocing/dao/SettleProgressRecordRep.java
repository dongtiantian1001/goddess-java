package com.bjike.goddess.projectprocing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;

/**
* 结算进度调整记录&结算问题汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:19 ]
* @Description:	[ 结算进度调整记录&结算问题汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SettleProgressRecordRep extends JpaRep<SettleProgressRecord ,SettleProgressRecordDTO> { 

 }