package com.ndr.free.concurrency.main;

import java.util.concurrent.TimeUnit;

import com.ndr.free.concurrency.model.SampleModel3;

public class SampleMain3 {

	public static void main(String[] args) {
		final SampleModel3 sampleModel3 = new SampleModel3();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable(){
				public void run() {
					sampleModel3.incrementCounter();
				}
			});
		}
		for (Thread thread : threads) {
			thread.start();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sampleModel3.getCounter());
	}
	
}
