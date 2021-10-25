package main.test.lambdaTest;

import org.junit.jupiter.api.Test;
import main.pojo.Student;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/2 9:23
 * @description： Lambda测试
 */
public class Lambda {

    //使用 对象::new 的方式创建对象
    //需要定义一个接口作为对象的生成器
    //通过 类名::new 的方式来实例化对象，然后调用方法返回对象
    @Test
    public  void newObject() {
        StudentConstruct creator =  Student::new;
        Student student = creator.getStudent();
        StudentParamConstrut creator1 = Student::new;
        Student student1 = creator1.getStudent("san", 15);
        System.out.println(student1);
    }

    //方法引用测试 格式： 类名/对象 :: 方法
    @Test
    public void methodQuote(){

        //引用类方法——类名::静态方法
        //需要定义一个接口，接口实现方法由该静态方法实现
        InstanceLambda instanceLambda = Lambda::sum;
        long method = instanceLambda.method(10, 5);
        System.out.println(method);

        //引用对象的实例方法——对象::成员方法(非静态方法)
        Lambda lambda = new Lambda();
        InstanceLambda instanceLambdas = lambda::add;
        System.out.println(instanceLambdas.method(5, 6));

        //引用类的实例方法——类名::成员方法(静态方法)
        InstanceLambda lambda1 = Lambda::divide;
        System.out.println(lambda1.method(10, 5));

        //引用构造器——类名::new

    }


    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */

    public static long divide (int a ,int b){
        return a/b;
    }

    public static long sum(int a ,int b ){
        return a+b;
    }
    public long add(int a ,int b ){
        return a+b;
    }


    private void getTestResult(InterfaceTest interfaceTest){

        Object invoke = interfaceTest.invoke("123");

        //对invoke对象进行操作
        //{公共处理方法块}
            //判断操作，对不同结果返回不同数据

        System.out.println(invoke);
    }


    @org.junit.Test
    public void interfaceTestMain(){

        getTestResult(new InterfaceTest() {
            @Override
            public Object invoke(String s) {
                return "1230";
            }
        });

        getTestResult(s -> "1234");
    }
}
