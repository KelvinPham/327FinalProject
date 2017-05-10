import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.*;

public class Server {
	static int client = 0;
	public static Lock fibLock = new ReentrantLock();
	public static Lock primeLock = new ReentrantLock();
	public static Lock randLock = new ReentrantLock();
	public static void main(String[] args) throws Exception {
		System.out.println("Server Started");
		HashMap<String, Integer> a = new HashMap<String,Integer>();
		a.put("evenFib", 1);
		a.put("nextPrime",1);
		a.put("nextRand", 1);
		ServerSocket server = new ServerSocket(1337);//Creates server at port 1337
		try{
			while(true){
				new ClientConnection(server.accept() , ++client, a).start();//Creates a thread for each client
			}
		}
		finally {
			server.close();
		}
	}
	private static class ClientConnection extends Thread {
		private Socket socket;
		private int ClientNumber;
		HashMap<String, Integer> numberSet;
		private static ArrayList<Integer> primeList= new ArrayList<Integer>();
		Random rand = new Random();
		public ClientConnection(Socket connection, int client, HashMap<String, Integer> values){
			this.socket=connection;
			this.ClientNumber=client;
			this.numberSet= values;
			System.out.println("Client #"+ ClientNumber +  " connected to the server");
		}



		public void run(){
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				while(true){
					String input = in.readLine();
					int a= Integer.parseInt(input);
					switch(a){
						case 2://Returns a fibbonaci number
							fibLock.lock();
							if(numberSet.get("evenFib") == 16)
								numberSet.put("evenFib", 1);
							int fibonacci = nextEvenFib(numberSet.get("evenFib"));
							numberSet.put("evenFib", numberSet.get("evenFib")+1);
							System.out.println("Sending Fibonacci: "
									+ fibonacci);
							out.println(fibonacci);
							fibLock.unlock();
							break;
						case 3://Returns a random that is +1-10 value higher
							randLock.lock();
							int value =nextLargerRand(numberSet.get("nextRand"));
							out.println(value);
							numberSet.put("nextRand", value);
							randLock.unlock();
							break;
						case 4://Returns the next bigger prime number
							primeLock.lock();
							int prime = nextPrime(numberSet.get("nextPrime"));
							if(prime<0) {
								numberSet.put("nextPrime", 1);
								prime = nextPrime(numberSet.get("nextPrime"));
							}
							numberSet.put("nextPrime", prime);
							System.out.println("Sending the prime number: "
									+ prime);
							out.println(prime);
							//primeNumber = prime;
							primeLock.unlock();
							break;
						case 5://Closes the thread client
							out.close();
							in.close();
							break;
						default:
							out.println("Error");
					}
					if(input.equals("quit"))
						break;
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
			finally {
				try {
					System.out.println("Client #" +ClientNumber + " is closed");
					client--;
					socket.close();
				}
				catch (IOException e){
					System.out.println("Unable to close socket!");
				}
			}
		}
		public int nextLargerRand(int Rand){//Generates a random number that is at least 1 higher than old number
			Rand+= rand.nextInt(10000)+1;
			if(Rand> Integer.MAX_VALUE || Rand < 0)
				Rand=0;//Reset value
			return Rand;
		}
		public static int nextPrime(int n) {
			boolean isPrime = false;
			while (!isPrime) {
				n += 1;
				isPrime = true;
				if (n <= 2) {
					return 2;
				}
				for (int i = 2; i <= Math.ceil(Math.sqrt(n)); i++) {
					if (n % i == 0) {
						isPrime = false;
						break;
					}
				}
			}
			return n;
		}
		public static int nextEvenFib(int n) {
			if (n == 1) {
				return 2;
			} else if (n < 1) {
				return n;
			}
			int fibonacci = ((4 * nextEvenFib(n - 1)) + nextEvenFib(n - 2));
			return fibonacci;

		}
	}
}