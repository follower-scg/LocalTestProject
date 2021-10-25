package main.test;

import com.sunyard.frame.base.util.SpringHelper;
import main.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/8/5 9:27
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;
//    static {
//        RedisTemplate redisTemplate =(RedisTemplate)SpringHelper.getBean("redisTemplate");
//    }

    @Test
    public void redis1(){
        RedisUtil redisUtil =(RedisUtil)SpringHelper.getBean("redisUtil");
//        redisTemplate.opsForValue().set("scg","123");
//        System.out.println(redisTemplate.opsForValue().get("scg"));
        redisUtil.set("scg","123");
        System.out.println(redisUtil.get("scg"));
    }

//    public static void main(String[] args) {
//        redisTemplate.opsForValue().set("scg","123");
//        System.out.println(redisTemplate.opsForValue().get("scg"));
//    }
}
