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
			request = Integer.toString(n.getCommand());
			String[] commandString = n.toString().split(" ");
			String command= commandString[commandString.length-1];
			outToServer.writeBytes(request + '\n');
			String resultValue=inFromServer.readLine();
			//n.setCommand(Integer.parseInt(asd));
			n.setMessage(command, Integer.parseInt(resultValue));
			//System.out.println("n:"+ asd);
			resultQue.add(n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
