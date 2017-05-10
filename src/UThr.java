import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UThr extends Thread implements Runnable {
	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;
	public UThr(ConcurrentLinkedQueue<Node> request, ConcurrentLinkedQueue<Node> result){
		this.requestQue = request;
		this.resultQue = result;
	}
	public void run() {
		Random ran = new Random(); // Creates random object
		for (int i = 0; i < 20; i++) {
			int x = ran.nextInt(5); // Chooses random number from 0 - 4
			switch(x){
			case 0:
				//public Node(int i, int cmdN, String r)
				requestQue.add(new Node(this.getId(), x , "NEXTEVEN"));
				break;

			case 1:
				requestQue.add(new Node(this.getId(), x , "NEXTODD"));
				break;

			case 2:
				requestQue.add(new Node(this.getId(), x , "NEXTEVENFIB"));
				break;

			case 3:
				requestQue.add(new Node(this.getId(), x , "NEXTLARGERRAND"));
				break;

			case 4:
				requestQue.add(new Node(this.getId(), x , "NEXTPRIME"));
				break;
			}
		}
		while(true){
			if(!resultQue.isEmpty())
			System.out.println(resultQue.poll().getMessage());
		}
	}
}