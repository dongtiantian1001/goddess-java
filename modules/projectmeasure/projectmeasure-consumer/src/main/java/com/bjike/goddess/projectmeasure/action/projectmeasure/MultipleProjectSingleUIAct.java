package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.MultipleProjectSingleUIAPI;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIBDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUIB;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUIBTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.vo.MultipleProjectSingleUIVO;
import com.bjike.goddess.projectmeasure.vo.MultipleProjectSingleUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 多项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("msui")
public class MultipleProjectSingleUIAct {

    @Autowired
    private MultipleProjectSingleUIAPI multipleProjectSingleUIAPI;


    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = multipleProjectSingleUIAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据id查询多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @return class MultipleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/msui/{id}")
    public Result findById(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            MultipleProjectSingleUIBO bo = multipleProjectSingleUIAPI.getOne(id);
            MultipleProjectSingleUIVO vo = BeanTransform.copyProperties(bo, MultipleProjectSingleUIVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 多项目单个界面dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated MultipleProjectSingleUIDTO dto, BindingResult result) throws ActException {
        try {
            Long count = multipleProjectSingleUIAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 分页查询多项目单个界面
     *
     * @param dto 多项目单个界面传输对象
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MultipleProjectSingleUIBDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(multipleProjectSingleUIAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加多项目单个界面
     *
     * @param to 多项目单个界面to信息
     * @return class MultipleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) MultipleProjectSingleUIBTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            multipleProjectSingleUIAPI.save(to);
            return ActResult.initialize("insert success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            multipleProjectSingleUIAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑多项目单个界面
     *
     * @param to 多项目单个界面to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) MultipleProjectSingleUIBTO to, BindingResult result) throws ActException {
        try {
            multipleProjectSingleUIAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}