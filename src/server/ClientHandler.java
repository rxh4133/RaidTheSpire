package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import global.Message;
import global.Player;

public class ClientHandler {
	
	public Socket client;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	
	public boolean done;
	
	private ServerDataHandler dataHandler;
	
	private Player player;
	
	public ClientHandler(Socket clientSocket, ServerDataHandler dh) {
		dataHandler = dh;
		client = clientSocket;
		done = false;
		try {
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(){
			@Override
			public void run() {
				while(!done) {
					try {
						handleIncoming(ois.readObject());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		send(new Message("CONN", null));
	}
	
	//avert your eyes...
	public void handleIncoming(Object obj) {
		if(obj instanceof Message) {
			Message message = (Message) obj;
			if(message.textEquals("pjoin")) {
				player = dataHandler.createPlayer(this, message.data);
			}else if(message.textEquals("prend")) {
				dataHandler.readyPlayerToEndTurn(player);
			}else if(message.textEquals("prfight")) {
				dataHandler.readyPlayerToStartFight(player);
			}else if(message.textEquals("prgame")) {
				dataHandler.readyPlayerToStartGame(player);
			}else if(message.textEquals("pcard")) {
				if(!dataHandler.playersCanPlayCard()) {
					send(new Message("pcardfail", null));
				}
			}
		}
		
	}
	
	public void send(Message mess) {
		try {
			oos.writeObject(mess);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ClientHandler && ((ClientHandler) obj).client.equals(client);
	}
}
