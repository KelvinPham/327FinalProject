import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class LocalThr extends Thread implements Runnable {
	public static int even = 0, odd = 1, evenCount = 1, oddCount = 1;
	public Node request;
	public ConcurrentLinkedQueue<Node> resultQue;

	public LocalThr(Node cmd, ConcurrentLinkedQueue<Node> r) {
		this.request = cmd;
		this.resultQue = r;
		// this.x = RuntimeThr.evenOddSequence;
	}

	public void run() {
		int result = 0;
		if (request.equals("0"))
			result = nextEven();
		else
			result = nextOdd();
		long n = this.getId();
		request.

		resultQue.add(request + " " + result);
	}

	public int nextEven() {
		return even += 2;
	}

	public int nextOdd() {
		return odd += 2;
	}
}