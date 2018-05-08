package com.bjike.goddess.staffwelfare.action.staffwelfare;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.api.StaffBirthDayWelfareAPI;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.entity.WishesStatement;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.vo.StaffBirthDayWelfareVO;
import com.bjike.goddess.staffwelfare.vo.ThankStatementVO;
import com.bjike.goddess.staffwelfare.vo.WishesStatementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工生日福利记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffbirthdaywelfare")
public class StaffBirthDayWelfareAct {

    @Autowired
    private StaffBirthDayWelfareAPI staffBirthDayWelfareAPI;

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

            Boolean isHasPermission = staffBirthDayWelfareAPI.guidePermission(guidePermissionTO);
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
     * 分页查询员工生日福利记录
     *
     * @param dto 分页条件
     * @return class StaffBirthDayWelfareVO
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(StaffBirthDayWelfareDTO dto) throws ActException {
        try {
            List<StaffBirthDayWelfareVO> voList = BeanTransform.copyProperties(staffBirthDayWelfareAPI.pageList(dto), StaffBirthDayWelfareVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 查询生日祝福语
//     *
//     * @param employeeName 员工姓名
//     * @return class WishesStatementVO
//     * @version v1
//     */
//    @GetMapping("v1/find/wish")
//    public Result findWish(@RequestParam String employeeName) throws ActException {
//       try {
//            List<WishesStatementVO> voList = BeanTransform.copyProperties(staffBirthDayWelfareAPI.findWish(employeeName), WishesStatementVO.class);
//            return ActResult.initialize(voList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 查询生日感谢语
//     *
//     * @param employeeName 员工姓名
//     * @return class ThankStatementVO
//     * @version v1
//     */
//    @GetMapping("v1/find/thank")
//    public Result findThank(@RequestParam String employeeName) throws ActException {
//        try {
//            List<ThankStatementVO> voList = BeanTransform.copyProperties(staffBirthDayWelfareAPI.findThank(employeeName), ThankStatementVO.class);
//            return ActResult.initialize(voList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 一声祝福
     * @param employeeName 员工姓名
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/wish")
    public Result findThank(@RequestParam String employeeName) throws ActException {
        try {
            List<String[]> voList = staffBirthDayWelfareAPI.findOnewish(employeeName);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 查询列表总条数
     *
     * @param dto 条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StaffBirthDayWelfareDTO dto) throws ActException{
        try {
            Long count = staffBirthDayWelfareAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}