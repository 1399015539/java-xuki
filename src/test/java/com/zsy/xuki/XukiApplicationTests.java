package com.zsy.xuki;

import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.service.LiteratureDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest

//让Junit运行spring的测试环境， 获得Spring上下文的支持
@RunWith(SpringRunner.class)
//获取上下文支持
@WebAppConfiguration
class XukiApplicationTests {

    @Test
    void contextLoads() {
    }

}
