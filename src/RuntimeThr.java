import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class RuntimeThr extends Thread implements Runnable{
	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;
	public Socket clientSocket;

	public RuntimeThr(ConcurrentLinkedQueue<String> request, ConcurrentLinkedQueue<String> result) {
		this.requestQue = request;
		this.resultQue = result;
		try {
			clientSocket = new Socket("localhost", 1337);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			while(!requestQue.isEmpty()) {
				String request = requestQue.peek();
				
				System.out.println("REQUEST: " + request);


				if(request.equals("0") || request.equals("1")) {
					new LocalThr(request, resultQue).run();
					//RuntimeThr.evenOddSequence++;

					//System.out.println("Spawned local thr");
				} else {
					if(request != null) {
						new NetworkThr(request, resultQue, clientSocket).run();
					}
					
				}
				requestQue.remove();
			}
		}
	}
}