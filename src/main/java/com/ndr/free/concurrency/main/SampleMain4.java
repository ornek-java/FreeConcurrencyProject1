package com.ndr.free.concurrency.main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ndr.free.concurrency.model.SampleThread4;

public class SampleMain4 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new SampleThread4(lock);
		}
		for (Thread thread : threads) {
			thread.start();
		}
		
		
	}
}
