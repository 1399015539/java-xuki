package com.zsy.xuki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Main")
public class MainController {

    @RequestMapping("/Hello")
    public String hello() {
        return "hello";
    }
}
