package com.ndr.free.concurrency.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SampleTask7 implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " start: " + new Date());
		long sleepSeconds = (long)(Math.random() * 4) ;
		try{
			System.out.println(Thread.currentThread().getName() + " sleep for " + sleepSeconds + " seconds...");
			TimeUnit.SECONDS.sleep( sleepSeconds);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " end: " + new Date());
		
	}

}
