package main.test.threadTest;

import java.util.concurrent.*;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/8/16 16:52
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class ThreadPoolTest {

    //核心线程数
    private static final int CORE_POOL_SIZE = 5;
    //最大线程数
    private static final int MAX_POOL_SIZE = 10;
    //线程等待时间
    private static final Long KEEP_ALIVE_TIME = 1L;
    //队列容量
    private static final int QUEUE_CAPACITY = 100;


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        //ThreadPoolExecutor使用execute执行任务，不需要返回值
        //而如果需要执行结果的话则可以实现Callable。在线程池同样execute提供一个不需要返回结果的任务执行，
        // 而对于需要结果返回的则可调用其submit方法。
//        for (int i = 0; i < 5; i++) {
////            MyRunnable runnable = new MyRunnable();
////
////            executor.execute(runnable);
////        }
////        executor.shutdown();
////        //当所有任务执行结束返回true
////        while (!executor.isTerminated()){
////        }
////        System.out.println(Thread.currentThread().getName()+"-执行结束");

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");;
            }
        });

        Future<String> submit = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "2";
            }
        });
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
