package main.test.baseTest;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/16 9:58
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class BigDecimalTest {

    @Test
    public void createTest(){

        //创建
        // 不推荐使用new 关键字创建Bigdecimal对象。原因是当new 的值是一个小数的时候，其真实的值并不是我们想要的值
        //2中方式： 1.通过字符串转换 new 形式
        //         2.通过整形或者小数转换： valueOf
        BigDecimal bigDecimal = new BigDecimal("1.1");
        BigDecimal bigDecimal1 = new BigDecimal("3");
        BigDecimal bigDecimal3 = new BigDecimal(1.1);
//        System.out.println(bigDecimal3);

        BigDecimal bigDecimal4 = BigDecimal.valueOf(1.1);
//        System.out.println(bigDecimal4);
        //设置小数位数 舎入模式最常用形式：四舍五入 BigDecimal.ROUND_HALF_UP 或 RoundingMode.HALF_UP 等同于数值4
        BigDecimal bigDecimal2 = bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal bigDecimal5 = bigDecimal.setScale(4, RoundingMode.HALF_UP);

        //在做加减乘除时，建议设置舎入模式 new MathContext(保留位数)
        //减法
        System.out.println(bigDecimal.subtract(bigDecimal1,new MathContext(2)));

        //乘法
        System.out.println(bigDecimal.multiply(bigDecimal2,new MathContext(2)));

        //加法
        System.out.println(bigDecimal.add(bigDecimal2,new MathContext(2)));

        //除法 ArithmeticException算术异常，要排除分母为0
        if (!bigDecimal2.equals(BigDecimal.ZERO)) {
            System.out.println(bigDecimal.divide(bigDecimal2,4,BigDecimal.ROUND_HALF_UP));
        }

        //比较大小 使用compareTo方法，根据其返回的值进行判断

//        a小于b		a.compareTo(b) == -1
//        a等于b		a.compareTo(b) == 0
//        a大于b		a.compareTo(b) == 1
//        a大于等于b	a.compareTo(b) > -1
//        a小于等于b	a.compareTo(b) < 1
        System.out.println(bigDecimal.compareTo(bigDecimal1));
    }

    @Test
    public void test1(){
        System.out.println(new BigDecimal(0f));
        double d = 2.0;
        int i =1;
        boolean b = new BigDecimal(d).compareTo(new BigDecimal(i)) == 0;
        System.out.println(b);
    }




}
