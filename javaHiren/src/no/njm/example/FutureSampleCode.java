package no.njm.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureSampleCode {

	Future<Integer> executeMe(Integer input) {

		return executor.submit(() -> {

			Thread.sleep(2);
			return input * input;
		});
	}

	ExecutorService executor = Executors.newSingleThreadExecutor();

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		Future<Integer> callMe = new FutureSampleCode().executeMe(3);
		System.out.println(callMe.get());
		System.out.println(" I Am not waiting here");
		System.out.println(" I Am not waiting here");
		System.out.println(" I Am not waiting here");
		System.out.println(" I Am not waiting here");
		System.out.println(" I Am not waiting here");
		Runnable r = () -> {
			System.out.println("Hello");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		ExecutorService nn = Executors.newFixedThreadPool(5);
		BlockingQueue bq = new ArrayBlockingQueue<>(1000);
		nn.execute(new workerThreadArrayListDeque(bq));
		for (int i = 0; i < 10; i++) {
			nn.execute(new workerThread(i, bq));
		}
		nn.shutdown();
		while (!nn.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

}

class workerThread implements Runnable {
	Integer i;
	BlockingQueue<Integer> alDq;

	public workerThread(Integer i) {
		super();
		this.i = i;
	}

	public workerThread(int i2, BlockingQueue<Integer> bq) {
		super();
		this.i = i2;
		alDq = bq;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		{
			System.out.println("Hello*************8" + i);
			try {
				Thread.sleep(10000);
				while (true) {
					System.out.println(alDq.poll());
					System.out.println(alDq.size() + " I am removing data to the ArrayBlockingQueue My Thread-->" + i);
					Thread.sleep(2000);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub

		}
	}

}

class workerThreadArrayListDeque implements Runnable {
	AtomicInteger ik = new AtomicInteger(0);
	BlockingQueue<Integer> alDq;

	public workerThreadArrayListDeque(BlockingQueue<Integer> i) {
		super();
		this.alDq = i;
	}

	@Override
	public void run() {
		while (true) {
			alDq.add(ik.incrementAndGet());
			System.out.println(alDq.size() + " I am adding data to the ArrayBlockingQueue");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
