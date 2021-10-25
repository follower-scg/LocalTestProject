package main.test.exceptionTest;

import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/9/27 15:22
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
@RestController
public class ExceptionController {

    @Test
    public void exceptionControllerTest(){

//        Asserts.fail("失败");
        int a = 1/0;
    }

}
