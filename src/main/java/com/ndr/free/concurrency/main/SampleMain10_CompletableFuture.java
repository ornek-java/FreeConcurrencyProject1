package com.ndr.free.concurrency.main;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SampleMain10_CompletableFuture {

	
	public static void main(String[] args) {
		
		runAsyncSample();
	
		suppyAsyncSample();
		
		joinSample();
		
		thenAppySample();
		
		thenComposeSample();
		
		thenCombineSample();
		
		orTimeoutSample();
		
		completeOnTimeoutSample();
		
		allOfSample();
		
		anyOfSample();
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
		CompletableFuture<String> futureResultStringFinal = futureResultString.thenApply( message -> {return message + " world";} );
		
		try {
			futureResultStringFinal.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * "thenCompose" metodu iki asenkron işlemin uç uça birleştirilmesi için kullanılır. 
	 * Birinci işlemin sonucu hazır olduğu zaman ikinci asenkron işleme input olarak geçirilir.
	 */
	private static void thenComposeSample() {
		CompletableFuture<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello ";});
		CompletableFuture<String> futureResultStringFinal = futureResultString.thenCompose( message -> CompletableFuture.supplyAsync(() -> {return "Hello " + message ;}));
		
		try {
			futureResultStringFinal.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void thenCombineSample() {
		CompletableFuture<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello ";});
		CompletableFuture<String> futureResultString2 = CompletableFuture.supplyAsync(() -> {return "world!!";});
		CompletableFuture<String> futureResultStringFinal = futureResultString.thenCombine(futureResultString2, (message1, message2) -> message1 + message2);
		
		printResult(futureResultStringFinal);
	}
	
	
	/**
	 * "orTimeout" metodu asenkron işlem belirlenen süre içerisinde tamamlanamazsa "Timeout-Exception" fırlatır.
	 */
	private static void orTimeoutSample() {
		CompletableFuture<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello world!!";})
				.orTimeout(5, TimeUnit.SECONDS);;

		printResult(futureResultString);
		
	}
	
	
	private static void completeOnTimeoutSample() {
		CompletableFuture<String> futureResultString = CompletableFuture.supplyAsync(() -> {return "Hello world!!";})
				.completeOnTimeout("<DEFAULT RESPONSE>", 5, TimeUnit.SECONDS);
		
		printResult(futureResultString);
	}

	/**
	 * "allOf" takes as input an array of CompletableFutures and returns a CompletableFuture<Void> 
	 *  that�s completed only when all the CompletableFutures passed have completed.
	 *  Invoking join on the CompletableFuture returned by the allOf method provides an easy way 
	 *  to wait for the completion of all the Completable-Futures
	 */
	private static void allOfSample() {
		List<Integer> intList = List.of(1,2,3,4);
		CompletableFuture[]  intFutures = intList.stream()
				.map(n -> CompletableFuture.supplyAsync( () -> {
																try {
																	TimeUnit.SECONDS.sleep(n);
																} catch (InterruptedException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																} 
																return n;}))
				.toArray(size -> new CompletableFuture[size]);
		
		CompletableFuture.allOf(intFutures).join();
		
	}
	
	
	/**
	 * "anyOf" takes as input an array of CompletableFutures and returns a CompletableFuture<Object> 
	 *  that completes with the same value as the first-to-complete CompletableFuture.
	 */
	private static void anyOfSample() {
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
		
		CompletableFuture[] intFutures = intFutureList.toArray(new CompletableFuture[intFutureList.size()]);
		
		CompletableFuture<Object> futureResult = CompletableFuture.anyOf(intFutures);
		
	}
	
	private static void printResult(CompletableFuture<String> futureResultString) {
		try {
			System.out.println(futureResultString.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
