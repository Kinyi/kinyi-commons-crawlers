package com.dataw.nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author Kinyi_Chan
 * @since 2018-11-03
 */
public class MyClient {

    public static void main(String[] args) throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(1024 * 8);
        //挑选器
        Selector sel = Selector.open();
        //套接字通道
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8888));
        sc.configureBlocking(false);
        sc.register(sel, SelectionKey.OP_READ);

        new Thread() {
            @Override
            public void run() {
                int i = 0;
                ByteBuffer buf2 = ByteBuffer.allocate(1024 * 8);
                while (true) {
                    try {
                        buf2.put(("Tom" + (i++)).getBytes());
                        buf2.flip();
                        sc.write(buf2);
                        buf2.clear();
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        while (true) {
            sel.select();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //读取通道的数据，写入到buf中
            while (sc.read(buf) > 0) {
                buf.flip();
                baos.write(buf.array(), 0, buf.limit());
                buf.clear();
            }
            System.out.println(new String(baos.toByteArray()));
            sel.selectedKeys().clear();
        }
    }
}
