package com.bjike.goddess.fundcheck.action.fundcheck;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.fundcheck.api.OtherSpendAPI;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.bo.OtherSpendBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.dto.OtherSpendDTO;
import com.bjike.goddess.fundcheck.entity.OtherSpend;
import com.bjike.goddess.fundcheck.excel.OtherSpendExcel;
import com.bjike.goddess.fundcheck.excel.PayStockExcel;
import com.bjike.goddess.fundcheck.to.*;
import com.bjike.goddess.fundcheck.vo.BackVO;
import com.bjike.goddess.fundcheck.vo.OtherIncomeVO;
import com.bjike.goddess.fundcheck.vo.OtherSpendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他支出
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:57 ]
 * @Description: [ 其他支出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("otherspend")
public class OtherSpendAction extends BaseFileAction {
    @Autowired
    private OtherSpendAPI otherSpendAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = otherSpendAPI.guidePermission(guidePermissionTO);
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
     * 其他支出列表总条数
     *
     * @param otherSpendDTO 其他支出dto
     * @des 获取所有其他支出
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OtherSpendDTO otherSpendDTO) throws ActException {
        try {
            Long count = otherSpendAPI.count(otherSpendDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个其他支出
     *
     * @param id
     * @return class OtherSpendVO
     * @des 获取一个其他支出
     * @version v1
     */
    @GetMapping("v1/back/{id}")
    public Result back(@PathVariable String id) throws ActException {
        try {
            OtherSpendBO otherSpendBO = otherSpendAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(otherSpendBO, OtherSpendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 其他支出列表
     *
     * @param otherSpendDTO 其他支出dto
     * @return class OtherSpendVO
     * @des 获取所有其他支出
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OtherSpendDTO otherSpendDTO, HttpServletRequest request) throws ActException {
        try {
            List<OtherSpendVO> otherSpendVOS = BeanTransform.copyProperties(
                    otherSpendAPI.findListBack(otherSpendDTO), OtherSpendVO.class, request);
            return ActResult.initialize(otherSpendVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加其他支出
     *
     * @param otherSpendTO 其他支出to
     * @return class OtherSpendVO
     * @des 添加其他支出
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OtherSpendTO otherSpendTO, BindingResult bindingResult) throws ActException {
        try {
            OtherSpendBO otherSpendBO = otherSpendAPI.insert(otherSpendTO);
            return ActResult.initialize(BeanTransform.copyProperties(otherSpendBO, OtherSpendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑其他支出
     *
     * @param otherSpendTO 其他支出数据to
     * @return class OtherSpendVO
     * @des 编辑其他支出
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OtherSpendTO otherSpendTO, BindingResult bindingResult) throws ActException {
        try {
            OtherSpendBO otherSpendBO = otherSpendAPI.edit(otherSpendTO);
            return ActResult.initialize(BeanTransform.copyProperties(otherSpendBO, OtherSpendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除其他支出
     *
     * @param id 用户id
     * @des 根据用户id删除其他支出记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            otherSpendAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param to 其他支出数据to
     * @des 汇总其他支出
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated OtherSpendCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            LinkedHashMap<String,String> map = otherSpendAPI.collect(to);
            return ActResult.initialize(map);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有一级科目
     *
     * @des 获取所有一级科目
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result listFirstSubject() throws ActException {
        try {
            List<String> userList = otherSpendAPI.listFirstSubject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有二级科目
     *
     * @des 根据一级科目获取所有二级科目
     * @version v1
     */
    @GetMapping("v1/listSubByFirst")
    public Result listSubByFirst(@RequestParam String firstSub) throws ActException {
        try {
            List<String> userList = otherSpendAPI.listSubByFirst(firstSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有三级科目
     *
     * @des 根据一级二级科目获取所有三级科目
     * @version v1
     */
    @GetMapping("v1/listTubByFirst")
    public Result listTubByFirst(@RequestParam String firstSub, @RequestParam String secondSub) throws ActException {
        try {
            List<String> userList = otherSpendAPI.listTubByFirst(firstSub, secondSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<OtherSpendExcel> tos = ExcelUtil.excelToClazz(is, OtherSpendExcel.class, excel);
            List<OtherSpendTO> tocs = new ArrayList<>();
            for (OtherSpendExcel str : tos) {
                OtherSpendTO otherSpendTO = BeanTransform.copyProperties(str, OtherSpendTO.class,"date");
                otherSpendTO.setDate(String.valueOf(str.getDate()));
                tocs.add(otherSpendTO);
            }
            //注意序列化
            otherSpendAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * excel模板下载
     *
     * @des 下载模板其他支出
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "其他支出导入模板.xlsx";
            super.writeOutFile(response, otherSpendAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}