package net.msbone.epicbot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Server {

	private boolean connected = false;
	
	private BufferedReader inFromServer = null;
	private DataOutputStream outToServer = null;
	private Socket socket = null;
	
	public Server(String IP, int PORT, String NAME) {
		connect(IP, PORT, NAME);
		clientLoop();
	}
	
	@SuppressWarnings("resource")
	private void connect(String IP, int PORT, String NAME) {
		if(connected) {
			System.out.println("Du kan ikke koble til server fordi du allerede har en tilkoblig til den, start clienten p� nytt for � koble til igjen");
		}
		else {
		System.out.println(NAME + " vill no pr�ve � koble til serveren: "+IP+":"+PORT);
			try {
				Socket socket = new Socket(IP, PORT);
				outToServer = new DataOutputStream(socket.getOutputStream());
				inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socket.setKeepAlive(true);
			} catch (UnknownHostException e) {
				System.out.println("Klarte ikke � koble til server");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Klarte ikke � koble til server");
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
		//Kj�rer rett etter tilkobligen er godkjent
		Map<String, String> connect = new HashMap<String, String>();
		connect.put("message", "connect");
		connect.put("revision", "1");
		connect.put("name", NAME);
		Gson gson = new Gson();
		String json = gson.toJson(connect);
		sendMessage(json);
	}
	
	public void loadout(String weapon, String weapon_sec) {
		//Kj�rer  når serveren er klar, venlger våpen
		Map<String, String> connect = new HashMap<String, String>();
		connect.put("message", "loadout");
		connect.put("primary-weapon", weapon);
		connect.put("secondary-weapon", weapon_sec);
		Gson gson = new Gson();
		String json = gson.toJson(connect);
		sendMessage(json);
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
					Type type =  new TypeToken<Map<String, Object>>(){}.getType();
					Gson gson = new Gson();
					Map<String, Object> map =  gson.fromJson(data, type);
					
					String message = null;
					message = (String) map.get("message");
					if(message.equals("connect")) {
						System.out.println("Server svar!");
						if((boolean) map.get("status")){
						System.out.println("Vi er koblet til serveren, venter p� att serveren er klar");
						}
						else {
							System.out.println("Servere godtok ikke meldingen, her er noe rart!");
						}
					}
					else if(message.equals("gamestate")) {
						//Spiller runde :)
						Double runde = (Double) map.get("turn");
						if(runde == 0.0) {
							//F�r vi starter :) Gj�r oss klar!
							System.out.println("I am here!");
							
							//Sender melding om att vi er klar
							loadout("laser","droid");
							
							//hent mapdata og bygg koordinatsystem
							
						}
						else {
							System.out.println("Runde " + runde + "er startet");
							System.out.println(data);
							
						}
					}
					else if(message.equals("endturn")) {
						System.out.println("Runden er ferdig");
						
					}
					else {
						//Unknown message!
						System.out.println("Ukjent data motatt fra server " + data);
					}
					
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
