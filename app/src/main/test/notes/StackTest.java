package main.test.notes;

import org.junit.Test;

import java.util.*;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/11 9:54
 * @description： 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 */
public class StackTest {

    //进栈
    private Stack<Integer> inStack;
    //出栈
    private Stack<Integer> outStack;


    @Test
    public void test1() throws Exception {
        inStack = new Stack<>();
        outStack = new Stack<>();

        int[] num = {1, 2, 3, 4, 5};

        for (int i : num) {
            pushNum(i);
        }

        //peek()出栈方法，但不会丢失数据，只是返回最后一个数据
        Integer peek = outStack.peek();

        System.out.println(popNum());

        while (!outStack.isEmpty()) {
            System.out.println(popNum());
        }


    }

    private void pushNum(int num) {

        inStack.push(num);
    }

    private int popNum() throws Exception {

        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }

        if (outStack.isEmpty()) {
            throw new Exception("stack is empty");
        }

        return outStack.pop();

    }

    public static void main(String[] args) {

        Vector<String> strings = new Vector<>();

        ArrayList<String> arrayList = new ArrayList<>();

        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.push("123");
        linkedList.push("456");
        linkedList.push("789");
        System.out.println(linkedList.peek());

        System.out.println(linkedList.pop());
        System.out.println(linkedList.pop());

        linkedList.add("789");
        linkedList.addFirst("999");
        linkedList.addLast("000");
        System.out.println(linkedList.pop());

        System.out.println(linkedList.get(0));



    }
}
