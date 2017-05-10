import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.*;
import java.net.Socket;

public class NetworkThr {
	String request;
	ConcurrentLinkedQueue<Node> resultQue;
	Socket sock;
	Node n;
	public NetworkThr (Node cmd, ConcurrentLinkedQueue<Node> result, Socket c) {
		this.n = cmd;
		this.resultQue = result;
		this.sock = c;
	}

	public void run() {
		try {
			DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			outToServer.writeBytes(request + '\n');
			n.setCommand(Integer.parseInt(inFromServer.readLine()));
			resultQue.add(n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
