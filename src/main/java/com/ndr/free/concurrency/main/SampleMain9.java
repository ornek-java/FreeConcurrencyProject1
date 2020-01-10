package com.ndr.free.concurrency.main;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ndr.free.concurrency.model.SampleCompletionHandler;

public class SampleMain9 {

	public static void main(String[] args) {
		ExecutorService threadPool = new ScheduledThreadPoolExecutor(5);
		AsynchronousFileChannel fileChannel = null;
		try {
			HashSet<StandardOpenOption> openOptionSet = new HashSet<>();
			openOptionSet.add(StandardOpenOption.READ);
			fileChannel = AsynchronousFileChannel.open(Paths.get(Paths.get("").toAbsolutePath().toString() + "\\src\\main\\resources\\SampleFile.txt"), openOptionSet, threadPool);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final int handlerCount = 10;
		SampleCompletionHandler[] handlers = new SampleCompletionHandler[handlerCount];
		for (int i = 0; i < handlers.length; i++) {
			handlers[i] = new SampleCompletionHandler();
		}
		
		final int bufferCount = 10;
		ByteBuffer buffers[] = new ByteBuffer[bufferCount];
		for (int i = 0; i < bufferCount; i++) {
			buffers[i] = ByteBuffer.allocate(10);
			fileChannel.read(buffers[i], i * 10, null, handlers[i % 5] );
		}
		
		try {
			threadPool.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		threadPool.shutdown();
		
		for (ByteBuffer byteBuffer : buffers) {
			for (int i = 0; i < byteBuffer.limit(); i++) {
				System.out.print((char) byteBuffer.get(i));
			}
			System.out.println("");
		}
	}
	
}
