package com.ndr.free.concurrency.main;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ndr.free.concurrency.model.SampleTask5;

public class SampleMain5 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		for (int i=0; i<10 ; i++ ) {
			SampleTask5 sampleTask5 = new SampleTask5(i+1);
			executor.execute(sampleTask5);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " end: " + new Date());
	}

}
