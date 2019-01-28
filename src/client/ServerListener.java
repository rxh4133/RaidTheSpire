package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import global.Message;
import global.Player;

public class ServerListener implements Runnable{
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	private ClientDataHandler dataHandler;
	
	private boolean done;

	public ServerListener(ClientDataHandler cdh) {
		dataHandler = cdh;
	}
	
	public void connect(String address) {
		Socket socket = null;
		try {
			socket = new Socket(address, 25646);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}
	
	public void sendMessage(Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleIncoming(Object obj) {
		if(obj instanceof Message) {
			Message message = (Message) obj;
			System.out.println("Message received " + message.text);
			if(message.textEquals("CONN")) {
				dataHandler.connected();
			}else if(message.textEquals("players")) {
				dataHandler.updateLobbyPlayers(message.data);
			}else if(message.textEquals("Name Taken")) {
				dataHandler.goToHome();
			}
		}
	}

	@Override
	public void run() {
		while(!done) {
			try {
				Object message = ois.readObject();
				handleIncoming(message);
			} catch (ClassNotFoundException e) {
				done = true;
				e.printStackTrace();
			} catch (IOException e) {
				done = true;
				e.printStackTrace();
			}
		}
	}
	
	
}
