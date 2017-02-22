import java.net.*;
import java.io.*;
import java.util.Scanner;

public class PiConnect {
	public static void main(String[] args) throws IOException { // takes (address), (port)
		if (args.length == 0 || args.length > 2) {
			System.err.println("Usage: java PiConnectServer [port number]");
			System.exit(-1);
		}
		String address = args[0];
		int port = 7777;
		if (args.length == 2) port = Integer.parseInt(args[1]);
		try {
			// ESTABLISH A CONNECTION WITH HOST
			System.out.print("Connecting to "+address+":"+port+"... \n");
			Socket client = new Socket(address, port);
			System.out.println("Connected!\n");
			
			// CREATE STREAMS
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			
			// ACCEPT Greeting
			System.out.println(in.readUTF()+"\n");
			
			// SEND Command LOOP
			String command;
			Scanner userInput;
			do {
				userInput = new Scanner(System.in);
				System.out.print("> ");
				command = userInput.nextLine();
				out.writeUTF(command);
			} while(!(command.equals("exit") || command.equals("quit") || command.equals("disconnect")));
			
			client.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}