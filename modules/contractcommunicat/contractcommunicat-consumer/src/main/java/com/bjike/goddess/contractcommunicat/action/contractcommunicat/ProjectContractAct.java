package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.api.ProjectContractAPI;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectContractExcel;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ExportExcelTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import com.bjike.goddess.contractcommunicat.vo.ProjectContractColelctVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectContractVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.919 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contract")
public class ProjectContractAct extends BaseFileAction {

    @Autowired
    private ProjectContractAPI projectContractAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 根据id查询项目承包洽谈
     *
     * @param id 项目承包洽谈id
     * @return class ProjectContractVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.findById(id), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectContractDTO dto) throws ActException {
        try {
            Long count = projectContractAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectContractVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectContractTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.saveProjectContract(to), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectContractVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectContractTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.editProjectContract(to), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目承包洽谈
     *
     * @param id 项目承包洽谈ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectContractAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            String path = "/contract";
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult();
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
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ProjectContractExcel> toList = ExcelUtil.excelToClazz(is, ProjectContractExcel.class, excel);
            List<ProjectContractTO> tos = BeanTransform.copyProperties(toList,ProjectContractTO.class);
            projectContractAPI.leadExcel(tos);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        ProjectContractExcel to = new ProjectContractExcel();
        to.setProjectResult(CommunicateResult.ABANDON);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(Arrays.asList(to), excel);
        File out = new File("/home/ike/out.xlsx");
        FileOutputStream fos = null;
        fos = new FileOutputStream(out);
        fos.write(bytes);

    }

    /**
     * 导出Excel
     *
     * @param to 导出条件
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(ExportExcelTO to) throws ActException {
        try {
            projectContractAPI.exportExcel(to);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 模糊查询条件
     * @return class ProjectContractVO
     * @version v1
     */
    @PostMapping("v1/list")
    public Result pageList(ProjectContractDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProjectContractVO> vo = BeanTransform.copyProperties(projectContractAPI.pageList(dto), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param to 模糊查询条件
     * @return class ProjectContractColelctVO
     * @version v1
     */
    @PostMapping("v1/collect")
    public Result collect(CollectConditionTO to, HttpServletRequest request) throws ActException {

        try {
            List<ProjectContractColelctVO> vo = BeanTransform.copyProperties(projectContractAPI.collect(to), ProjectContractColelctVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置汇总周期
     *
     * @param cycleType 周期类型
     * @version v1
     */
    @GetMapping("cycle")
    public Result setCollectSend(QuartzCycleType cycleType) throws ActException {

        try {
            projectContractAPI.setCollectSend(cycleType);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}