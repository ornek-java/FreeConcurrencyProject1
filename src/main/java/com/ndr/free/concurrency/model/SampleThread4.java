package com.ndr.free.concurrency.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class SampleThread4 extends Thread{

	private Lock lock; 
	
	public SampleThread4(Lock lock) {
		this.lock = lock;
	}

	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " start: " + new Date());
		try {
			while( !lock.tryLock(3, TimeUnit.SECONDS) ){
				System.out.println(Thread.currentThread().getName() + " retry acquiring lock... " +new Date());
			}
			System.out.println(Thread.currentThread().getName() + " acquired lock! " +new Date());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return;
		}
		try{
			long sleepSeconds = (long)(Math.random() * 4) ;
			System.out.println(Thread.currentThread().getName() + " sleep for " + sleepSeconds + " seconds...");
			TimeUnit.SECONDS.sleep( sleepSeconds);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(Thread.currentThread().getName() + " release lock! " +new Date());
			lock.unlock();
		}
		System.out.println(Thread.currentThread().getName() + " end: " + new Date());
	}

	
}
