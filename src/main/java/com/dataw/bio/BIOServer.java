package com.dataw.bio;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kinyi_Chan
 * @since 2018-11-05
 */
@Slf4j
public class BIOServer implements AutoCloseable {
    ServerSocket serverSocket;

    public BIOServer() {
        try {
            serverSocket = new ServerSocket(8888);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            while (true) {
                Socket client = serverSocket.accept();
                executor.submit(new HandlerThread(client));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        new BIOServer();
    }

    @Override
    public void close() throws Exception {
        this.serverSocket.close();
    }

    class HandlerThread implements Runnable {
        Socket client;

        public HandlerThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            boolean flag = true;
            try {
                while (flag) {
                    InputStream inputStream = client.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String inputStr = bufferedReader.readLine();
                    log.info("client say: " + inputStr);
                    if ("exit".equalsIgnoreCase(inputStr)) {
                        log.info("bye bye...kiss");
                        flag = false;
                    }
                    String outputStr = "[ECHO]" + inputStr + "\n";
                    client.getOutputStream().write(outputStr.getBytes());
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
