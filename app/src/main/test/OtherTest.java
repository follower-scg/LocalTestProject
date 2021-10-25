package main.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/14 10:08
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class OtherTest {



    @Test
    public void test1(){

        int a = 1;
        float b = 223;
        long c = 012;
        byte d = 127;
//        System.out.println(a/b);

        Integer integer = Integer.valueOf(a);

        String aa = "1";
        //软引用（当内存空间不足的时候，就回收这些对象）
        SoftReference<String> stringSoftReference = new SoftReference<String>(aa);
        stringSoftReference.clear();
        System.out.println(stringSoftReference.get());
        //弱引用（不管当前的内存空间是否足够，都会回收他的内存，只要扫描到就回收）
        WeakReference<String> stringWeakReference = new WeakReference<>(aa);
        System.out.println(stringWeakReference.get());




    }

}
