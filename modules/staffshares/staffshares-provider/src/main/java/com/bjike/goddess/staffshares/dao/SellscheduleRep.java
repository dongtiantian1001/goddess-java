package com.bjike.goddess.staffshares.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.entity.Sellschedule;

/**
 * 出售记录表持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SellscheduleRep extends JpaRep<Sellschedule, SellscheduleDTO> {

}