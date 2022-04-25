package com.zsy.xuki.controller;

import com.zsy.xuki.common.*;
import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.entity.Log;
import com.zsy.xuki.service.LiteratureDataService;
import com.zsy.xuki.service.LogService;
import com.zsy.xuki.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/literature")
public class LiteratureController {
    @Autowired
    private LiteratureDataService literatureService;

    @Autowired
    private LogService logService;

    @Autowired
    private StoreService storeService;

    @RequestMapping(value="/list",method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(@RequestBody Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("name", name);
        List<LiteratureData> literatureDataList = literatureService.selectAll(queryMap);
        Integer total = literatureService.getTotal(queryMap);
        if (!literatureDataList.isEmpty()) {
            for (LiteratureData literature : literatureDataList) {
                Map<String, Object> qMap = new HashMap<>();
                qMap.put("literatureId", literature.getId());
                literature.setStoreNum(storeService.getTotal(qMap));
            }
            return new PageResult(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, literatureDataList, total);
        }
        Log log = new Log();
        log.setContent( "get literature list is empty query: offset(" + page.getOffset() + ") pageSize (" + page.getRows() + ") searchName ("+ name + ")");
        log.setCreattime(new Date());
        logService.insert(log);
        return new PageResult(false, StatusCode.EMPTY, MessageConstant.EMPTY, literatureDataList, total);
    }

    @RequestMapping(value="/downTopList",method = RequestMethod.POST)
    @ResponseBody
    public PageResult downTopList(@RequestBody Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("sortDownNum", 1);
        queryMap.put("name", name);
        List<LiteratureData> literatureDataList = literatureService.selectAll(queryMap);
        Integer total = literatureService.getTotal(queryMap);
        if (!literatureDataList.isEmpty()) {
            for (LiteratureData literature : literatureDataList) {
                Map<String, Object> qMap = new HashMap<>();
                qMap.put("literatureId", literature.getId());
                literature.setStoreNum(storeService.getTotal(qMap));
            }
            return new PageResult(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, literatureDataList, total);
        }
        Log log = new Log();
        log.setContent( "get literature list is empty query: offset(" + page.getOffset() + ") pageSize (" + page.getRows() + ") searchName ("+ name + ")");
        log.setCreattime(new Date());
        logService.insert(log);
        return new PageResult(false, StatusCode.EMPTY, MessageConstant.EMPTY, literatureDataList, total);
    }

    @RequestMapping(value="/toreNumTop",method = RequestMethod.POST)
    @ResponseBody
    public PageResult toreNumTop(@RequestBody Page page, @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", name);
        List<LiteratureData> literatureDataList = literatureService.selectAll(queryMap);
        Integer total = literatureService.getTotal(queryMap);
        if (!literatureDataList.isEmpty()) {
            for (LiteratureData literature : literatureDataList) {
                Map<String, Object> qMap = new HashMap<>();
                qMap.put("literatureId", literature.getId());
                literature.setStoreNum(storeService.getTotal(qMap));
            }
            literatureDataList.sort(Comparator.comparing(LiteratureData::getStoreNum).reversed());
            if (literatureDataList.size() > 10) {
                literatureDataList = literatureDataList.subList(0, 10);
            }
            return new PageResult(true, StatusCode.OK, MessageConstant.USER_LOGIN_SUCCESS, literatureDataList, total);
        }
        Log log = new Log();
        log.setContent( "get literature list is empty query: offset(" + page.getOffset() + ") pageSize (" + page.getRows() + ") searchName ("+ name + ")");
        log.setCreattime(new Date());
        logService.insert(log);
        return new PageResult(false, StatusCode.EMPTY, MessageConstant.EMPTY, literatureDataList, total);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result edit(@RequestBody LiteratureData literatureData ) {
        if (literatureData.getId() != null) {
            if (literatureService.edit(literatureData) != 0) {
                return new Result().SUCCESS();
            } else {
                Log log = new Log();
                log.setContent("edit LiteratureData is error !!!");
                log.setCreattime(new Date());
                logService.insert(log);
            }
        } else if(literatureData.getId() == null) {
            if (literatureService.insert(literatureData) != 0) {
                return new Result().SUCCESS();
            } else {
                Log log = new Log();
                log.setContent("insert LiteratureData is error !!!");
                log.setCreattime(new Date());
                logService.insert(log);
            }
        }
        return new Result().ERROR();
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Result delete(@RequestParam String id) {
        if (id != null) {
            try {
                return new Result(true, StatusCode.OK, MessageConstant.SYSTEM_SUCCESS, literatureService.delete(Integer.valueOf(id)));
            } catch (Exception e) {
                Log log = new Log();
                log.setContent("delete LiteratureData is error !!! Exception:" + e);
                log.setCreattime(new Date());
                logService.insert(log);
            }
        }
        return null;
    }

    @RequestMapping(value="/selectById",method=RequestMethod.POST)
    public Result selectById(@RequestParam String id) {
        if (id != null) {
            try {
                LiteratureData literatureData =literatureService.selectById(Integer.valueOf(id));
                Map<String, Object> qMap = new HashMap<>();
                qMap.put("literatureId", literatureData.getId());
                literatureData.setStoreNum(storeService.getTotal(qMap));
                return new Result(true, StatusCode.OK, MessageConstant.SYSTEM_SUCCESS, literatureData);
            } catch (Exception e) {
                Log log = new Log();
                log.setContent("select LiteratureData is error !!! Exception:" + e);
                log.setCreattime(new Date());
                logService.insert(log);
            }
        }
        return null;
    }

    @RequestMapping(value="/pdf",method=RequestMethod.GET)
    public FileInputStream pdfJs() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("D:\\studytool\\xuki_data\\“双碳”背景下能源行业的机遇和挑战.pdf");
        return inputStream;
    }


}
