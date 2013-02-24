package net.msbone.epicbot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Server {

	private boolean connected = false;
	
	private BufferedReader inFromServer = null;
	private DataOutputStream outToServer = null;
	private Socket socket = null;
	private Json json;
	
	public Server(String IP, int PORT, String NAME) {
		connect(IP, PORT, NAME);
		clientLoop();
	}
	
	@SuppressWarnings("resource")
	private void connect(String IP, int PORT, String NAME) {
		if(connected) {
			System.out.println("Du kan ikke koble til server fordi du allerede har en tilkoblig til den, start clienten på nytt for å koble til igjen");
		}
		else {
		System.out.println(NAME + " vill no prøve å koble til serveren: "+IP+":"+PORT);
			try {
				Socket socket = new Socket(IP, PORT);
				outToServer = new DataOutputStream(socket.getOutputStream());
				inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socket.setKeepAlive(true);
			} catch (UnknownHostException e) {
				System.out.println("Klarte ikke å koble til server");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Klarte ikke å koble til server");
				e.printStackTrace();
			}
				connected = true;
		}
	}
	
	public void sendMessage(String message) {
		System.out.println("Melding sendt til server: " + message);
		try {
			outToServer.writeBytes(message + '\n');
			outToServer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initstart(String NAME) {
		json = new Json();
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "connect");
		map.put("revision", "1");
		map.put("name", NAME);
		sendMessage(json.toServer(map));
	}
	
	private void clientLoop() {
		System.out.println("Client loop started");
		initstart("EpicBot");
		while(connected) {
			try {
				//All the fun happens here!
				String data = inFromServer.readLine();
				if(data != null) {
					//Vi har motatt data fra server, la oss sjekke den ut!
					System.out.println("Data motatt fra server: " + data);
				}
				Thread.sleep(100);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public boolean isConnected() {
		return connected;
	}	
}
