package com.zsy.xuki.controller;

import com.zsy.xuki.common.*;
import com.zsy.xuki.entity.Log;
import com.zsy.xuki.entity.User;
import com.zsy.xuki.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping(value="/list",method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(@RequestBody Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("name", name);
        List<Log> logList = logService.selectAll(queryMap);
        Integer total = logService.getTotal(queryMap);
        if (!logList.isEmpty()) {
            return new PageResult(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, logList, total);
        }
        Log log = new Log();
        log.setContent( "get log list is empty query: offset(" + page.getOffset() + ") pageSize (" + page.getRows() + ") searchName ("+ name + ")");
        log.setCreattime(new Date());
        logService.insert(log);
        return new PageResult(false, StatusCode.EMPTY, MessageConstant.EMPTY, logList, total);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result editUser(@RequestBody Log log ) {
        if (log.getId() != null) {
            log.setCreattime(new Date());
            if (logService.edit(log) != 0) {
                return new Result().SUCCESS();
            } else {
                Log logl = new Log();
                logl.setContent("edit log is error query:" + log.getId() + log.getContent() + log.getCreattime());
                logl.setCreattime(new Date());
                logService.insert(logl);
            }
        } else if(log.getId() == null) {
            log.setCreattime(new Date());
            if (logService.insert(log) != 0) {
                return new Result().SUCCESS();
            } else {
                Log logl = new Log();
                logl.setContent("insert log is error query:" + log.getId() + log.getContent() + log.getCreattime());
                logl.setCreattime(new Date());
                logService.insert(logl);
            }
        }
        Log logl = new Log();
        logl.setContent("edit log is error query:" + log.getId() + log.getContent() + log.getCreattime());
        logl.setCreattime(new Date());
        logService.insert(logl);
        return new Result().ERROR();
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Result delete(@RequestParam String id) {
        if (id != null) {
            return new Result(true, StatusCode.OK, MessageConstant.SYSTEM_SUCCESS, logService.delete(Integer.valueOf(id)));
        }
        Log logl = new Log();
        logl.setContent("delete log is error query:" );
        logl.setCreattime(new Date());
        logService.insert(logl);
        return null;
    }
}
