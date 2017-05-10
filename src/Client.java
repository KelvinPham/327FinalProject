import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Client {
	private Socket socket;


	public Client(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
	}

	private void close() throws IOException  {
		socket.close();
	}

	public static void main(String[] args) throws InterruptedException {
		ConcurrentLinkedQueue<Node> requestQue = new ConcurrentLinkedQueue<Node>();
		ConcurrentLinkedQueue<Node> resultQue = new ConcurrentLinkedQueue<Node>();
		int uThrNumb= 8;
		Scanner in = new Scanner(System.in);
		try {
			Client client = new Client("localhost", 1337);

			RuntimeThr rt =new RuntimeThr(requestQue, resultQue);
			Thread runtime = new Thread(rt);
			runtime.start();

			Thread uThr = null;
			Thread[] ts = new Thread[8];


			for(int i = 0; i < ts.length; i++)
				ts[i] = new Thread(new UThr(requestQue, resultQue));

			for(int i = 0; i < ts.length; i++)
				ts[i].start();
			runtime.join();
			client.close();
		}catch (IOException ioe) {
			System.err.print(ioe.getMessage());
		}
		finally {
			in.close();
		}
	}
}