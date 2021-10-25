package main.test.threadTest;


import main.utils.JDBCUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/14 10:17
 * @description： 测试情景：
*                      1.测试类中注入dao等spring管理bean失败 ————》初步原因：测试类没有启用到spring容器
*                          导致无法通过容器注入对象 ————》数据库操作使用原生jdbc
*                      2.模拟实现多线程情景下操作数据库
*                          1.使用CountDownLatch对象保证所有线程一起运行
*                              countDownLatch.await()阻塞当前线程
* 		                        countDownLatch.countDown()计数器减一， 当计数器为0时，所有线程一起执行
* 		                    2.如何保证所有子线程运行完成后执行数据库连接关闭
* 		                        错误实例
* 		                                1.开始使用主线程join方法
* 		                                       主线程一直不动进入死循环
* 		                                       join方法的作用是插队，谁调用谁先执行，当主线程调用时，
* 		                                       会阻塞主线程先执行主线程，这导致了死循环
* 		                                       正确的例子： t1线程调用了线程t2.jion
* 		                                        t1暂停执行，等t2执行完后再执行t1
* 		                                2.使用守护线程
* 		                                        用守护线程执行关闭数据库连接
* 		                                            程序已运行就关闭了连接》》》守护线程是一直运行的，不适合此场景
* 		                                        守护线程应用场景：
* 		                                        （1）来为其它线程提供服务支持的情况；
* 		                                        （2） 或者在任何情况下，程序结束时，这个线程必须正常且立刻关闭，就可以作为守护线程来使用
 * 		                        正确实例
 * 		                                使用CountDownLatch对象
 * 		                                定义一个新的CountDownLatch对象，在每个线程执行完后执行countDown(),
 * 		                                当计数为0时，await()执行主线程后续代码
 */
public class TransactionThreadTest {

    private static Connection connection = JDBCUtil.getConnection();

    private static Lock lock = new ReentrantLock(true);
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;

//    @Autowired
//    private StudentDao studentDao;
//
//    @Autowired
//    private StudentNumberDao studentNumberDao;

//    private static SqlSession sqlSession = null;

//    private static StudentDao studentDao;
//    private static StudentNumberDao studentNumberDao;

//    static {
//        studentDao = (StudentDao) SpringHelper.getBean("studentDao");
//        studentNumberDao = (StudentNumberDao)SpringHelper.getBean("studentNumberDao");
//    }
//    public static TransactionThreadTest transactionThreadTest;
//
//    @PostConstruct
//    public void init(){
//        transactionThreadTest= this;
//    }

    //测试多线程下，10个名额，实际报名成功学生
//    @Transactional
    public static void operateTest() {
//        studentDao = (StudentDao) SpringHelper.getBean("studentDao");
//        studentNumberDao = (StudentNumberDao)SpringHelper.getBean("studentNumberDao");

        lock.lock();
        try {

            String sql = "select number from student_number where id = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) > 0) {
                    int number = resultSet.getInt(1) - 1;
                    String name = Thread.currentThread().getName();
                    String updateSql = "update student_number set number = ? where id = 1";
                    String insertSql = "insert into student (name,age)values(?,1)";
                    PreparedStatement upStatement = connection.prepareStatement(updateSql);
                    upStatement.setInt(1, number);
                    upStatement.executeUpdate();

                    PreparedStatement insertState = connection.prepareStatement(insertSql);
                    insertState.setString(1, name);
                    insertState.executeUpdate();
                    System.out.println(Thread.currentThread().getName() + "加入班级");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lock.unlock();
    }


    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(20);
        CountDownLatch countDownLatch2 = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    operateTest();
                    countDownLatch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.countDown();
        }
        System.out.println(Thread.currentThread().getName() + "————主线程阻塞");
//        Thread.currentThread().join();
        countDownLatch2.await();
        try {
            connection.close();
            System.out.println("数据库连接关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //守护线程
//        Thread thread = new Thread(()->{
//            try {
//                connection.close();
//                System.out.println("数据库连接关闭");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
        System.out.println("主线程执行完毕");

    }

}
