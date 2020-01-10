package com.ndr.free.concurrency.main;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.ndr.free.concurrency.model.SampleTask8;

public class SampleMain8 {

	public static void main(String[] args) {
		long numberOfItemsToProcess = 104;
		int numberOfParallelTasks = 5;
		int chunkSize = 5; //should be greater than the number of parallel tasks
		SampleTask8 sampleTask8 = new SampleTask8(numberOfItemsToProcess, numberOfParallelTasks, chunkSize);
		ForkJoinPool joinForkPool = new ForkJoinPool();
		joinForkPool.execute(sampleTask8);
		while (!sampleTask8.isDone()) {
			try { 
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		System.out.println(Thread.currentThread().getName() + " Steal Count: " + joinForkPool.getStealCount());
		joinForkPool.shutdown();
		System.out.println(Thread.currentThread().getName() + " end: " + new Date());
	}
}
