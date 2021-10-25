package main.test.threadTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/16 9:19
 * @description： 线程测试
 */
public class ThreadTest {

    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    //多线程下使用list
    //使用Collections.synchronizedList 方法把list转换成线程安全集合
    @Test
    public void list() {
        List<String> list = new ArrayList<>();
        List<String> list1 = Collections.synchronizedList(list);
    }

    //死锁
    @Test
    public void deadLock() {

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();

//        new Thread(() -> {
//            synchronized (resource1) {
//                System.out.println(Thread.currentThread() + "get resource1");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread() + "waiting get resource2");
//                synchronized (resource2) {
//                    System.out.println(Thread.currentThread() + "get resource2");
//                }
//            }
//        }, "线程 2").start();

        //主线程退出后，子线程立即退出，会导致打印不出部分语句
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //threadLocal
    @Test
    public void threadLocal(){

        ThreadLocal<String> threadLocal = new ThreadLocal<String>(){

            @Override
            //threadLocal初始化方法
            protected String initialValue(){
                return "1";
            }
        };
        System.out.println(threadLocal.get());
        threadLocal.set("123");
        System.out.println(threadLocal.get());


    }

    //线程本地存储变量
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public static void main(String[] args) {
        for (int i = 0; i <3; i++) {//启动三个线程
            Thread t = new Thread(() -> add10ByThreadLocal());
            t.start();
        }
    }

    /**
     * 线程本地存储变量加 5
     */
    private static void add10ByThreadLocal() {
        for (int i = 0; i <5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
        }
        //ThreadLocal内存泄漏
        //每次使用完ThreadLocal，都调用它的remove()方法，清除数据。
        //在使用线程池的情况下，没有及时清理ThreadLocal，不仅是内存泄漏的问题，更严重的是可能导致业务逻辑出现问题。
        // 所以，使用ThreadLocal就跟加锁完要解锁一样，用完就清理。
        THREAD_LOCAL_NUM.remove();
    }

}
