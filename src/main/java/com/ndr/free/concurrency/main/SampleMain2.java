package com.ndr.free.concurrency.main;

import java.util.Date;

public class SampleMain2 {

	private static volatile boolean isFinished = false;
	
	public static void main(String[] args) {
		
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable(){
				public void run() {
					System.out.println(Thread.currentThread().getName() + " start: " + new Date());
					int count=1;
					while (!isFinished){
						try {
							System.out.println(Thread.currentThread().getName() + " sleep ... " + count++ + " : " +new Date());
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(Thread.currentThread().getName() + " ended: " + new Date());
				}			
			});	
		
		}
		for (Thread thread : threads) {
			thread.start();
		}
		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 isFinished = true;
		 try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 System.out.println("Main thread end:" + new Date());
		
	}
}

