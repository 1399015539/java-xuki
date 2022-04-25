package com.zsy.xuki.controller;

import com.zsy.xuki.common.*;
import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.entity.Log;
import com.zsy.xuki.entity.Store;
import com.zsy.xuki.service.LogService;
import com.zsy.xuki.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private LogService logService;

    @RequestMapping(value="/list",method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(@RequestBody Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("name", name);
        List<Store> storeList = storeService.selectAll(queryMap);
        Integer total = storeService.getTotal(queryMap);
        if (!storeList.isEmpty()) {
            return new PageResult(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, storeList, total);
        }
        Log log = new Log();
        log.setContent( "get store list is empty query: offset(" + page.getOffset() + ") pageSize (" + page.getRows() + ") searchName ("+ name + ")");
        log.setCreattime(new Date());
        logService.insert(log);
        return new PageResult(false, StatusCode.EMPTY, MessageConstant.EMPTY, storeList, total);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result edit(@RequestBody Store store ) {
        if (store.getId() != null) {
            if (storeService.edit(store) != 0) {
                return new Result().SUCCESS();
            }
        } else if(store.getId() == null) {
            if (storeService.insert(store) != 0) {
                return new Result().SUCCESS();
            }
        }
        return new Result().ERROR();
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Result delete(@RequestParam String id) {
        if (id != null) {
            try {
                return new Result(true, StatusCode.OK, MessageConstant.SYSTEM_SUCCESS, storeService.delete(Integer.valueOf(id)));
            } catch (Exception e) {
                Log log = new Log();
                log.setContent("delete store is error !!! Exception:" + e);
                log.setCreattime(new Date());
                logService.insert(log);
            }
        }
        return null;
    }
}
