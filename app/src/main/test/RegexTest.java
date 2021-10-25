package main.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/7/27 15:54
 * @description： 正则表达式使用
 */
public class RegexTest {

    public static void main(String[] args) {
        String ss = "123,46,798,,,,,,,,,,";
        String regex = ",,,*";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(ss);
        String s = matcher.replaceAll("");
        System.out.println(s);

    }



}
