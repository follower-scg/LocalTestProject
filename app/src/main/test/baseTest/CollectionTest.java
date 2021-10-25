package main.test.baseTest;

import org.junit.Test;

import java.util.*;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/8/9 9:40
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class CollectionTest {

    @Test
    public void listTest(){

    }

    @Test
    public void setTest(){

        HashSet<String> set = new HashSet<>();
        set.add("123");
        set.add("3456");
        set.add("789");
        set.add("000");
//        for (String s : set) {
//            System.out.println(s);
//        }

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            iterator.remove();
//            System.out.println(iterator.next());
        }
    }

    //队列Queue，消费者和生产者模式使用，基于LinkedList实现
    @Test
    public void queue(){

        Queue<String> queue = new LinkedList<>();
        queue.add("123");
//        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println("消费第一次"+queue.poll());
        queue.offer("456");
        System.out.println(queue.peek());

//        System.out.println("消费第二次"+queue.poll());
//        System.out.println("消费第三次"+queue.poll());
//        System.out.println("消费第四次"+queue.remove());
    }
}
