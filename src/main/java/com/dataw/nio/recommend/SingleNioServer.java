package com.dataw.nio.recommend;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * single thread handle NIO
 *
 * @author Kinyi_Chan
 * @since 2018-11-09
 */
@Slf4j
public class SingleNioServer {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        HandlerSelectionKey handler = new HandlerSelectionKeyImpl();
        try {
            //创建ServerSocketChannel
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress("localhost", PORT));
            //创建Selector
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            log.info("服务器端程序启动，该程序在" + PORT + "端口上进行监听...");
            //死循环,持续接收客户端连接
            while (true) {
                //selector.select(); 是阻塞方法
                int keys = selector.select();
                if (keys > 0) {
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        //处理 SelectionKey
                        handler.handler(key, selector);
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * SelectionKey 处理接口
     */
    public interface HandlerSelectionKey {
        void handler(SelectionKey key, Selector selector) throws IOException;
    }

    /**
     * SelectionKey 接口 实现类
     */
    public static class HandlerSelectionKeyImpl implements HandlerSelectionKey {
        @Override
        public void handler(SelectionKey key, Selector selector) throws IOException {
            int keyState = selectionKeyState(key);
            switch (keyState) {
                case SelectionKey.OP_ACCEPT:
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    accept(serverSocketChannel, selector);
                    break;
                case SelectionKey.OP_READ:
                    SocketChannel readSocketChannel = (SocketChannel) key.channel();
                    read(readSocketChannel);
                    break;
                default:
                    break;
            }
        }

        /**
         * 获取 SelectionKey 是什么事件
         *
         * @param key
         * @return
         */
        private int selectionKeyState(SelectionKey key) {
            if (key.isAcceptable()) {
                return SelectionKey.OP_ACCEPT;
            } else if (key.isReadable()) {
                return SelectionKey.OP_READ;
            }
            return -1;
        }

        /**
         * 接口客户端请求
         *
         * @param serverSocketChannel
         * @param selector
         * @throws IOException
         */
        private void accept(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            //将channel注册到Selector
            socketChannel.register(selector, SelectionKey.OP_READ);
        }

        /**
         * 读取客户端发送过来的信息
         *
         * @param socketChannel
         * @throws IOException
         */
        private void read(SocketChannel socketChannel) throws IOException {
            int hashcode = socketChannel.hashCode();
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            int readBytes = socketChannel.read(buffer);
            if (readBytes > 0) {
                //客户端发送来的消息
                String msg = new String(buffer.array(), 0, readBytes).trim();
                log.info("客户端【" + hashcode + "】发送来的消息: " + msg);
                if ("exit".equalsIgnoreCase(msg)) {
                    socketChannel.close();
                    log.info("客户端【" + hashcode + "】关闭连接");
                } else {
                    //响应消息
                    msg = "游客【" + socketChannel.hashCode() + "】say: " + msg + "\n";
                    buffer.clear();
                    buffer.put(msg.getBytes());
                    buffer.flip();
                    //响应客户端
                    socketChannel.write(buffer);
                }
            }
        }
    }
}
