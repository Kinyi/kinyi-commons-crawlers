package com.dataw.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Kinyi_Chan
 * @since 2018-11-03
 */
public class FileCopy {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("/Users/Kinyi_Chan/work/data/temp.txt");
        FileChannel fcIn = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("/Users/Kinyi_Chan/work/data/copy.txt");
        FileChannel fcOut = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (fcIn.read(buffer) != -1) {
            buffer.flip();
            fcOut.write(buffer);
            buffer.clear();
        }

        fcIn.close();
        fis.close();
        fcOut.close();
        fos.close();
    }
}
