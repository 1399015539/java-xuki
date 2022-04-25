package com.zsy.xuki;

import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.entity.Log;
import com.zsy.xuki.entity.Store;
import com.zsy.xuki.entity.User;
import com.zsy.xuki.service.StoreService;
import com.zsy.xuki.service.LiteratureDataService;
import com.zsy.xuki.service.LogService;
import com.zsy.xuki.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

@SpringBootTest

//让Junit运行spring的测试环境， 获得Spring上下文的支持
@RunWith(SpringRunner.class)
//获取上下文支持
@WebAppConfiguration
public class LiteratureTest {
    @Autowired
    private LiteratureDataService literatureService;
    @Autowired
    private UserService userService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private LogService logService;

    @Test
    public void  GetLiterature() {
        LiteratureData literatureData = literatureService.selectById(2);
        System.out.println(literatureData.getAuthor());
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("admin1");
        user.setPassword("admin");
        user.setRole((short) 1);
        int flag = userService.insert(user);
        System.out.println(flag);
    }
    @Test
    public void editUser() {
        User user = new User();
        user.setId(1);
        user.setName("admin");
        user.setPassword("123");
        user.setRole((short) 1);
        int flag = userService.edit(user);
        System.out.println(flag);
    }
    @Test
    public void deleteUser() {
        int flag = userService.delete(2);
        System.out.println(flag);
    }
    @Test
    public void selectUser() {
        User user = userService.selectById(1);
        System.out.println(user.getName());
    }

    @Test
    public void addLog() {
        Log log = new Log();
        User user = userService.selectById(15);
        log.setContent(user.toString());
        Date date = new Date();
        log.setCreattime(date);
        int flag = logService.insert(log);
        System.out.println(flag);
    }

    @Test
    public void addStore() {
        Store store = new Store();
        store.setLiteratureId(1);
        store.setUserId(1);
        int flag = storeService.insert(store);
        System.out.println(flag);
    }

    @Test
    public void selectAllUser() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", 5);
        queryMap.put("pageSize", 5);
        List<User> userList = userService.selectAll(queryMap);
        for (User user : userList) {
            System.out.println(user.getName());
        }
        queryMap.put("name", "a");
        userList = userService.selectAll(queryMap);
        for (User user : userList) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void getTotal() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 10);
        queryMap.put("sortDownNum", 1);
        List<LiteratureData> data = literatureService.selectAll(queryMap);
        for (LiteratureData dat : data) {
            System.out.println(dat.getDownNum());
        }

    }
}
