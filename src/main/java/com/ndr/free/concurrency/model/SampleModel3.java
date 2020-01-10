package com.ndr.free.concurrency.model;

public class SampleModel3 {
	
	private int counter = 0;
	
	public synchronized void incrementCounter(){
		counter = counter + 1;
	}
	
	public int getCounter(){
		return counter;
	}
}
