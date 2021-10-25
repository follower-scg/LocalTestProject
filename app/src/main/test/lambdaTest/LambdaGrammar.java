package main.test.lambdaTest;

import org.junit.jupiter.api.Test;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/3 10:02
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class LambdaGrammar {

    private static int method(int a) {
        return 88;
    }

    /**
     * 多参数无返回
     */
    @FunctionalInterface
    public interface NoReturnMultiParam {
        void method(int a, int b);
    }

    /**
     * 无参无返回值
     */
    @FunctionalInterface
    public interface NoReturnNoParam {
        void method();
    }

    /**
     * 一个参数无返回
     */
    @FunctionalInterface
    public interface NoReturnOneParam {
        void method(int a);
    }

    /**
     * 多个参数有返回值
     */
    @FunctionalInterface
    public interface ReturnMultiParam {
        int method(int a, int b);
    }

    /*** 无参有返回*/
    @FunctionalInterface
    public interface ReturnNoParam {
        int method();
    }

    /**
     * 一个参数有返回值
     */
    @FunctionalInterface
    public interface ReturnOneParam {
        int method(int a);
    }

    @Test
    //lambda语法测试
    public void lambdaGrammar() {

        //多个参有返回值
        ReturnMultiParam returnMultiParam = (a, b) -> {
            return a + b;
        };
        long method = returnMultiParam.method(5, 6);
        System.out.println(method);

        //无参无返回值
        NoReturnNoParam noReturnNoParam = ()->{
            System.out.println("1");
        };
        noReturnNoParam.method();

        //多参数无返回
        NoReturnMultiParam noReturnMultiParam = (a,b)->{
            System.out.println(a+b);
        };
        noReturnMultiParam.method(7,8);


        //一个参数无返回
        NoReturnOneParam noReturnOneParam = (a)->{
            System.out.println(a);
        };
        noReturnOneParam.method(9);
        //简化
        NoReturnOneParam noReturnOneParams = System.out::println;
        noReturnOneParams.method(5000);


        //无参有返回
        ReturnNoParam returnNoParam =()->{
            return 99;
        };
        System.out.println(returnNoParam.method());
        //简化
        ReturnNoParam returnNoParams =()->99;

        //一个参数有返回值
        ReturnOneParam returnOneParam = (a)->{
            return 88;
        };
        System.out.println(returnOneParam.method(8));

        //简化
        //  (a)->{return 88;};
        ReturnOneParam returnOneParams = a -> 88;
        //创建方法method
        ReturnOneParam returnOneParamss = LambdaGrammar::method;
        System.out.println(returnOneParams.method(9));
    }


}
