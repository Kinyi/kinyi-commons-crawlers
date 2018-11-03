package com.dataw.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author Kinyi_Chan
 * @since 2018-11-03
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //开启挑选器
        Selector sel = Selector.open();
        //开启ServerSocket通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定端口
        ssc.socket().bind(new InetSocketAddress("localhost", 8888));
        //设置非阻塞
        ssc.configureBlocking(false);
        //在挑选器中注册通道(服务器通道)
        ssc.register(sel, SelectionKey.OP_ACCEPT);

        SelectableChannel sc = null;
        while (true) {
            //阻塞的
            sel.select();
            //迭代挑选出来的key集合
            Iterator<SelectionKey> it = sel.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                try {
                    //是可接受的事情
                    if (key.isAcceptable()) {
                        sc = key.channel();
                        SocketChannel sc0 = ((ServerSocketChannel) sc).accept();
                        //设置SocketChannel为非阻塞
                        sc0.configureBlocking(false);
                        //注册读事件
                        sc0.register(sel, SelectionKey.OP_READ);
                    }
                    //可读
                    if (key.isReadable()) {
                        SocketChannel sc1 = (SocketChannel) key.channel();
                        //会送hello:
                        byte[] helloBytes = "hello".getBytes();
                        buf.put(helloBytes, 0, helloBytes.length);

                        while (sc1.read(buf) != 0) {
                            buf.flip();
                            sc1.write(buf);
                            buf.clear();
                        }
                    }
                } catch (Exception e) {
                    sel.keys().remove(key);
                }
            }
            //清除所有挑选出来的key
            sel.selectedKeys().clear();
        }
    }
}
