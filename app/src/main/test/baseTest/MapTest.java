package main.test.baseTest;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/5/20 14:39
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class MapTest {

    public static void main(String[] args) {
        //1.8版本获取map值，为空时设置默认值
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.getOrDefault("key","");
        //比上一种方式多个好处：对传入map进行判空赋值
        MapUtils.getString(hashMap,"key","");

        //更新map中的键值，直接put，当遇到重复的键时会覆盖其值
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("1","123");
        hashMap1.put("1","456");
        System.out.println(hashMap1.get("1"));

        //判断map键值是否为空
        boolean value = hashMap1.get("1").toString().equals("");
        boolean key = hashMap1.get("2") == null;
        System.out.println("value:" +value+"     key:"+key);

    }

    //线程安全map
    public void threadMap(){

        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("scg","123");

    }
}
