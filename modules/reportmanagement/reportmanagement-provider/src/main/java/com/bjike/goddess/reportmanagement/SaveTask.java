package com.bjike.goddess.reportmanagement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.reportmanagement.service.AssetSer;
import com.bjike.goddess.reportmanagement.service.DebtSer;
import com.bjike.goddess.reportmanagement.service.assetTemp;
import com.bjike.goddess.user.api.UserLoginAPI;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.to.UserLoginTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: [董添添]
 * @Date: [2018-05-05 17:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.gitgeek]
 */

@Component
public class SaveTask {
    @Autowired
    UserLoginAPI userLoginAPI;
    @Autowired
    AssetSer assetSer;
    @Autowired
    DebtSer debtSer;

    private final Logger log = LoggerFactory.getLogger(SaveTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");

    @Scheduled(cron = "0 12 00 ? * SUN")
    public void reportInsertData() throws SerException {
        log.info("定时器启动..............." + dateFormat.format(new Date()));

//        UserLoginTO userLoginTO = new UserLoginTO();
//        userLoginTO.setAccount("admin");
//        userLoginTO.setPassword("abc123");
//        userLoginTO.setIp("127.0.0.1");
//        userLoginTO.setLoginType(LoginType.NONE);
//        String token = userLoginAPI.login(userLoginTO);

        log.info("向资产临时表中添加数据......" + dateFormat.format(new Date()));
       // RpcTransmit.transmitUserToken(token);
        assetSer.startTask();
        log.info("添加数据完成......" + dateFormat.format(new Date()));

    }
}

