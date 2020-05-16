// Java implementation for multithreaded chat client 
// Save file as Client.java 

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    final static int ServerPort = 1234;

    public static void main(String args[]) throws UnknownHostException, IOException {
	Scanner scn = new Scanner(System.in);

	// getting localhost ip
	InetAddress ip = InetAddress.getByName("localhost");

	// establish the connection

	// obtaining input and out streams

	Socket s;
	DataInputStream dis = null;
	DataOutputStream dos;

	boolean subscribed = false;
	try {
	    while (true) {

		if (subscribed) {
		    String response = dis.readUTF();
		    System.out.println("\nServer: \n" + response);
		} else {
		    System.out.println("\nEnter location:");
		    String msg = scn.nextLine();

		    s = new Socket(ip, ServerPort);
		    dis = new DataInputStream(s.getInputStream());
		    dos = new DataOutputStream(s.getOutputStream());

		    if (msg.startsWith("sub:")) {
			subscribed = true;
			System.out.println("Subscribed");
			dos.writeUTF(msg);
		    } else {
			dos.writeUTF(msg);
			String response = dis.readUTF();
			System.out.println("\nServer: \n" + response);
		    }
		}

	    }

	} catch (IOException e) {

	    e.printStackTrace();
	}

	// sendMessage thread
//	Thread sendMessage = new Thread(new Runnable() {
//	    @Override
//	    public void run() {
//		while (true) {
//
//		    // read the message to deliver.
//		    String msg = scn.nextLine();
//
//		    try {
//			// write on the output stream
//			dos.writeUTF(msg);
//		    } catch (IOException e) {
//			e.printStackTrace();
//		    }
//		}
//	    }
//	});

	// readMessage thread
//	Thread readMessage = new Thread(new Runnable() {
//	    @Override
//	    public void run() {
//
//		while (true) {
//		    try {
//			// read the message sent to this client
//			String msg = dis.readUTF();
//			System.out.println(msg);
//		    } catch (IOException e) {
//
//			e.printStackTrace();
//		    }
//		}
//	    }
//	});

//	sendMessage.start();
//	readMessage.start();

    }
}
