package com.zsy.xuki;


import com.sun.tools.javac.Main;
import com.zsy.xuki.entity.LiteratureData;
import com.zsy.xuki.service.LiteratureDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestBody;

//获取启动类，加载配置，确定装载 Spring 程序的装载方法，它会去寻找  主配置启动类（@SpringBootApplication 注解）
@SpringBootTest(classes = Main.class)
//让Junit运行spring的测试环境， 获得Spring上下文的支持
@RunWith(SpringRunner.class)
//获取上下文支持
@WebAppConfiguration
@ComponentScan("com.zsy.xuki.service")
public class BaseTest {

    @Test
    public void test1() {
        System.out.println("test1");
    }

}