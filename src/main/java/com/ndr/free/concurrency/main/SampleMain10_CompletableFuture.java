package com.ndr.free.concurrency.main;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class SampleMain10_CompletableFuture {

	public static void main(String[] args) {
		Future<Void> futureResultVoid = CompletableFuture.runAsync(() -> System.out.println("Hello world!!"));
		
		Future<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello world!!";});
		
	}
}
