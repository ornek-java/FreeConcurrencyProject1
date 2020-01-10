package com.ndr.free.concurrency.main;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.ndr.free.concurrency.model.SampleTask7;

public class SampleMain7 {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		SampleTask7 sampleTask7 = new SampleTask7();
		ScheduledFuture<?> result=executor.scheduleAtFixedRate(sampleTask7, 1, 5, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Next execution of task starts in " + result.getDelay(TimeUnit.MILLISECONDS) + " milliseconds.");
		try {
			TimeUnit.MILLISECONDS.sleep(result.getDelay(TimeUnit.MILLISECONDS) - 10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
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
