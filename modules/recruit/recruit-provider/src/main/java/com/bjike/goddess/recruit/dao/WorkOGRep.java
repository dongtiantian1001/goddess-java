package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.WorkOGDTO;
import com.bjike.goddess.recruit.entity.WorkOG;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * 工作对赌持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkOGRep extends JpaRep<WorkOG, WorkOGDTO> {

    WorkOG findByModular(String modular);

    List<WorkOG> findByRaters(String name);

    @Modifying
    void deleteByModular(String modular);

    List<WorkOG> findByRatersOrScoringOB(String name, String sc);

    WorkOG findByTime(String time);
}