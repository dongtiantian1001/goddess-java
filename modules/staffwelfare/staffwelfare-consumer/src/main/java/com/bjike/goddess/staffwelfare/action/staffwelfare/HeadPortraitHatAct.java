package com.bjike.goddess.staffwelfare.action.staffwelfare;

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
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.staffwelfare.api.HeadPortraitHatAPI;
import com.bjike.goddess.staffwelfare.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfare.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfare.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.HeadPortraitHatTO;
import com.bjike.goddess.staffwelfare.vo.HeadPortraitHatVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头像帽
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("headportraithat")
public class HeadPortraitHatAct extends BaseFileAction{

    @Autowired
    private HeadPortraitHatAPI headPortraitHatAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = headPortraitHatAPI.sonPermission();
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

            Boolean isHasPermission = headPortraitHatAPI.guidePermission(guidePermissionTO);
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
     * 新增头像帽
     *
     * @param to 祝福语
     * @return class HeadPortraitHatVO
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) HeadPortraitHatTO to, HttpServletRequest request, BindingResult bindingResult) throws ActException {
        try {
            HeadPortraitHatBO bo = headPortraitHatAPI.addModel(to);
            HeadPortraitHatVO vo = BeanTransform.copyProperties(bo, HeadPortraitHatVO.class);
            String path = "/staffwelfare/headportraithat/" + vo.getId();
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑头像帽
     *
     * @param to 祝福语
     * @return class HeadPortraitHatVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) HeadPortraitHatTO to, BindingResult bindingResult) throws ActException {
        try {
            HeadPortraitHatVO vo = BeanTransform.copyProperties(headPortraitHatAPI.editModel(to), HeadPortraitHatVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除头像帽
     *
     * @param id 祝福语id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String[] path = new String[]{"/staffwelfare/headportraithat/"+id};
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),path);
            headPortraitHatAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 头像帽分页查询
     *
     * @param dto 分页条件
     * @return class HeadPortraitHatVO
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(HeadPortraitHatDTO dto) throws ActException {
        try {
            List<HeadPortraitHatVO> voList = BeanTransform.copyProperties(headPortraitHatAPI.pageList(dto), HeadPortraitHatVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //上传文件
    private List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {
        if (null != request && !ServletFileUpload.isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("file");
    }

    /**
     * 获取列表总条数
     * @param dto 列表查询条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HeadPortraitHatDTO dto) throws ActException{
        try {
            Long count = headPortraitHatAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id来查询单条数据
     * @param id 查询条件id
     * @return class HeadPortraitHatVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            HeadPortraitHatBO bo = headPortraitHatAPI.findOne(id);
            HeadPortraitHatVO vo = BeanTransform.copyProperties(bo,HeadPortraitHatVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
}