package com.bjike.goddess.staffwelfare.action.staffwelfare;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.vo.EntryRegisterVO;
import com.bjike.goddess.staffwelfare.api.StaffBirthdaySchemeAPI;
import com.bjike.goddess.staffwelfare.bo.StaffBirthdaySchemeBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthdaySchemeDTO;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.StaffBirthdaySchemeTO;
import com.bjike.goddess.staffwelfare.vo.StaffBirthdaySchemeVO;
import com.bjike.goddess.staffwelfare.vo.WishesStatementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工生日福利方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffbirthdayscheme")
public class StaffBirthdaySchemeAct {

    @Autowired
    private StaffBirthdaySchemeAPI staffBirthdaySchemeAPI;

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

            Boolean isHasPermission = staffBirthdaySchemeAPI.guidePermission(guidePermissionTO);
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
     * 查询祝福语
     *
     * @return class WishesStatementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWishStatements")
    public Result findWishStatements() throws ActException {
        try {
            List<WishesStatementVO> voList = BeanTransform.copyProperties(staffBirthdaySchemeAPI.findWishStatements(), WishesStatementVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询头像帽
     *
     * @return class WishesStatementVO
     * @version v1
     */
    @GetMapping("v1/findHeadPortraitHats")
    public Result findHeadPortraitHats() throws ActException {
        try {
            List<WishesStatementVO> voList = BeanTransform.copyProperties(staffBirthdaySchemeAPI.findHeadPortraitHats(), WishesStatementVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增员工生日福利方案
     *
     * @param to 员工生日福利方案
     * @return class StaffBirthdaySchemeVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(StaffBirthdaySchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            StaffBirthdaySchemeVO vo = BeanTransform.copyProperties(staffBirthdaySchemeAPI.addModel(to), StaffBirthdaySchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工生日福利方案
     *
     * @param to 员工生日福利方案
     * @return class StaffBirthdaySchemeVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(StaffBirthdaySchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            StaffBirthdaySchemeVO vo = BeanTransform.copyProperties(staffBirthdaySchemeAPI.editModel(to), StaffBirthdaySchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据审核人通过员工生日福利方案
     *
     * @param id 员工生日福利方案id
     * @version v1
     */
    @GetMapping("v1/pass/{id}")
    public Result pass(@PathVariable String id) throws ActException {
        try {
            staffBirthdaySchemeAPI.pass(id);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工生日福利方案
     *
     * @param id 员工生日福利方案id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            staffBirthdaySchemeAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工生日福利方案分页查询
     *
     * @param dto 分页条件
     * @return class StaffBirthdaySchemeVO
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(StaffBirthdaySchemeDTO dto) throws ActException {
        try {
            List<StaffBirthdaySchemeVO> voList = BeanTransform.copyProperties(staffBirthdaySchemeAPI.pageList(dto), StaffBirthdaySchemeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 领取福利
     *
     * @param id     方案id
     * @param remark 备注
     * @version v1
     */
    @GetMapping("v1/receive/{id}")
    public Result receive(@PathVariable String id, String remark) throws ActException {
        try {
            staffBirthdaySchemeAPI.receive(id, remark);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有地区和所属部门
     *
     * @return class EntryRegisterVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/entry")
    public Result findEntry() throws ActException {
        try {
            List<EntryRegisterBO> boList = staffBirthdaySchemeAPI.findEntry();
            List<EntryRegisterVO> voList = BeanTransform.copyProperties(boList,EntryRegisterVO.class);
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
    public Result count(StaffBirthdaySchemeDTO dto) throws ActException {
        try {
            Long count = staffBirthdaySchemeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     *
     * @param id 条件
     * @return class StaffBirthdaySchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException {
        try {
            StaffBirthdaySchemeBO bo = staffBirthdaySchemeAPI.findOne(id);
            StaffBirthdaySchemeVO vo = BeanTransform.copyProperties(bo, StaffBirthdaySchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总
     *
     * @param dto 汇总条件
     * @return class StaffBirthdaySchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(StaffBirthdaySchemeDTO dto) throws ActException {
        try {
            List<StaffBirthdaySchemeBO> boList = staffBirthdaySchemeAPI.collect(dto);
            List<StaffBirthdaySchemeVO> voList = BeanTransform.copyProperties(boList, StaffBirthdaySchemeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}