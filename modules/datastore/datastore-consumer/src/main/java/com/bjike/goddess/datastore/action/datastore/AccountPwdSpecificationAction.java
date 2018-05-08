package com.bjike.goddess.datastore.action.datastore;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.api.AccountPwdSpecificationAPI;
import com.bjike.goddess.datastore.bo.AccountPwdSpecificationBO;
import com.bjike.goddess.datastore.dto.AccountPwdSpecificationDTO;
import com.bjike.goddess.datastore.to.AccountPwdSpecificationTO;
import com.bjike.goddess.datastore.to.DataStoreDeleteFileTO;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import com.bjike.goddess.datastore.vo.AccountPwdSpecificationVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 数据存储账号密码规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储账号密码规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accountpwdspecification")
public class AccountPwdSpecificationAction extends BaseFileAction{
    @Autowired
    private AccountPwdSpecificationAPI accountPwdSpecificationAPI;

    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = accountPwdSpecificationAPI.guidePermission(guidePermissionTO);
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
     * 数据存储账号密码规范列表总条数
     *
     * @param accountPwdSpecificationDTO 数据存储账号密码规范dto
     * @des 获取所有数据存储账号密码规范总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws ActException {
        try {
            Long count = accountPwdSpecificationAPI.countAccountPwdSpecification(accountPwdSpecificationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个数据存储账号密码规范
     *
     * @param id
     * @return class AccountPwdSpecificationVO
     * @des 获取一个数据存储账号密码规范
     * @version v1
     */
    @GetMapping("v1/account/{id}")
    public Result account(@PathVariable String id) throws ActException {
        try {
            AccountPwdSpecificationBO accountPwdSpecificationBO = accountPwdSpecificationAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(accountPwdSpecificationBO, AccountPwdSpecificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据存储账号密码规范列表
     *
     * @param accountPwdSpecificationDTO 数据存储账号密码规范dto
     * @return class AccountPwdSpecificationVO
     * @des 获取所有数据存储账号密码规范
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AccountPwdSpecificationDTO accountPwdSpecificationDTO, HttpServletRequest request) throws ActException {
        try {
            List<AccountPwdSpecificationVO> accountPwdSpecificationVOS = BeanTransform.copyProperties
                    (accountPwdSpecificationAPI.findListAccountPwdSpecification(accountPwdSpecificationDTO), AccountPwdSpecificationVO.class, request);
            return ActResult.initialize(accountPwdSpecificationVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加数据存储账号密码规范
     *
     * @param accountPwdSpecificationTO 数据存储账号密码规范数据to
     * @return class AccountPwdSpecificationVO
     * @des 添加数据存储账号密码规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(AccountPwdSpecificationTO accountPwdSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            AccountPwdSpecificationBO accountPwdSpecificationBO = accountPwdSpecificationAPI.insertAccountPwdSpecification(accountPwdSpecificationTO);
            return ActResult.initialize(accountPwdSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑数据存储账号密码规范
     *
     * @param accountPwdSpecificationTO 数据存储账号密码规范数据to
     * @return class AccountPwdSpecificationVO
     * @des 编辑数据存储账号密码规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(AccountPwdSpecificationTO accountPwdSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            AccountPwdSpecificationBO accountPwdSpecificationBO = accountPwdSpecificationAPI.editAccountPwdSpecification(accountPwdSpecificationTO);
            return ActResult.initialize(accountPwdSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除数据存储账号密码规范
     *
     * @param id 用户id
     * @des 根据用户id删除数据存储账号密码规范记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            accountPwdSpecificationAPI.removeAccountPwdSpecification(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @des 数据存储账号密码规范记录
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
     * @param id 数据存储账号密码规范记录id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /bidding/id/....
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
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
     * @param dataStoreDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DataStoreDeleteFileTO.TestDEL.class) DataStoreDeleteFileTO dataStoreDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != dataStoreDeleteFileTO.getPaths() && dataStoreDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),dataStoreDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


}