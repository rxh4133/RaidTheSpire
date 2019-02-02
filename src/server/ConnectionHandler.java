package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable {
	
	public static final int SERVER_PORT = 25646;
	
	public ArrayList<ClientHandler> clients;
	public ServerSocket server = null;
	public boolean done;
	
	private final ServerDataHandler dataHandler;
	
	public ConnectionHandler(ServerDataHandler dh) {
		clients = new ArrayList<ClientHandler>();
		done = false;
		dataHandler = dh;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(SERVER_PORT);
			while(!done) {
				Socket client = server.accept();
				clients.add(new ClientHandler(client, dataHandler));
			}
		} catch (SocketException se) {
			se.printStackTrace();
			System.out.println("Server socket most likely closed");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void stopAccepting() {
		done = true;
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("Server socket most likely closed but the exception is here");
			e.printStackTrace();
		}
	}

	
}
