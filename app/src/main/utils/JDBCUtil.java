package main.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/21 9:31
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class JDBCUtil {

    public static Connection getConnection() {
        //获取mySql数据库的驱动类
        String driver = "org.gjt.mm.mysql.Driver";
        //连接数据库
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        //连接mySql的用户名
        String name = "root";
        //连接mySql的密码
        String password = "123456";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, name, password);
            System.out.println("成功连接数据库");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载驱动程序有错误");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("取得连接时有错误，核对用户名和密码");
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection cc = JDBCUtil.getConnection();
        System.out.println(cc);
    }
}

