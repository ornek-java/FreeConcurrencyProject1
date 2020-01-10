package com.ndr.free.concurrency.model;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class SampleTask8 extends RecursiveAction {

	private static final long serialVersionUID = 1852883230181599349L;
	private long numberOfItemsToProcess;
	private int numberOfParallelTasks;
	private int chunkSize;
	
	
	public SampleTask8(long numberOfItemsToProcess, int numberOfParallelTasks, int chunkSize) {
		this.numberOfItemsToProcess = numberOfItemsToProcess;
		this.numberOfParallelTasks = numberOfParallelTasks;
		this.chunkSize = chunkSize;
	}


	@Override
	protected void compute() {
		if ( numberOfItemsToProcess <= chunkSize){
			System.out.println(this + " will compute itself " + numberOfItemsToProcess + " items...");
			long sleepSeconds = (long)Math.random() * chunkSize;
			try {
				TimeUnit.SECONDS.sleep(sleepSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println(this + " will divide tasks for " + numberOfItemsToProcess + " items...");
			long numberOfItemsPerTask = numberOfItemsToProcess / numberOfParallelTasks;
			long remainingItemsToProcess = numberOfItemsToProcess;
			SampleTask8 sampleTask8 = null;
			ArrayList<SampleTask8> sampleTask8List = new ArrayList<>();
			for (int i=0; i< numberOfParallelTasks; i++){
				if (i < numberOfParallelTasks -1 ){
					sampleTask8 = new SampleTask8(numberOfItemsPerTask, numberOfParallelTasks, chunkSize);
					remainingItemsToProcess -= numberOfItemsPerTask;
					sampleTask8List.add(sampleTask8);
				}else{
					sampleTask8 = new SampleTask8(remainingItemsToProcess, numberOfParallelTasks, chunkSize);
					sampleTask8List.add(sampleTask8);
					break;
				}
			}
			invokeAll(sampleTask8List);	
		}
	}

}
