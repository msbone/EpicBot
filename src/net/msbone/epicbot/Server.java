package net.msbone.epicbot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
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
			System.out.println("Du kan ikke koble til server fordi du allerede har en tilkoblig til den, start clienten pï¿½ nytt for ï¿½ koble til igjen");
		}
		else {
		System.out.println(NAME + " vill no prï¿½ve ï¿½ koble til serveren: "+IP+":"+PORT);
			try {
				Socket socket = new Socket(IP, PORT);
				outToServer = new DataOutputStream(socket.getOutputStream());
				inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socket.setKeepAlive(true);
			} catch (UnknownHostException e) {
				System.out.println("Klarte ikke ï¿½ koble til server");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Klarte ikke ï¿½ koble til server");
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
		//Kjï¿½rer rett etter tilkobligen er godkjent
		Map<String, String> connect = new HashMap<String, String>();
		connect.put("message", "connect");
		connect.put("revision", "1");
		connect.put("name", NAME);
		Gson gson = new Gson();
		String json = gson.toJson(connect);
		sendMessage(json);
	}
	
	public void loadout(String weapon, String weapon_sec) {
		//Kjï¿½rer  nÃ¥r serveren er klar, velger vÃ¥pen
		Map<String, String> connect = new HashMap<String, String>();
		connect.put("message", "loadout");
		connect.put("primary-weapon", weapon);
		connect.put("secondary-weapon", weapon_sec);
		Gson gson = new Gson();
		String json = gson.toJson(connect);
		sendMessage(json);
	}
	
	private void clientLoop() {
		System.out.println("Client loop startet");
		initstart("SmartBot");
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
						System.out.println("Vi er koblet til serveren, venter på att serveren er klar");
						}
						else {
							System.out.println("Servere godtok ikke meldingen, her er noe rart!");
						}
					}
					else if(message.equals("gamestate")) {
						//Spiller runde :)
						Double runde = (Double) map.get("turn");
						if(runde == 0.0) {
							//Før vi starter :) Gjør oss klar!
							//Må lese kartet her!
							
							Type maptype =  new TypeToken<Map<String, Object>>(){}.getType();
							Map<String, Object> kartdata =  gson.fromJson(map.get("map").toString(), maptype);
							
							ArrayList<ArrayList> lines = new ArrayList<ArrayList>();
						 lines.addAll((Collection<ArrayList>) kartdata.get("data"));
						 
						Object[][] kart = new Object[lines.size()][lines.size()];
						 
						      for(int j = 0; j < lines.size(); j += 1) {
									ArrayList<ArrayList> line = new ArrayList<ArrayList>();
									line.addAll((Collection<ArrayList>) lines.get(j));
									for(int k = 0; k < lines.size(); k += 1) {
										kart[j][k] = line.get(k);
									}
						       }
						      
						
						    int grass = 0;
						    int tom = 0;
						    int explodium = 0;
						    int rubidium = 0;
						    int scrap = 0;
						    int rock = 0;
						    double mortar = 2;
						    double droid = 2;
						    double laser = 2;
						    
						   
						      
						    for(int c1 = 0; c1 < lines.size(); c1 += 1){
						    	for(int c2 = 0; c2 < lines.size(); c2 += 1){
						    		if(kart[c1][c2].equals("G")){grass += 1;}
						    		if(kart[c1][c2].equals("V")){tom += 1;}
						    		if(kart[c1][c2].equals("E")){explodium += 1;}
						    		if(kart[c1][c2].equals("R")){rubidium += 1;}
						    		if(kart[c1][c2].equals("C")){scrap += 1;}
						    		if(kart[c1][c2].equals("O")){rock += 1;}
						    	}
						    }
						    
						    if((grass/rock) <=  15){
						    	laser -= 1;
						    	droid -= 0.5;
						    }
						    
						    if((grass/tom) <= 15){
						    	droid -= 0.5;
						    }
						    
						    if(explodium <= rubidium || explodium < scrap){
						    	mortar -= 1;
						    }
						    
						    if(rubidium < explodium || rubidium < scrap){
						    	laser -= 1;
						    }
						    
						    if(scrap < explodium || scrap < rubidium){
						    	droid -= 1;
						    }
						    
						    double weapons[] = {mortar, droid, laser};
						    double largest = weapons[0];
						    double smallest = weapons[0];
						    String primary_weapon = "";
						    String secondary_weapon = "";
						    
						    for(int c3 = 0; c3 < weapons.length; c3 += 1){
						    	if(weapons[c3] > largest){
						    		largest = weapons[c3];
						    		if(c3 == 0){primary_weapon = "mortar";}
						    		if(c3 == 1){primary_weapon = "droid";}
						    		if(c3 == 2){primary_weapon = "laser";}
						    	}
						    	if(weapons[c3] < smallest){
						    		smallest = weapons[c3];
						    	}
						    	if(weapons[c3] > smallest && weapons[c3] < largest){
						    		if(c3 == 0){secondary_weapon = "mortar";}
						    		if(c3 == 1){secondary_weapon = "droid";}
						    		if(c3 == 2){secondary_weapon = "laser";}
						    	}
						    }
						    
						    System.out.println("grass: " + grass);
						    System.out.println("tom: " + tom);
						    System.out.println("explodium: " + explodium);
						    System.out.println("rubidium: " + rubidium);
						    System.out.println("scrap: " + scrap);
						    System.out.println("rock: " + rock);
						    
							//Sender melding om att vi er klar og velger våpen
							loadout(primary_weapon,secondary_weapon) ;
							System.out.println(data);
							
						}
						else {
							System.out.println("Runde " + runde + "er startet");
							//System.out.println(data);
						}
					}
					else if(message.equals("endturn")) {
						System.out.println("Runden er ferdig");
						
					}
					else {
						//Unknown message!
						//System.out.println("Ukjent data motatt fra server " + data);
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
