package com.bjike.goddess.foreigntax.action.foreigntax;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.TaxManagementAPI;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.bo.VoucherDataBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.to.ForeignTaxDeleteFileTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import com.bjike.goddess.foreigntax.to.VoucherDataTO;
import com.bjike.goddess.foreigntax.vo.TaxManagementVO;
import com.bjike.goddess.foreigntax.vo.VoucherDataVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 税金管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("taxmanagement")
public class TaxManagementAction extends BaseFileAction {
    @Autowired
    private TaxManagementAPI taxManagementAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = taxManagementAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = taxManagementAPI.guidePermission(guidePermissionTO);
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
     * 税金管理列表总条数
     *
     * @param dto 税金管理dto
     * @des 获取所有税金管理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TaxManagementDTO dto) throws ActException {
        try {
            Long count = taxManagementAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个税金管理
     *
     * @param id
     * @return class TaxManagementVO
     * @des 获取一个税金管理
     * @version v1
     */
    @GetMapping("v1/tax/{id}")
    public Result tax(@PathVariable String id) throws ActException {
        try {
            TaxManagementBO taxManagementBO = taxManagementAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(taxManagementBO, TaxManagementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 税金管理列表
     *
     * @param dto 税金管理dto
     * @return class TaxManagementVO
     * @des 获取所有税金管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TaxManagementDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaxManagementVO> taxManagementVOS = BeanTransform.copyProperties
                    (taxManagementAPI.list(dto), TaxManagementVO.class, request);
            return ActResult.initialize(taxManagementVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加税金管理
     *
     * @param to 税金管理数据to
     * @return class TaxManagementVO
     * @des 添加税金管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TaxManagementTO to, BindingResult bindingResult) throws ActException {
        try {
            TaxManagementBO taxManagementBO = taxManagementAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(taxManagementBO, TaxManagementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑税金管理
     *
     * @param to 税金管理数据to
     * @return class TaxManagementVO
     * @des 编辑税金管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TaxManagementTO to, BindingResult bindingResult) throws ActException {
        try {
            TaxManagementBO taxManagementBO = taxManagementAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(taxManagementBO, TaxManagementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除税金管理
     *
     * @param id 用户id
     * @des 根据用户id删除税金管理记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result remove(@PathVariable String id) throws ActException {
        try {
            taxManagementAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据税款所属期止获得申报期限
     *
     * @param taxEnd
     * @version v1
     */
    @GetMapping("v1/getDead")
    public Result getDead(String taxEnd) throws ActException {
        try {
            Map<String, String> map = taxManagementAPI.getDead(taxEnd);
            return ActResult.initialize(map);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有公司
     *
     * @version v1
     */
    @GetMapping("v1/company")
    public Result company() throws ActException {
        try {
            List<String> companyList = taxManagementAPI.getCompany();
            return ActResult.initialize(companyList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有税种
     *
     * @version v1
     */
    @GetMapping("v1/taxType")
    public Result taxType() throws ActException {
        try {
            List<String> taxTypeList = taxManagementAPI.getTaxType();
            return ActResult.initialize(taxTypeList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param dto
     * @return class TaxManagementVO
     * @des 根据公司税种时间汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(TaxManagementDTO dto) throws ActException {
        try {
            List<TaxManagementVO> taxManagementVOS = BeanTransform.copyProperties(
                    taxManagementAPI.collect(dto), TaxManagementVO.class);
            return ActResult.initialize(taxManagementVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 生成记账凭证
     *
     * @param ids 列表id数组
     * @return class VoucherDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vGenerate")
    public Result vGenerate(String[] ids) throws ActException {
        try {

            VoucherDataBO voucherDataBO = taxManagementAPI.vGenerate(ids);
            return ActResult.initialize(BeanTransform.copyProperties(voucherDataBO, VoucherDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 提交记账凭证
     *
     * @param to to数据
     * @return class VoucherDataVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/voucher")
    public Result voucher(@Validated() VoucherDataTO to) throws ActException {
        try {

            VoucherDataBO voucherDataBO = taxManagementAPI.generate(to);
            return ActResult.initialize(BeanTransform.copyProperties(voucherDataBO, VoucherDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @des 税金管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 税金管理id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /foreigntax/id/....
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            String token = RpcContext.getContext().getAttachment("storageToken");
            fileInfo.setStorageToken(token);
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {


            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param foreignTaxDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ForeignTaxDeleteFileTO.TestDEL.class) ForeignTaxDeleteFileTO foreignTaxDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != foreignTaxDeleteFileTO.getPaths() && foreignTaxDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), foreignTaxDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}