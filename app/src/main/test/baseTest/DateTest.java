package main.test.baseTest;

import java.util.Date;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/8/4 14:43
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class DateTest {

    public static void main(String[] args) {

        Date date = new Date();
        long start = System.currentTimeMillis();
        long count = 0;
        for (int i = 0; i<10000000;i++){
            count += i;
        }
        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        System.out.println(count);
        Date date1 = new Date();
//        System.out.println(date.toString());
//        System.out.println(date1.toString());
//        System.out.println(date.getTime());
//        System.out.println(date1.getTime());
//        System.out.println(date1.getTime() - date.getTime());
    }
}
