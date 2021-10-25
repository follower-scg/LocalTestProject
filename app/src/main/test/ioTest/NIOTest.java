package main.test.ioTest;

import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/8 11:10
 * @description： NIO
 *                      NIO与IO区别
 *                          IO是阻塞式的，当线程在读取或写入时，线程一直是等待状态
 *                          NIO是非阻塞式的，当线程在读取或写入时，空闲的线程会进行其他操作
 */
public class NIOTest {

    String path ="C:/Users/Administrator/Desktop";
    /**
     * 一、缓冲区（Buffer）：在Java NIO中负责数据的存取。缓冲区就是数组。数组是用于存储相同数据类型的数据。
     * 根据数据类型的不同，提供了相应类型的缓冲区（boolean除外）：ByteBuffer、CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer。
     * 上述缓冲区的管理方式几乎一致，都是通过allocate()获取缓冲区。
     *
     * 二、缓冲区存取数据的两个核心方法：
     * put()：存入数据到缓冲区。
     * get()：从缓冲区获取数据
     *
     * 三、缓冲区中的四个核心属性。
     * capacity：容量，表示缓冲区中最大存储数据的容量。一旦声明，不能更改。
     * limit：限制，界限，表示缓冲区可以操作数据的大小。(limit后面的数据不能进行读写)。
     * position：位置，表示缓冲区中正在操作数据的位置。
     * mark:标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置。
     *
     * 0 <= mark <= position <= limit <= capacity
     */
    @org.junit.Test
    public void nioBufferTest(){

        //获取指定容量的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //往缓存存数据
        buffer.put("123".getBytes());

        System.out.println("——————put——————");
        System.out.println("position:"+buffer.position());//3
        System.out.println("limit:"+buffer.limit());//1024
        System.out.println("capacity:"+buffer.capacity());//1024

        //切换到读取模式
        buffer.flip();

        System.out.println("——————flip——————");
        System.out.println("position:"+buffer.position());//0
        System.out.println("limit:"+buffer.limit());//3
        System.out.println("capacity:"+buffer.capacity());//1024

        //获取数据
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);

        System.out.println("获取缓冲区中的数据：" + new String(dst, 0, dst.length));//123
        System.out.println("----------------get--------------");
        System.out.println("position：" + buffer.position());//3
        System.out.println("limit：" + buffer.limit());//3
        System.out.println("capacity：" + buffer.capacity());//1024

        System.out.println("可以操作的数据量："+buffer.remaining());
        //读取一次后如果不进行判断是否还有数据，若没有数据再次读取就会报异常
        while (buffer.hasRemaining()) {
            byte[] dd = new byte[3];
            buffer.get(dd);
            System.out.println("再次获取缓冲区中的数据：" + new String(dd, 0, dd.length));
        }
        //重复读数据，将位置position设置为0
        buffer.rewind();

        System.out.println("----------------rewind-0-------------");
        System.out.println("position：" + buffer.position());//0
        System.out.println("limit：" + buffer.limit());//3

        byte[] ddd = new byte[buffer.limit()];
        buffer.get(ddd);
        System.out.println("----------------rewind-1-------------");
        System.out.println("重置后再次获取缓冲区中的数据：" + new String(ddd, 0, ddd.length));//123
        System.out.println("position：" + buffer.position());//3
        System.out.println("limit：" + buffer.limit());//3
        System.out.println("capacity：" + buffer.capacity());//1024

        //清空缓冲区，但是缓冲区的数据依然存在，数据处于“被遗忘状态”。
            //与clear类似方法compact()  》》区别
            //ByteBuffer compact()  将所有未读的数据拷贝到Buffer起始处，
            // 然后将position设置到最后一个未读元素的后面，limit属性依然设置为capacity。
            // 可以使得Buffer中的未读数据还可以在后续中被使用。
        buffer.clear();

        System.out.println("----------------clear-0-------------");
        System.out.println("position：" + buffer.position());//0
        System.out.println("limit：" + buffer.limit());//1024

