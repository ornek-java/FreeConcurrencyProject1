package com.ndr.free.concurrency.model;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SampleTask6 implements Callable<Long> {

	private int taskNo;
		
	public SampleTask6(int taskNo) {
		this.taskNo = taskNo;
	}

	@Override
	public Long call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " - task no: " + taskNo + " start: " + new Date());
		long sleepSeconds = (long)(Math.random() * 4) ;
		try{
			System.out.println(Thread.currentThread().getName() + " - task no: " + taskNo + " sleep for " + sleepSeconds + " seconds...");
			TimeUnit.SECONDS.sleep( sleepSeconds);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " - task no: " + taskNo + " end: " + new Date());
		return sleepSeconds;
		
	}

}
