package com.zsy.xuki.controller;

import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.service.LiteratureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testLiterature")
public class LiteratureDataController {

    @Autowired
    private LiteratureDataService literatureService;

    @RequestMapping("/getLiteratureData")
    public LiteratureData getLiteratureData(@RequestParam(value = "id") String id) {
        LiteratureData literatureData = literatureService.selectById(Integer.valueOf(id));
        return literatureData;
    }

    @RequestMapping("/getAll")
    public LiteratureData getAll() {
        LiteratureData literatureData = literatureService.selectById(2);
        return literatureData;
    }
}
