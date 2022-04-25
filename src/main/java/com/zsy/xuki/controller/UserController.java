package com.zsy.xuki.controller;

import com.zsy.xuki.common.*;
import com.zsy.xuki.entity.Log;
import com.zsy.xuki.entity.User;
import com.zsy.xuki.service.LogService;
import com.zsy.xuki.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @RequestMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password) {
        User user = userService.selectByName(username);
        if (password.equals(user.getPassword())) {
            Log log = new Log();
            log.setContent(username + ": is login system");
            log.setCreattime(new Date());
            logService.insert(log);
            return new Result(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS);
        }
        else
            return new Result(false, StatusCode.LOGINERROR, MessageConstant.USER_LOGIN_ERROR);
    }

    @RequestMapping(value="/list",method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(@RequestBody Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        List<User> userList = new ArrayList<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("name", name);
        userList = userService.selectAll(queryMap);
        Integer total = userService.getTotal(queryMap);
        if (!userList.isEmpty()) {
            return new PageResult(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, userList, total);
        }
        Log log = new Log();
        log.setContent( "get user list is empty query: offset(" + page.getOffset() + ") pageSize (" + page.getRows() + ") searchName ("+ name + ")");
        log.setCreattime(new Date());
        logService.insert(log);
        return new PageResult(false, StatusCode.EMPTY, MessageConstant.EMPTY, userList, total);
    }

    @RequestMapping("/search")
    public Result search(@RequestParam String name) {
        List<User> userList = new ArrayList<>();
        userList = userService.likeSearch(name);
        if (!userList.isEmpty()) {
            return new Result(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, userList);
        }
        return null;
    }


    @RequestMapping("/nameList")
    public Result nameList() {
        List<User> userList = new ArrayList<>();
        Map<String, Object> queryMap = new HashMap<>();
        userList = userService.selectAll(queryMap);
        if (!userList.isEmpty()) {
            List<String> nameList = userList.stream().map(User::getName).collect(Collectors.toList());
            return new Result(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, nameList);
        }
        return null;
    }

    @RequestMapping("/userInfo")
    public Result userInfo(@RequestParam String username) {
        if (username != null) {
            return new Result(true, StatusCode.OK, MessageConstant.SYSTEM_SUCCESS, userService.selectByName(username));
        }
        return null;
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result editUser(@RequestBody User user ) {
        if (user.getId() != null) {
            if (userService.edit(user) != 0) {
                return new Result().SUCCESS();
            } else {
                Log log = new Log();
                log.setContent( "edit user is error queryUser: " + user.toString());
                log.setCreattime(new Date());
                logService.insert(log);
            }
        } else if(user.getId() == null) {
            if (userService.insert(user) != 0) {
                return new Result().SUCCESS();
            } else {
                Log log = new Log();
                log.setContent( "insert user is error queryUser: " + user.toString());
                log.setCreattime(new Date());
                logService.insert(log);
            }
        }
        return new Result().ERROR();
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Result delete(@RequestParam String id) {
        if (id != null) {
            return new Result(true, StatusCode.OK, MessageConstant.SYSTEM_SUCCESS, userService.delete(Integer.valueOf(id)));
        }
        return null;
    }

}
