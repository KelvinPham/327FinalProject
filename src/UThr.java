import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UThr extends Thread implements Runnable {
	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;
	public UThr(ConcurrentLinkedQueue<String> request, ConcurrentLinkedQueue<String> result){
		this.requestQue = request;
		this.resultQue = result;
	}
	public void run() {
		Random ran = new Random(); // Creates random object
		for (int i = 0; i < 20; i++) {
			int x = ran.nextInt(5); // Chooses random number from 0 - 4
			switch(x){
			case 0:
				requestQue.add("0");
				break;

			case 1:
				requestQue.add("1");
				break;

			case 2:
				requestQue.add("2");
				break;

			case 3:
				requestQue.add("3");
				break;

			case 4:
				requestQue.add("4");
				break;
			}
		}
	}
}