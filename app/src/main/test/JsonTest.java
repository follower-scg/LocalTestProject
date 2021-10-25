package main.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunyard.frame.app.po.User;
import main.pojo.Student;

import java.util.*;

public class JsonTest {
    public static String jsonmap = "{\"001\":{\"name\":\"xiaohong\",\"password\":\"654321\"},\"002\":[{\"$ref\":\"$.001\"},{\"name\":\"xixi\",\"password\":\"789\"}]}";
    public static String jsonuser = "{\"name\":\"xiaohong\",\"password\":\"654321\"}";
    public static String jsonlist = "[{\"name\":\"xiaohong\",\"password\":\"654321\"},{\"name\":\"xixi\",\"password\":\"789\"}]";
    public static char jsonChar = 'a';
    public static String jasonString = "ad";
    public static String jsonArrays = "[\"123\",\"465\",\"789\"]";

    public static void main(String[] args) {
        User user = new User();
        user.setCusername("xiaohong");
        user.setCpassword("654321");
        List<User> list = new ArrayList<User>();
        list.add(user);
        Map map = new HashMap();
        map.put("001", user);
        map.put("002", list);
        // 1. json字符串 转换为 java 对象

        JSONArray jsonArray = JSONObject.parseArray(jsonArrays);
        System.out.println(jsonArray.toJSONString());
        // toJsonString  - java对象转换为json字符串
        System.out.println(JSONObject.toJSONString(user));
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(map));

        // toJavaObject - json字符串转换为java对象
        System.out.println(JSONObject.parseObject(jsonuser, User.class));
        List list1 = JSONObject.parseObject(jsonlist, List.class);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(JSONObject.parseObject(JSONObject.toJSONString(list1.get(i)), User.class));
        }
        Map map1 = JSONObject.parseObject(jsonmap, Map.class);
        Iterator iterator = map1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("key :" + entry.getKey() + "   value: " + entry.getValue());
        }
        // Json  -- 实体类转换为json字符串
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(map));
        // json  -- json 转 java对象
        System.out.println(JSON.parseObject(jsonuser, User.class));
        List list2 = JSON.parseObject(jsonlist, List.class);
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(JSON.parseObject(JSON.toJSONString(list1.get(i)), User.class));
        }
        Map map2 = JSON.parseObject(jsonmap, Map.class);
        Iterator iterator2 = map1.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator2.next();
            System.out.println("key :" + entry.getKey() + "   value: " + entry.getValue());
        }


        List<Student> list5 = new ArrayList<>();
        Student student = new Student("bob", 24);
        Student student12 = new Student("lily", 23);
        list5.add(student);
        list5.add(student12);
        System.out.println("*******javaBean  to jsonString*******");
        String str1 = JSON.toJSONString(student);
        System.out.println(str1);
        System.out.println(JSON.toJSONString(list5));
        System.out.println();

        System.out.println("******jsonString to javaBean*******");
        //main.pojo.Student stu1=JSON.parseObject(str1,new TypeReference<main.pojo.Student>(){});
        Student stu1 = JSON.parseObject(str1, Student.class);
        System.out.println(stu1);
        System.out.println();

        System.out.println("******javaBean to jsonObject******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(student);
        System.out.println(jsonObject1.getString("name"));
        System.out.println();

        System.out.println("******jsonObject to javaBean******");
        Student student2 = JSON.toJavaObject(jsonObject1, Student.class);
        System.out.println(student2);
        System.out.println();

        System.out.println("*******javaBean to jsonArray******");
        List<Student> stulist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stulist.add(new Student("student" + i, i));

        }
        JSONArray jsonArrays = (JSONArray) JSON.toJSON(stulist);
        for (int i = 0; i < jsonArrays.size(); i++) {
            System.out.println(jsonArrays.getJSONObject(i));
        }
        System.out.println();

        System.out.println("*****jsonArry to javalist******");
        List<Student> myList = new ArrayList<>();
        for (int i = 0; i < jsonArrays.size(); i++) {

            Student student3 = JSON.toJavaObject(jsonArrays.getJSONObject(i), Student.class);
            myList.add(student3);
        }
        for (Student stu : myList) {
            System.out.println(stu);
        }

        System.out.println();

        System.out.println("*****jsonObject to jsonString*****");
        String str4 = JSON.toJSONString(jsonObject1);
        System.out.println(str4);
        System.out.println();

        System.out.println("*******jsonString to jsonObject*****");
        JSONObject jso1 = JSON.parseObject(str1);
        System.out.println(jso1.getString("name"));
        System.out.println();

        System.out.println("*****jsonString to jsonArray*****");
        JSONArray jArray = JSON.parseArray(JSON.toJSONString(stulist));
        for (int i = 0; i < jArray.size(); i++) {
            System.out.println(jArray.getJSONObject(i));
        }
        System.out.println();

    }


}

