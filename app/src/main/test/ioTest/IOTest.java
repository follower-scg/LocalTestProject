package main.test.ioTest;

import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/6/3 10:56
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
public class IOTest {
    String path ="C:/Users/Administrator/Desktop";

    //字符串和字节互转
    //字符-->字节
//    String s = "";
//    byte[] bys = s.getBytes("gbk/utf-8");  	 使用指定码表获得字节码数组
    //字节-->字符
//    byte[] bys = {};
//new String(bys,"gbk/utf-8");		使用指定字节获得字符串

    //文件类测试
    @Test
    public void fileTest() throws IOException {

        //创建文件
        File file = new File(path + "/fileTest.sql");
        //获取文件绝对路径
        file.getAbsolutePath();

        //判断文件是否存在
        if (file.exists()){
            //删除文件
            //当删除目录时，目录下有文件，需要先删除文件在删除目录
            file.delete();
        }
        //创建文件
        file.createNewFile();
        //创建文件夹
        file.mkdirs();
        //判断是否是目录文件夹
        if (file.isDirectory()){
            System.out.println(file.getAbsolutePath() +": 是目录");
        }else {
            System.out.println(file.getAbsolutePath() +": 不是目录");
        }
        //获取文件下所有文件的名称集合
        String[] list = file.list();


    }

    //IO流测试
    @Test
    public void ioStream(){
        //浏览器下载文件需要设置
//        response.setContentType("application/force-download;charset=UTF-8");
//        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//        os = response.getOutputStream();

//        is = new FileInputStream(file);
//        byte[] b = new byte[1024];
//        int len = 0, length = 0;
//        while ((len = is.read(b)) > 0) {
//            length += len;
//            os.write(b, 0, len);
//        }
    }


    @Test
    public void fileTest1(){
//        File file = new File("C:\\Users\\Administrator\\Desktop\\标签设计");
        File file = new File("C:/Users/Administrator/Desktop/标签设计");
        char separatorChar = file.separatorChar;
        System.out.println(separatorChar);
//        for (String s : file.list()) {
//            System.out.println(s);
//        }
        for (File listFile : file.listFiles()) {
//            System.out.println(listFile.getAbsolutePath());
        }
//        C:\jiaxing_bank_res\apache-tomcat-8.5.27\bin\zdk\temp\Thu Jul 15 16:39:17 CST 2021\嘉银运营〔2021〕162号
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\456\\嘉银运营〔2021〕162号");
//        file1.mkdirs();

    }

    //字节流复制文件
    /**
     * 字节输入流  FileInputStream
     * 缓冲区字节输入流 BufferdInputStream
     * 字节输出流  FileOutputStream
     * 缓冲区字节输出流 BufferdOutputStream
     * **/
    @Test
    public void byteStreamTest() throws IOException{

        FileInputStream inputStream = new FileInputStream(path+"/123.txt");
        FileOutputStream outputStream = new FileOutputStream(path+"/456.txt");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(bytes)) != -1){
            bufferedOutputStream.write(bytes,0,len);
        }

        bufferedOutputStream.close();
        bufferedInputStream.close();
        outputStream.close();
        inputStream.close();


    }

    //字符流复制文件

    /**
     * 字符输入流 FileReader
     * 缓冲区字符输入流 BufferedReader
     * 字符输出流 FileWriter
     * 缓冲区字符输出流 BufferdWriter
     */
    @Test
    public void charStreamTest() throws IOException{

        FileReader reader = new FileReader(path+"/123.txt");
        FileWriter writer = new FileWriter(path+"/456.txt");

        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String line;
        while (StringUtils.isNotBlank(line = bufferedReader.readLine())){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        bufferedReader.close();
        writer.close();
        reader.close();

    }

}
