package com.ndr.free.concurrency.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SampleTask5 implements Runnable {

	private int taskNo;
		
	public SampleTask5(int taskNo) {
		this.taskNo = taskNo;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " - task no: " + taskNo + " start: " + new Date());
		try{
			long sleepSeconds = (long)(Math.random() * 4) ;
			System.out.println(Thread.currentThread().getName() + " - task no: " + taskNo + " sleep for " + sleepSeconds + " seconds...");
			TimeUnit.SECONDS.sleep( sleepSeconds);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " - task no: " + taskNo + " end: " + new Date());
	}

}
