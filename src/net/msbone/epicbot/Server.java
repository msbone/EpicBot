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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Server {

	private boolean connected = false;
	
	private ArrayList<ArrayList> players = new ArrayList<ArrayList>();
	private ArrayList<ArrayList> players2 = new ArrayList<ArrayList>();
	
	private BufferedReader inFromServer = null;
	private static DataOutputStream outToServer = null;
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
	
	public static void sendMessage(String message) {
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
	
	private void clientLoop() {
		System.out.println("Client loop startet");
		initstart("SmartBot");
		while(connected) {
			try {
				//All the fun happens here!
				String data = inFromServer.readLine();
				if(data != null) {
					System.out.println(data);
					//Vi har motatt data fra server, la oss sjekke den ut!
					Type type =  new TypeToken<Map<String, Object>>(){}.getType();
					Gson gson = new Gson();
					Map<String, Object> map =  gson.fromJson(data, type);
					
					String message = null;
					message = (String) map.get("message");
					
					if(message.isEmpty()) {
						System.out.println("Vi dør!");
						
					}
					
					if(message.equals("connect") && connected == false) {
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
						    //Sjekker hvor mange spillere som er med
								
						        players.clear();
								players.addAll((Collection<ArrayList>) map.get("players"));
						      
								 System.out.println("Antall spillere " +players.size());
								 
							//Sender melding om att vi er klar og velger våpen
							Loadout.loadout(kart, lines);
							System.out.println(data);
							
						}
						else {
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
							
							
							System.out.println("Runde " + runde + "er startet");
							//Sjekke om det er min tur?
					        players.clear();
					        players2.clear();
							players.addAll((Collection<ArrayList>) map.get("players"));
							Object spillerdata1 = players.get(0);
							String spillerdata = spillerdata1.toString();
							String[] spillerdata2 = spillerdata.split(",");
							String spillerdata3 = spillerdata2[4];
							String[] spillernavn = spillerdata3.split("=");
							String currentplayer = spillernavn[1];
							if(currentplayer.equals("SmartBot")) {
								System.out.println("Min tur!");
								//La oss sjekke hvor vi kan flytte oss
								String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
								String[] spillerpos2 = spillerpos.split("=");
								String spillerpo3 = spillerpos2[1];
								System.out.println(spillerpo3);
								System.out.println("Vår spiller er her:");
								//Henter ut x/j og y/k 
								String[] spillerpos4 = spillerpo3.split(",");
								String spillerposJ = spillerpos4[0].replace(" ", "");
								String spillerposK = spillerpos4[1].replace(" ", "");;
								
								int posJ = Integer.parseInt(spillerposJ);
								int posK = Integer.parseInt(spillerposK);
								
								
								
								//Top-right = J - 1
								//Bot-right = K + 1
								//Top-left = K - 1
								//Bot-left = J + 1
								
								for(int move_count = 0; move_count < 3; move_count++){
								//Top
								int blockInfrontJ = posJ + 1;
								int blockInfrontK = posK + 1;
								
								//Bot
								int blockInbackJ = posJ - 1;
								int blockInbackK = posK - 1;
								
								//Left
								int blockInleftTopK = posK - 1;
								int blockInleftTopJ = posJ;
								
								int blockInleftBotK = posK + 1;
								int blockInleftBotJ = posJ;
								
								//Right
								int blockInrightTopK = posK;
								int blockInrightTopJ = posJ - 1;
								
								int blockInrightBotK = posK + 1;
								int blockInrightBotJ = posJ;
								
								
								
								System.out.println("The player is standing on " + kart[posJ][posK] + "at" + posJ + ":" + posK);
								Map<String, String> move = new HashMap<String, String>();
								if(walkable(blockInfrontJ, blockInfrontK, kart)) {
									//Front / down
									System.out.println("Moving to " + kart[blockInfrontJ][blockInfrontK] + " at " + blockInfrontJ + ":" + blockInfrontK);
									move.put("message", "action");
									move.put("type", "move");
									move.put("direction", "down");
									posK = blockInfrontK;
									posJ = blockInfrontJ;
								}
								else if(walkable(blockInbackJ, blockInbackK, kart)) {
									//Back / up
									System.out.println("Moving to " + kart[blockInbackJ][blockInbackK] + " at " + blockInfrontJ + ":" + blockInfrontK);
									move.put("message", "action");
									move.put("type", "move");
									move.put("direction", "up");
									posK = blockInbackK;
									posJ = blockInbackJ;
								}
								else if(walkable(blockInrightTopJ, blockInrightTopK, kart)) {
									//TOP-RIGHT
									System.out.println("Moving to " + kart[blockInrightTopJ][blockInrightTopK] + " at " + blockInrightTopJ + ":" + blockInrightTopK);
									move.put("message", "action");
									move.put("type", "move");
									move.put("direction", "right-up");
									posK = blockInrightTopK;
									posJ = blockInrightTopJ;
								}
								else if(walkable(blockInrightBotJ, blockInrightBotK, kart)) {
									//BOT-RIGHT
									System.out.println("Moving to " + kart[blockInrightBotJ][blockInrightBotK] + " at " + blockInrightBotJ + ":" + blockInrightBotK);
									move.put("message", "action");
									move.put("type", "move");
									move.put("direction", "right-down");
									posK = blockInrightBotK;
									posJ = blockInrightBotJ;
								}
								else if(walkable(blockInleftTopJ, blockInleftTopK, kart)) {
									//TOP-LEFT
									System.out.println("Moving to " + kart[blockInleftTopJ][blockInleftTopK] + " at " + blockInleftTopJ + ":" + blockInleftTopK);
									move.put("message", "action");
									move.put("type", "move");
									move.put("direction", "left-up");	
									posK = blockInleftTopK;
									posJ = blockInleftTopJ;
								}
								else if(walkable(blockInleftBotJ, blockInleftBotK, kart)) {
									//BOT-LEFT
									System.out.println("Moving to " + kart[blockInleftBotJ][blockInleftBotK] + " at " + blockInleftBotJ + ":" + blockInleftBotK);
									move.put("message", "action");
									move.put("type", "move");
									move.put("direction", "left-down");
									posK = blockInleftBotK;
									posJ = blockInleftBotJ;
								}
								else{
									move.put("message", "action");
									move.put("type", "pass");
									move.put("direction", "left-down");
									posK = blockInleftBotK;
									posJ = blockInleftBotJ;
								}
									String json = gson.toJson(move);
									sendMessage(json);
								}
							}
							System.out.println(spillernavn[1]);
							System.out.println(spillerdata);
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
	
	private boolean walkable(int j, int k, Object[][] kart){
		int aSize = kart[0].length;
		System.out.println("aSize = " + aSize);
		if(j >= 0 && k >= 0 && j < aSize && k < aSize){
			if(!kart[j][k].equals("O") && !kart[j][k].equals("V") && !kart[j][k].equals("S")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
}
