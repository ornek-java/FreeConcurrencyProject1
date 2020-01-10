package com.ndr.free.concurrency.main;

import java.util.Date;

public class SampleMain1 {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable(){
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("Thread1 end: " + new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}});
		System.out.println("Thread1 start: " + new Date());
		thread1.start();
	}
}
