package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.HolidaySetDTO;
import com.bjike.goddess.attendance.entity.HolidaySet;

/**
* 假期设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-20 11:54 ]
* @Description:	[ 假期设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface HolidaySetRep extends JpaRep<HolidaySet ,HolidaySetDTO> { 

 }