package main.test.threadTest;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/8/16 16:43
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName()+"-执行线程");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"-睡了3秒");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
