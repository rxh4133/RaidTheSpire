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
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
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
						dataHandler.removePlayer(player);
						done = true;
					} catch (IOException e) {
						e.printStackTrace();
						dataHandler.removePlayer(player);
						done = true;
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
			//System.out.println("SMR: " + message.text);
			if(message.textEquals("pjoin")) {
				player = dataHandler.createPlayer(this, message.data);
				if(player == null) {
					send(new Message("Name Taken", null));
				}
			}else if(message.textEquals("prend")) {
				dataHandler.readyPlayerToEndTurn(player);
			}else if(message.textEquals("prfight")) {
				dataHandler.readyPlayerToStartFight(player);
			}else if(message.textEquals("prgame")) {
				dataHandler.readyPlayerToStartGame(player);
			}else if(message.textEquals("pcard")) {
				dataHandler.playCard(message.data, player);
			}else if(message.textEquals("rewardchoice")) {
				dataHandler.handleRewardChoice(message.data, player);
			}
		}
		
	}
	
	public void send(Message mess) {
		try {
			oos.writeObject(mess);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ClientHandler && ((ClientHandler) obj).client.equals(client);
	}
}
