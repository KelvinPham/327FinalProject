import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.concurrent.*;

public class RuntimeThr extends Thread implements Runnable{
	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;
	public Socket clientSocket;
	public LinkedList<UThr> uThrList;

	public RuntimeThr(ConcurrentLinkedQueue<Node> request, ConcurrentLinkedQueue<Node> result) {
		this.requestQue = request;
		this.resultQue = result;
		uThrList = new LinkedList<UThr>();

		try {
			clientSocket = new Socket("localhost", 1337);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addThrList(UThr u){
		uThrList.add(u);
	}

	public void run() {
		while(true){
			while(!requestQue.isEmpty()) {
				Node request = requestQue.peek();
				System.out.println("REQUEST: " + request);
				if(request.getCommand() == 0 || request.getCommand() == 1 ) {
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