import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
	public static final int PORT = 1337;

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
		try {
			serverSocket = new ServerSocket(PORT);
			int maximum = 2147483647;
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
							int primeNumber = 1;
							for (int j = 1; j <= 5; j++) {

								int fibonacci = nextEvenFib(j);
								System.out.println("Sending Fibonacci: "
										+ fibonacci);
								out.println(fibonacci);
							}
							for (int j = 1; j <= 5; j++) {

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
							}
							for (int j = 1; j <= 5; j++) {
								int prime = (int) nextPrime(primeNumber);
								System.out.println("Sending the prime number: "
										+ prime);
								out.println(prime);
								primeNumber = prime;
							}

							out.close();
							in.close();
							socket.close();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
				});
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}