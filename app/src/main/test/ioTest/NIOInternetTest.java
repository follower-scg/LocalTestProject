package main.test.ioTest;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/8 16:37
 * @description： NIO实现网络通讯
 */
public class NIOInternetTest {

    String path ="C:/Users/Administrator/Desktop";
    /**
     * 一、使用NIO完成网络通信的三个核心：
     * ①通道（Channel）：负责连接。
     * java.nio.channels.Channel接口：
     *         |--SelectableChannel
     *         |--SocketChannel
     *         |--ServerSocketChannel
     *         |--DatagramChannel
     *
     *         |--Pipe.SinkChannel
     *         |--Pipe.SourceChannel
     * ②缓冲区（Buffer）：负责数据的存取。
     * ③选择器（Selector）：是SelectableChannel的多路复用器。用于监控SelectableChannel的一些IO状况。
     */

    // NIO阻塞式客户端
    @Test
    public void blockingNIOClient() throws IOException {

        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9898));

        FileChannel fileChannel = FileChannel.open(Paths.get(path + "/123.txt"), StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读取本地文件发送到服务端
        while (-1 != fileChannel.read(buffer)){

            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //通知服务端发送完毕
        socketChannel.shutdownOutput();

        //接收服务端发送的消息
        int len = 0;

        while ((len = socketChannel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
            buffer.clear();
        }


        fileChannel.close();
        socketChannel.close();

    }

    //NIO阻塞式服务端
    @Test
    public void blockingNIOServer() throws IOException{
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        SocketChannel socketChannel = serverSocketChannel.accept();

        FileChannel fileChannel = FileChannel.open(Paths.get(path + "/789.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (-1 != socketChannel.read(buffer)) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        buffer.put("服务器端接受成功".getBytes());
        buffer.flip();

        socketChannel.write(buffer);


        fileChannel.close();
        socketChannel.close();
    }

    //NIO非阻塞式客户端
    @Test
    public void nioClient() throws IOException {
        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9897));
        //切换成非阻塞模式
        socketChannel.configureBlocking(false);
        //分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //发送数据给服务器端
        buffer.put(new Date().toString().getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        //关闭通道
        socketChannel.close();
    }

    //NIO非阻塞式服务端
    @Test
    public void nioServer() throws IOException {
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //切换成非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9897));
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上,并且指定监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            //获取当前选择器中所有注册的“选择键（已注册的监听事件）”
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //遍历
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //判断具体是什么事件准备就绪
                if (selectionKey.isAcceptable()) {
                    //如果是“接受就绪”，获取客户端的连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //切换成非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //获取当前选择器上“读就绪”状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }

                }
                //取消选择键
                iterator.remove();
            }


        }


    }
}


