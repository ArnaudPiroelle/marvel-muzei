package com.arnaudpiroelle.muzei.marvel.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class HttpUtils {

    public static void writeFile(InputStream inputStream, File pageFile) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(pageFile);

        ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
        WritableByteChannel outputChannel = Channels.newChannel(outputStream);

        fastChannelCopy(inputChannel, outputChannel);

        inputChannel.close();
        outputChannel.close();
    }

    private static void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }
}
