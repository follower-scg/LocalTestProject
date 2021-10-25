package main.test;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/1 15:36
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class ASCIITest {

    public static void main(String[] args) {

        //16进制中编码相关数值
        System.out.println("0xFF :"+(byte) 0xFF);
        System.out.println("0xFE :"+(byte) 0xFE);
        System.out.println("0xBB :"+(byte) 0xBB);
        System.out.println("0xBF :"+(byte) 0xBF);
        System.out.println("0xF0 :"+(byte) 0xF0);
        System.out.println("0xC0 :"+(byte) 0xC0);
        System.out.println("0x80 :"+(byte) 0x80);
        System.out.println("0xDF :"+(byte) 0xDF);
    }
}
