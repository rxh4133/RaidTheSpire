package client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.ServerDataHandler;

public class HomeWindow extends JPanel{

	private static final long serialVersionUID = 0L;
	
	private ClientDataHandler dataHandler;
	
	private JButton hostButton;
	private JLabel ipLabel;
	private JButton connectButton;
	private JTextField hostField;
	
	private JTextField nameField;
	
	public HomeWindow(ClientDataHandler cdh) {
		dataHandler = cdh;
		hostButton = new JButton("Host");
		hostButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServerDataHandler sdh = new ServerDataHandler();
				System.out.println("Server started: " + sdh);
				dataHandler.connect("localhost", nameField.getText());
				System.out.println("fug");
			}
		});
		String address = "Lan Address Not Found";
		try {
			address = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		ipLabel = new JLabel(address);
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataHandler.connect(hostField.getText(), nameField.getText());
			}
		});
		hostField = new JTextField(60);
		
		nameField = new JTextField();
		
		this.setLayout(new GridLayout(3,2));
		this.add(new JLabel("Your Name: "));
		this.add(nameField);
		this.add(hostButton);
		this.add(ipLabel);
		this.add(connectButton);
		this.add(hostField);
	}
	
}
