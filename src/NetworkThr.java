import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.*;
import java.net.Socket;

public class NetworkThr {
	String request;
	ConcurrentLinkedQueue<String> resultQue;
	Socket sock;
	String result;
	
	public NetworkThr (String cmd, ConcurrentLinkedQueue<String> result, Socket c) {
		this.request = cmd;
		this.resultQue = result;
		this.sock = c;
	}

	public void run() {
		try {
			DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			outToServer.writeBytes(request + '\n');
			result = inFromServer.readLine();
			resultQue.add(request + " " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
