package main.test;

import org.junit.jupiter.api.Test;
import main.pojo.Student;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/5/20 14:40
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class StreamTest {

    //测试stream生成
    public static void main(String[] args) throws InterruptedException {

        /**简介
         //      1.对单列，双列，数组的一种操作，不是IO流
         /       2.不会影响原始集合或数组的数据，只是通过流获取筛选其中的一些数据，
         最后可以把操作获得的数据存入到一个新的集合（保存流里面的数据）
         3."Stream流"不是集合，可以将它看成是"迭代器"，而且是一次性的，
         不能对一个Stream对象调用两次方法，所以只能用方法链；

         应用场景
         1.我们经常需要对集合中的元素进行一系列筛选，以前都是使用循环 + 判断。
         如果要进行一系列操作：例如：检索所有的“张姓”学员，然后再找出其中的男同学，然后年龄大于20岁的，
         然后取找到的前3个，使用“Stream”流可以很方便的进行操作。
         **/

        //生成流
        //1.单列集合，List/Set
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("5");

        Stream<String> stream = list1.stream();
        Stream<String> parallelStream = list1.parallelStream();

        //2.双列集合 Map
        //转成单列集合
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        //values和keySet——>获得map的值的集合
        //entrySet ——>获得map的键值集合，中间用=连接
        Collection<String> values = map.values();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Set<String> keySet = map.keySet();
//        System.out.println("values:"+values);
//        System.out.println("entries:"+entries);
//        System.out.println("keySet:"+keySet);
        Stream<String> valuesStream = values.stream();
        Stream<Map.Entry<String, String>> entryStream = entries.stream();
        Stream<String> keySetStream = keySet.stream();

        //3.数组
        //of方法中需要的是一个引用类型的数据或者引用类型的数组
        int[] ints = {10, 20, 30};
        Stream<int[]> arrayStream = Stream.of(ints);

        middleOpr(stream, valuesStream, entryStream, arrayStream);

    }

    //stream流中间操作
    private static void middleOpr(Stream<String> stream, Stream<String> valuesStream,
                                  Stream<Map.Entry<String, String>> entryStream,
                                  Stream<int[]> arrayStream) {

        //流操作工具类Collectors——Stream流的收集（将Stream流转成集合/数组）
        //list = stream.collect(Collectors.toList());
        //object = stream.toArray();

        Stream<String> stream1 = getStream();
        Stream<String> stream2 = getStream();
        Stream<String> stream3 = getStream();
        Stream<String> stream4 = getStream();
        Stream<String> stream5 = getStream();

        Stream<String> valuesStream1 = getValueStreams();
        Stream<String> valuesStream2 = getValueStreams();
        Stream<String> valuesStream3 = getValueStreams();
        Stream<String> valuesStream4 = getValueStreams();
        Stream<String> valuesStream5 = getValueStreams();

        //filter(Predicate predicate)——> 用于对流中的数据进行过滤
        //filter中的test方法返回true代表当前元素会保留下来
        Stream<String> stringStream = stream.filter(new Predicate<String>() {

            @Override
            public boolean test(String s) {
                return Integer.parseInt(s) < 3;
            }
        });

        List<String> collect = stringStream.collect(Collectors.toList());
        System.out.println("collect :" + collect);

        //limit(long maxSize)—— 返回此流中的元素组成的流，截取前指定参数个数的数据
        //截取前5条数据
        Stream<String> stringStream1 = valuesStream.limit(5);
//        stringStream1.forEach(System.out::println);

        //skip(long n)——跳过指定参数个数的数据，返回由该流的剩余元素组成的流
        //去除前2条数据获取后面数据
        System.out.println("limit_stringStream1 : ——————-");
        stringStream1.skip(2).forEach(System.out::println);

        //concat(Stream a, Stream b)——合并a和b两个流为一个流
        Stream<String> concatStream = concat(stream1, valuesStream1);
//        System.out.println("concat_concatStream : ——————-");
//        concatStream.forEach(System.out::println);

        //distinct ——去重操作,对自定义对象进行去重时需要在对象实体类重写equals方法
        System.out.println("distinct_stream2 : ——————-");
        stream2.distinct().forEach(System.out::println);

        //sorted—— 返回由此流的元素组成的流，根据自然顺序排序
        System.out.println("sorted_stream3 : ——————-");
        stream3.sorted().forEach(System.out::println);

        //Stream sorted(Comparator comparator)—— comparator)——自定义排序规则，
        // 返回由该流的元素组成的流，根据提供的Comparator进行 排序
        System.out.println("sorted_stream4 : ——————-");
        stream4.sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        }).forEach(System.out::println);

        //map(Function mapper) -- 把一种数据类型的流转换成另一种数据类型的流
        System.out.println("map_stream5 : ——————-");
        stream5.map(new Function<String, Integer>() {

            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).forEach(System.out::println);

        //mapToInt -- 把一种数据类型的流转换成IntStream -- 方便求通过sum方法求和
        System.out.println("mapToInt_valuesStream1 : ——————-");
        int sum = valuesStream2.mapToInt(new ToIntFunction<String>() {
            @Override
            public int applyAsInt(String s) {
                return Integer.parseInt(s);
            }
        }).sum();
        System.out.println(sum);


    }

    @Test
    //stream 终止操作
    public void finalOpr() {

        //find 如果需要找到某些数据，可以使用find相关方法
        //findFirst和findAny都是返回第一个值
        //findFirst返回的值是固定的
        Optional<Integer> first = Stream.of(5, 3, 6, 1).findFirst();
        System.out.println("first = " + first.get());
        //findAny返回的值不固定，在多线程流中会有多种结果
        Optional<Integer> any = Stream.of(5, 3, 6, 1).findAny();
        System.out.println("any = " + any.get());

        //match匹配——如果需要判断数据是否匹配指定的条件，可以使用Match相关方法
        boolean b = Stream.of(5, 3, 6, 1)
                // .allMatch(e -> e > 0); // allMatch: 元素是否全部满足条件
                // .anyMatch(e -> e > 5); // anyMatch: 元素是否任意有一个满足条件
                .noneMatch(e -> e < 0); // noneMatch: 元素是否全部不满足条件
        System.out.println("b = " + b);

        //foreach遍历流中的数据

        //max、min获得流中最大和最小的值
        List<String> list13 = Arrays.asList("zhangsan", "lisi", "wangwu", "xuwujing");
        int maxLines = list13.stream().mapToInt((String s) -> {
            return s.length();
        }).max().getAsInt();
        int minLines = list13.stream().mapToInt(String::length).min().getAsInt();
        System.out.println("最长字符的长度:" + maxLines + ",最短字符的长度:" + minLines);
        //最长字符的长度:8,最短字符的长度:4

        //count 统计其中的元素个数
        List<String> strList = new ArrayList<>();
        Collections.addAll(strList, "张无忌", "周芷若", "赵敏", "小昭", "杨不悔");
        System.out.println(strList.stream().count());

        //groupingBy 对流数据进行分组
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 52),
                new Student("杨颖", 56),
                new Student("迪丽热巴", 56),
                new Student("柳岩", 52));
