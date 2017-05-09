import java.util.concurrent.*;

public class RuntimeThr extends Thread {
	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;

	public RuntimeThr(ConcurrentLinkedQueue<String> request, ConcurrentLinkedQueue<String> result) {
		this.requestQue = request;
		this.resultQue = result;
	}

	public void run() {
		while(true){
			System.out.println("Hello");
		}
	}
}