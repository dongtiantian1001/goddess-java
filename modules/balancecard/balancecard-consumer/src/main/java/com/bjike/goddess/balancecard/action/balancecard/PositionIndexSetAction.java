package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.PositionIndexSetAPI;
import com.bjike.goddess.balancecard.bo.PositionIndexSetBO;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.excel.PositionIndexSetExcel;
import com.bjike.goddess.balancecard.to.ExportExcelPositTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.PositionIndexSetTO;
import com.bjike.goddess.balancecard.vo.PositionIndexSetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 岗位指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("positionindexset")
public class PositionIndexSetAction extends BaseFileAction {



    @Autowired
    private PositionIndexSetAPI positionIndexSetAPI;


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

            Boolean isHasPermission = positionIndexSetAPI.guidePermission(guidePermissionTO);
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
     * @param positionIndexSetDTO  岗位指标信息dto
     * @des 获取所有岗位指标信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PositionIndexSetDTO positionIndexSetDTO) throws ActException {
        try {
            Long count = positionIndexSetAPI.countPositionIndexSet(positionIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位指标列表
     *
     * @param positionIndexSetDTO 岗位指标信息dto
     * @param request 前端过滤参数
     * @des 获取所有岗位指标信息
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<PositionIndexSetVO> positionIndexSetVOList = BeanTransform.copyProperties(
                    positionIndexSetAPI.listPositionIndexSet(positionIndexSetDTO), PositionIndexSetVO.class, request);
            return ActResult.initialize(positionIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个岗位指标
     *
     * @param id 岗位指标信息id
     * @des 获取所有岗位指标信息
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            PositionIndexSetVO positionIndexSetVOList = BeanTransform.copyProperties(
                    positionIndexSetAPI.getOneById( id), PositionIndexSetVO.class);
            return ActResult.initialize(positionIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加岗位指标
     *
     * @param positionIndexSetTO 岗位指标基本信息数据to
     * @des 添加岗位指标
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addPositionIndexSet(@Validated(PositionIndexSetTO.TestAdd.class) PositionIndexSetTO positionIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            PositionIndexSetBO positionIndexSetBO1 = positionIndexSetAPI.addPositionIndexSet(positionIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(positionIndexSetBO1,PositionIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑岗位指标
     *
     * @param positionIndexSetTO 岗位指标基本信息数据bo
     * @des 编辑岗位指标
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editPositionIndexSet(@Validated(PositionIndexSetTO.TestAdd.class) PositionIndexSetTO positionIndexSetTO) throws ActException {
        try {
            PositionIndexSetBO positionIndexSetBO1 = positionIndexSetAPI.editPositionIndexSet(positionIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(positionIndexSetBO1,PositionIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除岗位指标信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deletePositionIndexSet(@PathVariable String id) throws ActException {
        try {
            positionIndexSetAPI.deletePositionIndexSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 查看本月指标总条数
     *
     * @param positionIndexSetDTO  岗位指标信息dto
     * @des 获取本月所有岗位指标信息总条数
     * @version v1
     */
    @GetMapping("v1/countNow")
    public Result countNow(PositionIndexSetDTO positionIndexSetDTO) throws ActException {
        try {
            Long count = positionIndexSetAPI.countNow(positionIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看本月岗位指标列表
     *
     * @param positionIndexSetDTO 岗位指标信息dto
     * @param request 前端过滤参数
     * @des 获取本月所有岗位指标信息
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @GetMapping("v1/listNow")
    public Result listNow(PositionIndexSetDTO positionIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<PositionIndexSetVO> positionIndexSetVOList = BeanTransform.copyProperties(
                    positionIndexSetAPI.listNow(positionIndexSetDTO), PositionIndexSetVO.class, request);
            return ActResult.initialize(positionIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我的指标列表总条数
     *
     * @param positionIndexSetDTO  岗位指标信息dto
     * @des 获取所有我的指标信息总条数
     * @version v1
     */
    @GetMapping("v1/countSelf")
    public Result countSelf(PositionIndexSetDTO positionIndexSetDTO,BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            Long count = positionIndexSetAPI.countSelf(positionIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我的指标列表
     *
     * @param positionIndexSetDTO 岗位指标信息dto
     * @param request 前端过滤参数
     * @des 获取所有我的指标信息
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @GetMapping("v1/listSelf")
    public Result listSelf(PositionIndexSetDTO positionIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<PositionIndexSetVO> positionIndexSetVOList = BeanTransform.copyProperties(
                    positionIndexSetAPI.listSelf(positionIndexSetDTO), PositionIndexSetVO.class, request);
            return ActResult.initialize(positionIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 添加我的指标
     *
     * @param positionIndexSetTO 岗位指标基本信息数据to
     * @des 添加我的指标
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addSelf")
    public Result addSelf(@Validated(PositionIndexSetTO.TestAdd.class) PositionIndexSetTO positionIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            PositionIndexSetBO positionIndexSetBO1 = positionIndexSetAPI.addSelf(positionIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(positionIndexSetBO1,PositionIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑我的指标
     *
     * @param positionIndexSetTO 岗位指标基本信息数据bo
     * @des 编辑我的指标
     * @return  class PositionIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSelf")
    public Result editSelf(@Validated(PositionIndexSetTO.TestAdd.class) PositionIndexSetTO positionIndexSetTO) throws ActException {
        try {
            PositionIndexSetBO positionIndexSetBO1 = positionIndexSetAPI.editSelf(positionIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(positionIndexSetBO1,PositionIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除我的指标
     *
     * @param id id
     * @des 根据id删除岗位指标信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/deleteSelf/{id}")
    public Result deleteSelf(@PathVariable String id) throws ActException {
        try {
            positionIndexSetAPI.deleteSelf(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request id
     * @des 导入Excel
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<PositionIndexSetExcel> tos = ExcelUtil.excelToClazz(is, PositionIndexSetExcel.class, excel);
            List<PositionIndexSetTO> toList = BeanTransform.copyProperties(tos,PositionIndexSetTO.class);
            positionIndexSetAPI.leadExcel(toList);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param to 导出条件
     * @version v1
     */
    @GetMapping("v1/positionReport")
    public Result positionReport(ExportExcelPositTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "岗位指标.xlsx";
            super.writeOutFile(response, positionIndexSetAPI.positionReport(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板岗位指标
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "岗位指标导入模板.xlsx";
            super.writeOutFile(response, positionIndexSetAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }




}