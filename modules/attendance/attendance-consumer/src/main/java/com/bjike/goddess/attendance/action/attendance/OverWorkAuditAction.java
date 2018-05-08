package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.overtime.OverWorkAPI;
import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.OverLongAndRelaxdayDTO;
import com.bjike.goddess.attendance.dto.overtime.OverWorkDTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.OverWorkAuditTO;
import com.bjike.goddess.attendance.vo.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.OverWorkVO;
import com.bjike.goddess.attendance.vo.PositionAndDepartVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 加班审核
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("overworkaudit")
public class OverWorkAuditAction {

    @Autowired
    private OverWorkAPI overWorkAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = overWorkAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     *
     * @param overWorkDTO 加班信息dto
     * @des 获取所有加班信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OverWorkDTO overWorkDTO) throws ActException {
        try {
            Long count = overWorkAPI.countAudit(overWorkDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param overWorkDTO 加班信息dto
     * @return class OverWorkVO
     * @des 获取所有加班信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListOverWork(OverWorkDTO overWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<OverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.listAudit(overWorkDTO), OverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param overWorkAuditTO 加班基本信息数据to
     * @return class OverWorkVO
     * @des 审核加班
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/audit")
    public Result auditOverWork(@Validated(OverWorkAuditTO.TESTAddAndEdit.class) OverWorkAuditTO overWorkAuditTO, BindingResult bindingResult) throws ActException {
        try {
            OverWorkBO overWorkBO1 = overWorkAPI.auditOverWork(overWorkAuditTO);
            return ActResult.initialize(BeanTransform.copyProperties(overWorkBO1, OverWorkVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除加班信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteOverWork(@PathVariable String id) throws ActException {
        try {
            overWorkAPI.deleteOverWork(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 获取地区
     *
     * @return class AreaBO
     * @des 获取所有地区
     * @version v1
     */
    @GetMapping("v1/areaList")
    public Result areaList() throws ActException {
        try {
            List<AreaBO> list = overWorkAPI.areaList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取任务下达人或加班人员
     *
     * @des 获取任务下达人或加班人员
     * @version v1
     */
    @GetMapping("v1/peopleList")
    public Result peopleList() throws ActException {
        try {
            List<String> list = overWorkAPI.peopleList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据加班人员获取部门和职位
     *
     * @param overWorker 加班人
     * @return class PositionAndDepartVO
     * @des 根据加班人员获取部门和职位
     * @version v1
     * @return class PositionAndDepartVO
     */
    @GetMapping("v1/positAndDepart/{overWorker}")
    public Result getPositAndDepart(@PathVariable String overWorker) throws ActException {
        try {
            PositionAndDepartVO list = overWorkAPI.getPositAndDepart(overWorker);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取加班时长和可休天数
     *
     * @param overLongAndRelaxdayDTO 时长和可休天数数据
     * @return class OverLongAndRelaxDayVO
     * @des 根据加班开始时间和加班结束时间和是否午休计算加班时长和可休天数
     * @version v1
     * @return class OverLongAndRelaxDayVO
     */
    @GetMapping("v1/caculateTime")
    public Result caculateTime(@PathVariable OverLongAndRelaxdayDTO overLongAndRelaxdayDTO) throws ActException {
        try {
            OverLongAndRelaxDayVO list = overWorkAPI.caculateTime(overLongAndRelaxdayDTO);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}