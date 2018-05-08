package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.recruit.api.WorkOGAPI;
import com.bjike.goddess.recruit.entity.WorkOG;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 工作对赌
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 02:33 ]
 * @Description: [ 工作对赌 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("workog")
public class WorkOGAction {


    @Autowired
    private WorkOGAPI workOGAPI;

    @Autowired
    private MyWebSocket myWebSocket;

    @GetMapping("v1/get")
    public Result getWork(String id) throws SerException {
        return new ActResult("success", workOGAPI.getWorkOG(id));
    }

    @GetMapping("test")
    public ModelAndView test() {

        return new ModelAndView("hello");

    }

    @GetMapping("v1/list")
    public Result getList() {

        return new ActResult();
    }

    @PutMapping("v1/update")
    public Result upd() {

        return new ActResult();
    }

    @DeleteMapping("v1/del")
    public Result del() {

        return new ActResult();
    }

    @PostMapping("v1/add")
    public Result add(String data) throws IOException, SerException {
        workOGAPI.addWork(WanyJackson.superman(data, WorkOG.class));
        return new ActResult();
    }

    //---------------

    /**
     * 对赌消息
     */
    @PostMapping("v1/workog")
    public Result work(String data) throws IOException, SerException {
//        System.out.println("action" + data);
        if (data != null) {
//            System.out.println("ha");
            myWebSocket.dataMsg(data);
        }
        return new ActResult("success", data);
    }

    /**
     * 智能消息
     *
     * @param name
     * @return
     * @throws SerException
     */
    @GetMapping("v1/msglist")
    public Result getList(String name) throws SerException {
        return new ActResult("success", workOGAPI.getRemind(name));
    }

    //对赌消息
    @GetMapping("v1/remid")
    public Result getRemid(String name) throws SerException {
        return new ActResult("success", workOGAPI.getWorkMsg(name));
    }

    @GetMapping("v1/wo")
    public Result getWo(String name) throws SerException {
        return new ActResult("success", workOGAPI.getWorkScore(name));
    }

    @PostMapping("v1/send")
    public void sendMsg(String msg, String user) throws IOException {
        myWebSocket.sendMsg(msg, user);
//        System.out.println(msg + "---" + user);
    }

    @PostMapping("v1/getOnline")
    public String getMap() throws JsonProcessingException {
        System.out.println(MyWebSocket.onlineMap);
        Iterator<Map.Entry<String, Session>> iterator = MyWebSocket.onlineMap.entrySet().iterator();
        List<String> list = new ArrayList<String>();
        while (iterator.hasNext()) {
            list.add(iterator.next().getKey());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String map = objectMapper.writeValueAsString(list);
        System.out.println(map);
        return map;
    }
}