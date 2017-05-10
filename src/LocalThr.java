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
	}

	public void run() {
		int result = 0;
		if (request.getCommand() == 0){
			result = nextEven();
		request.setMessage("NEXTEVEN: ",result);
		}
		else{
			result = nextOdd();
			request.setMessage("NEXTODD: ",result);

		}
		long n = this.getId();

		resultQue.add(request);
	}

	public int nextEven() {
		return even += 2;
	}

	public int nextOdd() {
		return odd += 2;
	}
	public Node getNode(){
		return request;
	}
}