        byte[] dddd = new byte[buffer.limit()];
        buffer.get(dddd);
        System.out.println("----------------clear-1-------------");
        System.out.println("清空缓存后再次获取缓冲区中的数据：" + new String(dddd, 0, dddd.length));//123
        System.out.println("position：" + buffer.position());//1024
        System.out.println("limit：" + buffer.limit());//1024
        System.out.println("capacity：" + buffer.capacity());//1024
    }

    /**
     * 一、通道（Channel）：用于源节点和目标节点之间的连接。在Java NIO中负责缓冲区中数据的传输。Channel本身是不存储数据，因此需要配合缓冲区进行操作。
     * 二、通道的主要实现类
     * java.nio.channels.Channel接口
     * FileChannel：用于读取、写入、映射和操作文件的通道。
     * DatagramChannel：通过UDP读写网络中的数据通道。
     * SocketChannel：通过TCP读写网络中的数据。
     * ServerSocketChannel：可以监听新进来的TCP连接，对每一个新进来的连接都会创建一个SocketChannel。
     * 三、获取通道
     * ①Java支持通道的类提供了getChannel()方法。
     * 本地IO：FileInputStream、FileOutputStream、RandomAccessFile
     * 网络IO：Socket、ServerSocket、DatagramSocket
     * ②在JDK1.7中的NIO2针对各个通道提供了静态方法open()。
     * ③在JDK1.7中的NIO2的Files工具类的newByteChannel()。
     * 四、通道之间的数据传输
     * transferFrom()
     * transferTo()
     */

    //利用直接缓冲区完成文件的复制
    @Test
    public void nioChannelTest() throws IOException {

        FileChannel inStream = FileChannel.open(Paths.get(path + "/123.txt"), StandardOpenOption.READ);
        FileChannel outStream = FileChannel.open(Paths.get(path + "/456.txt"),StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inStream.transferTo(0,inStream.size(),outStream);

        outStream.close();
        inStream.close();

        //用FileChannel创建的FileChannel文件当不设置权限时，默认为只读，无法修改，会报NonWritableChannelException异常
        // 用RandomAccessFile可以指定位置读取
        //mode值
//        "r"    以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。
//        "rw"   打开以便读取和写入。
//        "rws"  打开以便读取和写入。相对于 "rw"，"rws" 还要求对“文件的内容”或“元数据”的每个更新都同步写入到基础存储设备。
//        "rwd"  打开以便读取和写入，相对于 "rw"，"rwd" 还要求对“文件的内容”的每个更新都同步写入到基础存储设备

//        RandomAccessFile inFileStream = new RandomAccessFile(new File(path + "/123.jpg"), "rw");
//        RandomAccessFile outFileStream = new RandomAccessFile(new File(path + "/456.jpg"), "rw");
//
//        FileChannel inStream = inFileStream.getChannel();
//        FileChannel outStream = outFileStream.getChannel();
//
//        //复制文件
//        inStream.transferTo(0,inStream.size(),outStream);
//
//        outStream.close();
//        inStream.close();

    }

    //内存映射文件实现文件复制
    //使用此种方式适用于某些缓存区无法访问导致出现异常
    @Test
    public void nioChannelMapTest() throws IOException{

        FileChannel inStream = FileChannel.open(Paths.get(path + "/123.txt"), StandardOpenOption.READ);
        FileChannel outStream = FileChannel.open(Paths.get(path + "/456.txt"),StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMap = inStream.map(FileChannel.MapMode.READ_ONLY, 0, inStream.size());
        MappedByteBuffer outMap = outStream.map(FileChannel.MapMode.READ_WRITE, 0, inStream.size());

        //直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[inMap.limit()];
        inMap.get(bytes);

        outMap.put(bytes);

        outStream.close();
        inStream.close();
    }

    //使用非直接缓存复制文件
    @Test
    public void nioChannelIndirectTest() throws IOException{

        FileInputStream inputStream = new FileInputStream(path + "/123.txt");
        FileOutputStream outputStream = new FileOutputStream(path + "/456.txt");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (-1 != inputStreamChannel.read(buffer)){

            buffer.flip();

            //将缓冲区写入通道
            outputStreamChannel.write(buffer);
            //清空缓冲区
            buffer.clear();
        }

        outputStreamChannel.close();
        inputStreamChannel.close();
        outputStream.close();
        inputStream.close();
    }

    //分散读取和聚集写入
    //分散读取（Scattering Reads）是指从Channel中读取的数据“分散”到多个Buffer中。
    //聚集写入（Gathering Writes）是指将多个Buffer中的数据“聚集”到Channel。
    @Test
    public void nioChannelMoreTest() throws IOException{

        FileInputStream inputStream = new FileInputStream(path + "/123.txt");
        FileOutputStream outputStream = new FileOutputStream(path + "/456.txt");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer allocate1 = ByteBuffer.allocate(512);
        ByteBuffer allocate2 = ByteBuffer.allocate(512);

        ByteBuffer[] buffers = {allocate1, allocate2};

        while (-1 != inputStreamChannel.read(buffers)){
            for (ByteBuffer buffer : buffers) {
                buffer.flip();
            }
            outputStreamChannel.write(buffers);

            for (ByteBuffer buffer : buffers) {
                buffer.clear();
            }
        }

        outputStreamChannel.close();
        inputStreamChannel.close();
        outputStream.close();
        inputStream.close();

    }
}
