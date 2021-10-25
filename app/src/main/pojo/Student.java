package main.pojo;

import java.util.Objects;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/5/14 15:21
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class Student {

        private String name;
        private int age;

        public Student() {
            // TODO Auto-generated constructor stub
        }

        public Student(String name,int age){
            this.name=name;
            this.age=age;
        }

        public void setName(String name){
            this.name=name;

        }

        public String getName(){
            return name;
        }

        public void setAge(int age){
            this.age=age;
        }

        public int getAge(){
            return age;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "student [name="+name+" , "+"age="+age+"]";
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
