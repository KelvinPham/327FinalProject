import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.*;

public class Server {
	public static final int PORT = 1337;
	public static Lock fibLock = new ReentrantLock();
	public static Lock primeLock = new ReentrantLock();
	public static Lock randLock = new ReentrantLock();
	private static int option = 0;
	public static int nextEvenFib(int n) {
		if (n == 1) {
			return 2;
		} else if (n < 1) {
			return n;
		}
		int fibonacci = ((4 * nextEvenFib(n - 1)) + nextEvenFib(n - 2));
		return fibonacci;

	}

	public static int nextLargerRand(int prevNumber, int max) {
		Random random = new Random();
		int rand;
		do {
			rand = random.nextInt();
		} while (rand < prevNumber);

		return rand;
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

	public static void main(String[] args) {
		ServerSocket serverSocket;
		HashMap<String, Integer> a = new HashMap<String,Integer>();
		final HashMap pointer = a;
		a.put("evenFib", 1);
		a.put("nextPrime",1);
		try {
			serverSocket = new ServerSocket(PORT);
			int maximum = Integer.MAX_VALUE;
			while (true) {
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							Socket socket = serverSocket.accept();
							// try {

							System.err.println("client connected");

							BufferedReader in = new BufferedReader(
									new InputStreamReader(
											socket.getInputStream()));

							PrintWriter out = new PrintWriter(
									new OutputStreamWriter(
											socket.getOutputStream()), true);

							int prevNumber = 0;
							option = -1;
							option = Character.getNumericValue(in.read());

							switch(option){
							case 2:
								fibLock.lock();
								if(a.get("evenFib") == 16)
									a.put("evenFib", 1);
								int fibonacci = nextEvenFib(a.get("evenFib"));
								a.put("evenFib", a.get("evenFib")+1);
								System.out.println("Sending Fibonacci: "
										+ fibonacci);
								out.println(fibonacci);
							fibLock.unlock();
							break;
							case 3:
								randLock.lock();

								int newNumber = nextLargerRand(prevNumber,
										maximum);
								System.out.println("Sending next large random: "
										+ newNumber);
								out.println(newNumber);
								if (prevNumber >= maximum || prevNumber < 0) {
									prevNumber = 0;
								} else {
									prevNumber = newNumber;
								}
							randLock.unlock();
							break;
							case 4:
								primeLock.lock();
								int prime = nextPrime(a.get("nextPrime"));
								if(prime<0) {
									a.put("nextPrime", 1);
									prime = nextPrime(a.get("nextPrime"));
								}
								a.put("nextPrime", prime);
								System.out.println("Sending the prime number: "
										+ prime);
								out.println(prime);
								//primeNumber = prime;
							primeLock.unlock();
			
							break;
							}
							out.close();
							in.close();
							socket.close();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
				});
				thread.run();
				//thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}