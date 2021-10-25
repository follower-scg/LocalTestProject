package main.test;

import org.apache.velocity.anakia.NodeList;
import org.junit.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/9 13:36
 * @description： 单元测试
 */
public class UnitTest {

    //全局只会执行一次，而且是第一个运行
    @BeforeClass
    public static void beforeClassMethod(){
        System.out.println("————————beforeClass————————");
    }

    @Before
    public void beforeMethod(){
        System.out.println("————————before————————");
    }

    @After
    public void afterMethod(){
        System.out.println("————————after————————");    }

//    全局只会执行一次，而且是最后一个运行
    @AfterClass
    public static void afterClassMethod(){
        System.out.println("————————afterClass————————");
    }

    @Test
    public void testMain(){
        System.out.println("————————testMain————————");
    }

    @Test
    public void testMain2(){
        System.out.println("————————testMain2————————");
    }

    public static void main(String[] args) {

        List<Object> arrayList = new LinkedList<>();
    }
}


