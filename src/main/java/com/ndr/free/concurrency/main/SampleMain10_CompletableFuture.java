package com.ndr.free.concurrency.main;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SampleMain10_CompletableFuture {

	public static void main(String[] args) {
		
		runAsyncSample();
	
		suppyAsyncSample();
		
		joinSample();
		
		thenAppySample();
	}

	
	private static void runAsyncSample() {
		CompletableFuture<Void> futureResultVoid = CompletableFuture.runAsync(() -> System.out.println("Hello world!!"));
		try {
			futureResultVoid.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void suppyAsyncSample() {
		CompletableFuture<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello world!!";});
		
		try {
			futureResultString.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private static void joinSample() {
		List<Integer> intList = List.of(1,2,3,4);
		List<CompletableFuture<Integer>>  intFutureList = intList.stream()
				.map(n -> CompletableFuture.supplyAsync( () -> {
																try {
																	TimeUnit.SECONDS.sleep(n);
																} catch (InterruptedException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																} 
																return n;}))
				.collect(Collectors.toList());
		
		intList = intFutureList.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
	
	
	private static void thenAppySample() {
		CompletableFuture<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello ";});
		futureResultString.thenApply( message -> {return message + " world";} );
		
	}
}
