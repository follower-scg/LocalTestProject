package main.test.lambdaTest;

import main.pojo.Student;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/2 10:18
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public interface StudentParamConstrut {

    Student getStudent(String name,int age);
}
