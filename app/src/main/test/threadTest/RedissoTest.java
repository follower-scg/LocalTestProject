package main.test.threadTest;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/9/28 13:35
 * @description： Redisson实现分布式锁
 */
public class RedissoTest {

    static int count = 1;

    private static void redissonThreadTest(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        config.useSingleServer().setPassword("");
//        RedissonClient redisson = Redisson.create(config);
        RedissonClient redisson = Redisson.create();
        RLock mylock = redisson.getLock("mylock");

        try {
//            System.out.println(Thread.currentThread().getName());
            mylock.lock(200, TimeUnit.SECONDS);
//            mylock.lock();
            System.out.println("第"+ count + "次运行");
            System.out.println(Thread.currentThread().getName());
            count++;
//            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("结束运行"+count);
            //如果不释放锁，其他线程无法进行下去
            mylock.unlock();
        }
//        redisson.shutdown();
    }

    @Test
    public void threadtest(){

        for (int i = 0;i<5;i++){
            Thread thread = new Thread(() -> redissonThreadTest());
            thread.start();
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<5;i++){
            Thread thread = new Thread(() -> redissonThreadTest());
            thread.start();
        }
    }
}