//         Map<Integer, List<Student>> map = studentStream.collect(Collectors.groupingBy(Student::getAge));
        // 将年龄大于55的分为一组,小于55分成另一组
        Map<String, List<Student>> map = studentStream.collect(Collectors.groupingBy((s) -> {
            if (s.getAge() > 55) {
                return "及格";
            } else {
                return "不及格";
            }
        }));
        map.forEach((k, v) -> {
            System.out.println(k + "::" + v);
        });

        //joining 根据指定的连接符，将所有元素连接成一个字符串。
        Stream<Student> studentStream1 = Stream.of(
                new Student("赵丽颖", 95),
                new Student("杨颖",88),
                new Student("迪丽热巴",99),
                new Student("柳岩",77));
        String collect = studentStream1
                .map(student -> {
                    return student.getName();
                })
                .collect(Collectors.joining(">_<", "^_^", "^v^"));
        System.out.println(collect);

        //collect 指定对象收集到对应集合

    }

    /**
     * 测试单线程流和多线程流的区别
     * 结果：单线程流遍历集合跟传统集合遍历效率差不多
     * 多线程流效率比单线程流效率高很多
     * 区别：多线程流无法保证输出结果顺序一致
     */
    @Test
    public void Test1() {
        //模拟1000条数据 forEach打印测试
        List<Integer> list = new ArrayList();
        for (int j = 0; j < 1000; j++) {
            list.add(j);
        }

        //下面测试下各方法执行的时间 检查效率
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);//睡眠1毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("传统for循环运行时间:" + (endTime - startTime) + "ms");

        // 测试单管道stream执行效率
        startTime = System.currentTimeMillis();
        list.stream().forEach(r -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);//睡眠1毫秒
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        long streamendTime = System.currentTimeMillis();
        System.out.println("stream : " + (streamendTime - startTime) + "ms");

        // 测试多管道parallelStream执行效率
        startTime = System.currentTimeMillis();
        list.parallelStream().forEach(r -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);//睡眠1毫秒
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        long parallelStreamendTime = System.currentTimeMillis();
        System.out.println("parallelStream : " + (parallelStreamendTime - startTime) + "ms");
    }

    private static Stream getStream() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("5");

        Stream<String> stream = list1.stream();
        return stream;
    }

    private static Stream getValueStreams() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        Collection<String> values = map.values();
        Stream<String> valuesStream = values.stream();
        return valuesStream;
    }

}
