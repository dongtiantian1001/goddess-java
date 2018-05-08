package com.bjike.goddess.staffwelfare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffwelfare.dto.PersonalFestivalWishDTO;
import com.bjike.goddess.staffwelfare.entity.PersonalFestivalWish;

/**
* 节日祝福语持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-07 03:08 ]
* @Description:	[ 节日祝福语持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PersonalFestivalWishRep extends JpaRep<PersonalFestivalWish ,PersonalFestivalWishDTO> { 

 }