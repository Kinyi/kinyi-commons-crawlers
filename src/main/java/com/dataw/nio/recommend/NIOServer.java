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
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 运行有问题
 *
 * @author Kinyi_Chan
 * @since 2018-11-05
 */
@Slf4j
public class NIOServer {
    private static final int PORT = 8888;
    ExecutorService executorService;
    ServerSocketChannel serverChannel;
    Selector selector;

    public NIOServer() {
        try {
            executorService = Executors.newFixedThreadPool(1);
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(PORT));
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            log.info("服务器端程序启动，该程序在" + PORT + "端口上进行监听...");
            clientHandle();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void clientHandle() throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    executorService.submit(new ClientHandleThread(channel));
                }
                iterator.remove();
            }
            selector.selectedKeys().clear();
        }
    }

    public static void main(String[] args) {
        new NIOServer();
    }

    class ClientHandleThread implements Runnable {
        SocketChannel client;

        public ClientHandleThread(SocketChannel client) {
            this.client = client;
            log.info("服务器端连接成功，可以与服务器端进行数据的交互操作...");
        }

        @Override
        public void run() {
            boolean flag = true;
            ByteBuffer buffer = ByteBuffer.allocate(50);
            try {
                while (flag) {
                    buffer.clear();
                    int readCount = client.read(buffer);
                    String inputStr = new String(buffer.array(), 0, readCount);
                    log.info("client say: " + inputStr);
                    String outputStr = "[ECHO]" + inputStr + "\n";
                    if ("exit".equalsIgnoreCase(inputStr)) {
                        flag = false;
                        outputStr = "bye bye...kiss";
                    }
                    buffer.clear();
                    buffer.put(outputStr.getBytes());
                    buffer.flip();
                    client.write(buffer);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
