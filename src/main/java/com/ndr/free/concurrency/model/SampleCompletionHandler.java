package com.ndr.free.concurrency.model;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

public class SampleCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

	
	
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		System.out.println(Thread.currentThread().getName() + " completed..." + result + " bytes read!");
	}

	@Override
	public void failed(Throwable e, ByteBuffer byteBuffer) {
		e.printStackTrace();
	}
	
}


