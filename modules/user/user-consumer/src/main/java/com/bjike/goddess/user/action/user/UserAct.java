package com.bjike.goddess.user.action.user;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.attendance.api.VacateAPI;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.vo.EntryRegisterVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.user.api.ShareCodeAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.ShareCodeBO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.to.UserTO;
import com.bjike.goddess.user.vo.ShareCodeVO;
import com.bjike.goddess.user.vo.UserVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 用户操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
public class UserAct extends BaseFileAction {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private EventAPI eventAPI;
    @Autowired
    private VacateAPI vacateAPI;
    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private ShareCodeAPI shareCodeAPI;

    /**
     * 根据账号查询用户信息
     *
     * @param accountNumber 账号
     * @version v1
     */
    @GetMapping("v1/findByAccountNumber/{accountNumber}")
    public Result findByAccountNumber(@PathVariable String accountNumber) throws ActException {
        try {
            UserBO userBO = userAPI.findByAccountNumber(accountNumber);
            return ActResult.initialize(BeanTransform.copyProperties(userBO, UserVO.class));
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 手机号码是否存在
     *
     * @param phone 手机号码
     * @version v1
     */
    @GetMapping("v1/phone/{phone}/exists")
    public Result existPhone(@PathVariable String phone) throws ActException {
        try {
            Boolean result = (null != userAPI.findByPhone ( phone ));
            return ActResult.initialize ( result );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @version v1
     */
    @GetMapping("v1/username/{username}/exists")
    public Result existUsername(@PathVariable String username) throws ActException {
        try {
            Boolean result = (null != userAPI.findByUsername ( username ));
            return ActResult.initialize ( result );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 添加
     *
     * @param userTO 用户
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(UserTO userTO) throws ActException {
        try {
            Boolean result = (null != userAPI.add ( null, userTO ));
            return ActResult.initialize ( result );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }


    /**
     * 当前用户修改密码自己的密码
     *
     * @param userTO 用户
     * @version v1
     */
    @PostMapping("v1/update/pwd")
    public Result updatePassword(@Validated(UserTO.UPDATEPWD.class) UserTO userTO, BindingResult bindingResult) throws ActException {
        try {
            userAPI.updatePassword ( userTO );
            return new ActResult ( "修改密码成功！" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 当前用户修改手机号码
     *
     * @param userTO 用户
     * @version v1
     */
    @PostMapping("v1/update/phone")
    public Result updatePhone(@Validated(UserTO.UPDATEPHONE.class) UserTO userTO, BindingResult bindingResult) throws ActException {
        try {
            userAPI.updatePhone ( userTO );
            return new ActResult ( "修改手机号码成功！" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }


    /**
     * 上传用户头像前检查
     * tanghaixiang
     *
     * @param username 被修改头像的用户名
     * @desc 当前用户只能操作自己的头像, 用于上传头像和修改头像前校验
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/check/currentuser/{username}")
    public Result currentUserCheck(@PathVariable String username, HttpServletRequest request) throws ActException {
        try {
            String userToken = RpcContext.getContext ().getAttachment ( "userToken" );
            UserBO userBO = userAPI.currentUser ();
            if (StringUtils.isBlank ( username ) || !username.equals ( userBO.getUsername () )) {
                throw new ActException ( "操作失败，您只能操作自己的头像或密码" );
            } else {
                return new ActResult ( "checkSuccess" );
            }

        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }


    /**
     * 上传用户头像
     * tanghaixiang
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-头像图片
            String userToken = RpcContext.getContext ().getAttachment ( "userToken" );
            UserBO userBO = userAPI.currentUser ();
            if (null == userBO) {
                throw new ActException ( "该用户不存在" );
            }
            if (StringUtils.isNotBlank ( userBO.getHeadSculpture () ) && !"1".equals ( userBO.getHeadSculpture () )) {
                throw new ActException ( "该用户已经上传过头像，请进行修改操作" );
            }
            RpcContext.getContext ().setAttachment ( "userToken", userToken );
            String path = "/user/touxiang/" + userBO.getId ();
            List<InputStream> inputStreams = getInputStreams ( request, path );
            List<FileBO> list = fileAPI.upload ( inputStreams );
            RpcContext.getContext ().setAttachment ( "userToken", userToken );

            UserTO userTO = new UserTO ();
            BeanTransform.copyProperties ( userBO, userTO );
            userTO.setHeadSculpture ( list.get ( 0 ).getPath () );
            userAPI.update ( userTO );
            return new ActResult ( "upload success" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 修改用户头像
     * tanghaixiang
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/update/titlePic")
    public Result updateTitlePic(HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-头像图片
            Object storageToken = request.getAttribute ( "storageToken" );

            String userToken = RpcContext.getContext ().getAttachment ( "userToken" );
            UserBO userBO = userAPI.currentUser ();
            if (null == userBO) {
                throw new ActException ( "该用户不存在" );
            }

            RpcContext.getContext ().setAttachment ( "userToken", userToken );
            //旧的地址
            String oldPath = userBO.getHeadSculpture ();
            //新的地址
            String path = "/user/touxiang/" + userBO.getId ();
            //先删除
            fileAPI.delFile ( storageToken.toString (), new String[]{oldPath} );

            //重新上传
            RpcContext.getContext ().setAttachment ( "userToken", userToken );
            List<InputStream> inputStreams = getInputStreams ( request, path );
            List<FileBO> fileBOS = fileAPI.upload ( inputStreams );

            RpcContext.getContext ().setAttachment ( "userToken", userToken );
            UserTO userTO = new UserTO ();
            BeanTransform.copyProperties ( userBO, userTO );
            userTO.setHeadSculpture ( fileBOS.get ( 0 ).getPath () );
//            userTO.setHeadSculpture( "1" );
            userTO.setId ( userBO.getId () );
            userAPI.update ( userTO );


            return new ActResult ( "edit pic success" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 根据用户名获取头像
     * tanghaixiang
     *
     * @version v1
     */
    @GetMapping("v1/titlePic/{username}")
    public Result titlePic(@PathVariable String username, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-头像图片
            String userToken = request.getParameter ( "token" );
//                    RpcContext.getContext().getAttachment("userToken");
//            UserBO userBO = userAPI.currentUser();
            UserBO userBO = userAPI.findByUsername ( username );
            RpcContext.getContext ().setAttachment ( "userToken", userToken );
            if (null == userBO) {
                throw new ActException ( "该用户不存在" );
            }

            FileInfo fileInfo = new FileInfo ();
            Object storageToken = request.getAttribute ( "storageToken" );
            fileInfo.setStorageToken ( storageToken.toString () );
            fileInfo.setPath ( userBO.getHeadSculpture () );
            String filename = StringUtils.substringAfterLast ( fileInfo.getPath (), "/" );
            byte[] buffer = fileAPI.download ( fileInfo );
            try {
                writeOutFile ( response, buffer, filename );
            } catch (IOException e) {
                e.printStackTrace ();
            }
            return new ActResult ( "download success" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 待办事件总条数(phone)
     *
     * @param dto 事件数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/event/count")
    public Result count(FatherDTO dto) throws ActException {
        try {
            return ActResult.initialize ( eventAPI.count ( dto ) );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 请假总条数(phone)
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vacate/count")
    public Result vacate(VacateDTO dto) throws ActException {
        try {
            return ActResult.initialize ( vacateAPI.count ( dto ) );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 报销列表总条数(phone)
     *
     * @param reimburseRecordDTO 申请报销信息dto
     * @des 获取所有申请报销信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result counts(ReimburseRecordDTO reimburseRecordDTO) throws ActException {
        try {
            Long count = reimburseRecordAPI.countReimburseRecords ( reimburseRecordDTO );
            return ActResult.initialize ( count );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 成为企业(phone)
     *
     * @param userTO 用户
     * @version v1
     */
    @PostMapping("v1/phone/becomeEnterprise")
    public Result becomeEnterprise(@Validated(UserTO.BECOMEENTERPRISE.class) UserTO userTO, BindingResult bindingResult) throws ActException {
        try {
            userAPI.becomeEnterprise ( userTO );
            return new ActResult ( "成为企业成功！" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 根据姓名获取个人信息(phone)
     *
     * @param dto
     * @return class EntryRegisterVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findEntryRegisterByName")
    public Result findEntryRegisterByName(@Validated(UserDTO.NAME.class) UserDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<EntryRegisterBO> boList = entryRegisterAPI.getEntryRegisterByName ( dto.getUsername () );
            List<EntryRegisterVO> vo = BeanTransform.copyProperties ( boList, EntryRegisterVO.class );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }
    /**
     * 测试token是否过期
     *
     * @return class UserBO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/testToken")
    public Result testToken( ) throws ActException {
        try {
            UserBO userBO = userAPI.currentUser();

            return  ActResult.initialize(userBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据邀请码获取邀请人信息
     *
     * @param code
     * @return class ShareCodeVO
     * @version v1
     */
    @GetMapping("v1/getByCode")
    public Result getByCode(String code) throws ActException {
        try{
        ShareCodeBO shareCodeBO = shareCodeAPI.getByCode(code);
            return ActResult.initialize(BeanTransform.copyProperties(shareCodeBO, ShareCodeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




}