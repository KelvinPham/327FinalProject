import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {
          
    public static void main(String[] args) {
    	Socket socket;
    	BufferedReader in;
    	PrintStream out;
    	Scanner input = new Scanner(System.in);
        try {
            socket = new Socket("localhost", Server.PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		out = new PrintStream(socket.getOutputStream());
   
            for (int i = 1; i <= 5; ++i) {
            	 out.print(i + "\n");
                 out.flush();
            }
            
            for (int i = 1; i <= 5; ++i) {
            	String fibbonacci = in.readLine();                
            	System.out.println("Next Even Fibonacci Number is = " + fibbonacci);
            }
           

            for (int i = 1; i <= 5; ++i) {
           	 out.print(i + "\n");
                out.flush();
           }
           
           for (int i = 1; i <= 5; ++i) {
           	String nextLargerRand = in.readLine();               
           	System.out.println("Next larger random number is = " + nextLargerRand);
           }
           for (int i = 1; i <= 5; ++i) {
          	 out.print(i + "\n");
             out.flush();
          }
          
          for (int i = 1; i <= 5; ++i) {
          	 String prime = in.readLine();
                              
          	System.out.println("Next prime number is = " + prime);
          }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}