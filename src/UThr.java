import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UThr extends Thread {
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
			if (x == 0) {
				//EVEN
			}
			if (x == 1) {
				//ODD
			}
			if (x == 2) {
				//EVEN FIBB
			}
			if (x == 3) {
				//RAND
			}
			if (x == 4) {
				//PRIME
			}
		}
	}
}