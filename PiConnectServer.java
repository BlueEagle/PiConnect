import java.net.*;
import java.io.*;
import java.util.*;

public class PiConnectServer extends Thread {
	// ESTABLISH INITIAL LISTENING SOCKET TO ACCEPT INCOMING CONNECTIONS
	ServerSocket listeningSocket;
	
	public PiConnectServer(int port) throws IOException {
		listeningSocket = new ServerSocket(port);
	}
	
	public void run() {
		while(true) {
			try {
				// Connection handler
				System.out.print("\nWaiting for a connection on port "+listeningSocket.getLocalPort()+"... \n");
				Socket server = listeningSocket.accept();
				System.out.println("Connected!");
				System.out.println("Client: "+server.getRemoteSocketAddress()+"\n");
				
				// CREATE STREAMS
				DataInputStream in = new DataInputStream(server.getInputStream());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				
				// SEND Greeting
				out.writeUTF("Thank you for connecting to "+server.getLocalSocketAddress()+"!");
				
				// OTHER THINGS
				String command; // Will store the command sent by the client
				String[] commandArray; // Will be the command after being broken up into individual words
				
				// ENTER CONNECTION LOOP
				do {
					// READ command into command String
					command = in.readUTF(); // Read input from the client
					System.out.println("\nUser entered command: "+command); // Print out client input
					commandArray = CommandHandler.breakCommand(command);// a command that will break a string, using space delimiter
					CommandHandler.execute(commandArray); // handles the command array.
					
				}while(!(command.toLowerCase().equals("exit") || command.toLowerCase().equals("quit") || command.toLowerCase().equals("disconnect")));
				
				// CLOSE CONNECTION
				server.close();
			} catch(IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	// THREAD RUNNER < Essentially just executes the program in a new thread.
	public static void main(String[] args) {
		if(args.length > 1) {
			System.err.println("Usage: java PiConnectServer [port number]");
			System.exit(-1);
		}
		int port = 7777;
		if(args.length == 1) port = Integer.parseInt(args[0]);
		
		try {
			Thread t = new PiConnectServer(port);
			t.start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}