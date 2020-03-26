package com.nero.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP服务端，实现模拟用户登录
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //1、创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(8099);
        //2、创建数据报，用于接受客户端发送的数据
        byte[] data = new byte[1024];//
        DatagramPacket packet = new DatagramPacket(data,0, data.length);
        //3、接受客户端发送的数据
        socket.receive(packet);//此方法在接受数据报之前会一致阻塞
        //4、读取数据
        String info = new String(data, 0, data.length);

        //向客户端响应数据
        //1、从收到的数据包中获取客户端的地址、端口号、数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        System.out.println("我是服务器，客户端" + address + ":" + port + "告诉我" + info);
        byte[] data2 = "欢迎您！".getBytes();
        //2、创建数据报，包含响应的数据信息
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
        //3、响应客户端
        socket.send(packet2);
        //4、关闭资源
        socket.close();
    }
}
