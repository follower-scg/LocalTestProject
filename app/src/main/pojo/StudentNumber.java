package main.pojo;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/20 13:56
 * @description： 班级学生数量
 */
public class StudentNumber {

    private int id;
    private String className;
    private int number;

    public String getClassName() {
        return className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
