package com.ndr.free.concurrency.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.ndr.free.concurrency.model.SampleTask6;

public class SampleMain6 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		ArrayList<Future<Long>> results = new ArrayList<>();
		for (int i=0; i<10 ; i++ ) {
			SampleTask6 sampleTask6 = new SampleTask6(i+1);
			results.add(executor.submit(sampleTask6));
		}
		for (int i = 0; i < results.size(); i++) {
			Future<Long> future = results.get(i);
			try {
				System.out.println("Task no: " + (i+1) + " sleeped for " + future.get(10, TimeUnit.SECONDS) + " seconds.");
			} catch (InterruptedException | ExecutionException| TimeoutException e) {
				e.printStackTrace();
			}
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
