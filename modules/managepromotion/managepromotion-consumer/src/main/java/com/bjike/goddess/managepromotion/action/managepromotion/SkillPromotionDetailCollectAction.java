package com.bjike.goddess.managepromotion.action.managepromotion;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.EmployeePromotedAPI;
import com.bjike.goddess.managepromotion.bo.StaffSkillCollectBO;
import com.bjike.goddess.managepromotion.to.ProfessionalSkillTO;
import com.bjike.goddess.managepromotion.to.SkillPromotionDetailCollectTO;
import com.bjike.goddess.managepromotion.to.StaffSkillCollectTO;
import com.bjike.goddess.managepromotion.vo.ProfessionalSkillCollectVO;
import com.bjike.goddess.managepromotion.vo.SkillPromotionDetailCollectAVO;
import com.bjike.goddess.managepromotion.vo.SkillPromotionDetailCollectVO;
import com.bjike.goddess.managepromotion.vo.StaffSkillCollectVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 技能晋升明细汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 15:26]
 * @Description: [技能晋升明细汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("skillpromotiondetailcollect")
public class SkillPromotionDetailCollectAction {
    @Autowired
    private EmployeePromotedAPI employeePromotedAPI;

    /**
     * 技能晋升明细周汇总
     *
     * @param to to
     * @return class SkillPromotionDetailCollectAVO
     * @des 技能晋升明细周汇总
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(SkillPromotionDetailCollectTO to) throws ActException {
        try {
            SkillPromotionDetailCollectAVO collectAVO =
                    BeanTransform.copyProperties(employeePromotedAPI.detailWeekCollect(to), SkillPromotionDetailCollectAVO.class);
            return ActResult.initialize(collectAVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能晋升明细月汇总
     *
     * @param to to
     * @return class SkillPromotionDetailCollectAVO
     * @des 技能晋升明细月汇总
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(SkillPromotionDetailCollectTO to) throws ActException {
        try {
            SkillPromotionDetailCollectAVO collectAVO =
                    BeanTransform.copyProperties(employeePromotedAPI.detailMonthCollect(to), SkillPromotionDetailCollectAVO.class);
            return ActResult.initialize(collectAVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能晋升明细累计汇总
     *
     * @param to to
     * @return class SkillPromotionDetailCollectAVO
     * @des 技能晋升明细累计汇总
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(SkillPromotionDetailCollectTO to) throws ActException {
        try {
            SkillPromotionDetailCollectAVO collectAVO =
                    BeanTransform.copyProperties(employeePromotedAPI.detailTotalCollect(to), SkillPromotionDetailCollectAVO.class);
            return ActResult.initialize(collectAVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业技能日汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectVO
     * @des 各专业技能日汇总
     * @version v1
     */
    @GetMapping("v1/daySkillCollect")
    public Result daySkillCollect(ProfessionalSkillTO to) throws ActException {
        try {
            List<ProfessionalSkillCollectVO> skillPromotionDetailCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.dayProfessionalCollect(to), ProfessionalSkillCollectVO.class);
            return ActResult.initialize(skillPromotionDetailCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业技能周汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectVO
     * @des 各专业技能周汇总
     * @version v1
     */
    @GetMapping("v1/weekSkillCollect")
    public Result weekSkillCollect(ProfessionalSkillTO to) throws ActException {
        try {
            List<ProfessionalSkillCollectVO> skillPromotionDetailCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.weekProfessionalCollect(to), ProfessionalSkillCollectVO.class);
            return ActResult.initialize(skillPromotionDetailCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业技能月汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectVO
     * @des 各专业技能月汇总
     * @version v1
     */
    @GetMapping("v1/monthSkillCollect")
    public Result monthSkillCollect(ProfessionalSkillTO to) throws ActException {
        try {
            List<ProfessionalSkillCollectVO> skillPromotionDetailCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.monthProfessionalCollect(to), ProfessionalSkillCollectVO.class);
            return ActResult.initialize(skillPromotionDetailCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业技能累计汇总
     *
     * @param to to
     * @return class ProfessionalSkillCollectVO
     * @des 各专业技能累计汇总
     * @version v1
     */
    @GetMapping("v1/totalSkillCollect")
    public Result totalSkillCollect(ProfessionalSkillTO to) throws ActException {
        try {
            List<ProfessionalSkillCollectVO> skillPromotionDetailCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.totalProfessionalCollect(to), ProfessionalSkillCollectVO.class);
            return ActResult.initialize(skillPromotionDetailCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员技能&晋升日汇总
     *
     * @param to to
     * @return class StaffSkillCollectVO
     * @des 人员技能&晋升日汇总
     * @version v1
     */
    @GetMapping("v1/dayStaffCollect")
    public Result dayStaffCollect(StaffSkillCollectTO to) throws ActException {
        try {
            List<StaffSkillCollectVO> staffSkillCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.dayStaffCollect(to), StaffSkillCollectVO.class);
            return ActResult.initialize(staffSkillCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员技能&晋升周汇总
     *
     * @param to to
     * @return class StaffSkillCollectVO
     * @des 人员技能&晋升周汇总
     * @version v1
     */
    @GetMapping("v1/weekStaffCollect")
    public Result weekStaffCollect(StaffSkillCollectTO to) throws ActException {
        try {
            List<StaffSkillCollectVO> staffSkillCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.weekStaffCollect(to), StaffSkillCollectVO.class);
            return ActResult.initialize(staffSkillCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员技能&晋升月汇总
     *
     * @param to to
     * @return class StaffSkillCollectVO
     * @des 人员技能&晋升月汇总
     * @version v1
     */
    @GetMapping("v1/monthStaffCollect")
    public Result monthStaffCollect(StaffSkillCollectTO to) throws ActException {
        try {
            List<StaffSkillCollectVO> staffSkillCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.monthStaffCollect(to), StaffSkillCollectVO.class);
            return ActResult.initialize(staffSkillCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员技能&晋升累计汇总
     *
     * @param to to
     * @return class StaffSkillCollectVO
     * @des 人员技能&晋升累计汇总
     * @version v1
     */
    @GetMapping("v1/totalStaffCollect")
    public Result totalStaffCollect(StaffSkillCollectTO to) throws ActException {
        try {
            List<StaffSkillCollectVO> staffSkillCollectVOS =
                    BeanTransform.copyProperties(employeePromotedAPI.totalStaffCollect(to), StaffSkillCollectVO.class);
            return ActResult.initialize(staffSkillCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前月有几周
     *
     * @param year  年份
     * @param month 月份
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWeek/{year}/{month}")
    public Result findWeek(@PathVariable Integer year, @PathVariable Integer month) throws ActException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= weekNum; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
@Autowired
private UserAPI userAPI;
    @GetMapping("v1/getECharts")
    public Result getECharts() throws SerException{
        UserBO userBO = userAPI.currentUser();
        userBO.getUsername();
        return new ActResult();
    }
}
