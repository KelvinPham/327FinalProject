import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class LocalThr extends Thread implements Runnable {
	public int x;
	public String request;
	public ConcurrentLinkedQueue<String> resultQue;

	public LocalThr(String cmd, ConcurrentLinkedQueue<String> r) {
		this.request = cmd; 
		this.resultQue = r;
		this.x = RuntimeThr.evenOddSequence;
	}
	
	public void run() {
		int result = 0;
		if(x > 10) {
			x = 0;
		}

		if (request.equals("0"))
			result = setNextEven();
		else
			result = setNextOdd();

		resultQue.add(request + " " + result);
	}
	
	public int setNextEven() {
		while(x % 2 != 0) {
			x++;
		}
		RuntimeThr.evenOddSequence = x;
		return x;
	}
	
	public int setNextOdd() {
		while(x % 2 != 1){
			x++;
		}
		
		RuntimeThr.evenOddSequence = x;

		return x;
	}
}