package com.bjike.goddess.user.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.entity.Role;
import com.bjike.goddess.user.service.RoleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: [用户操作]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user/role")
public class RoleAct {

    @Autowired
    private RoleAPI roleAPI;

    @LoginAuth
    @GetMapping("list")
    public ActResult list() throws ActException {
        try {
            List<Role> roles = roleAPI.findAll();
            return ActResult.initialize(roles);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}