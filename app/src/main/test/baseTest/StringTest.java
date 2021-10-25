package main.test.baseTest;

import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/1 16:58
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class StringTest {

    public static void main(String[] args) {
        //字符串特殊符号切割需要使用转义符号
        String s ="123.4567";
        System.out.println(s.hashCode());
//        System.out.println(s.substring(1));
//        System.out.println(s.length());
        String[] split = s.split("\\.");
//        for (String s1 : split) {
//            System.out.println(s1);
//        }

        String sss= "123.465.789.0";
//        System.out.println(sss.substring(0, sss.lastIndexOf(".")));
        String ss ="123/456/789";
        String[] split1 = ss.split("/");
        for (String s1 : split1) {
//            System.out.println(s1);

        }
        Long l = 0x8000000000000000L;
//        System.out.println(l.intValue());
        Long ll = 0x7fffffffffffffffL;
//        System.out.println(ll.intValue());
    }

    @Test
    public void stringBufferAndStringBuilder(){
        //StringBuffer线程安全，效率低，适合多线程
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("123");
        System.out.println(stringBuffer);

        //字符串反转
        System.out.println(stringBuffer.reverse());

        //StringBuilder线程不安全，效率高，适合单线程
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("456");
        System.out.println(stringBuilder);



    }
}
