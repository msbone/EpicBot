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

public class Client {

	private boolean connected = false;
	
	private BufferedReader inFromServer = null;
	private static DataOutputStream outToServer = null;
	private Socket socket = null;
	
	
	
	public Client(String IP, int PORT, String NAME) {
		connect(IP, PORT, NAME);
		clientLoop();
	}
	
	
	@SuppressWarnings("resource")
	private void connect(String IP, int PORT, String NAME) {
		if(connected) {
			System.out.println("The client can not connect to the server. Try to restart the client/server");
		}
		else {
		System.out.println(NAME + " will try to join the server: "+IP+":"+PORT);
			try {
				Socket socket = new Socket(IP, PORT);
				outToServer = new DataOutputStream(socket.getOutputStream());
				inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socket.setKeepAlive(true);
			} catch (UnknownHostException e) {
				System.out.println("Can not connect to the server");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Can not connect to the server");
				e.printStackTrace();
			}
				connected = true;
		}
	}
	
	public static void sendMessage(String message) {
		System.out.println("Message sent to server: " + message);
		try {
			outToServer.writeBytes(message + '\n');
			outToServer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initstart(String NAME) {
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
		initstart(EpicBot.name);
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
					
					if(message == null) {
						System.out.println("Error from server");
						System.out.println(data);
					}
					
					else if(message.equals("connect") && connected == false) {
						System.out.println("Server svar!");
						if((boolean) map.get("status")){
						System.out.println("We are connected to the server");
						}
						else {
							System.out.println("The server did not accept us, something is wrong");
						}
					}
					else if(message.equals("gamestate")) {
						//Spiller runde :)
						Double runde = (Double) map.get("turn");
						if(runde == 0.0) {
							//Før vi starter :) Gjør oss klar!
							//Må lese kartet her!
						    //Sjekker hvor mange spillere som er med
								 
							//Sender melding om att vi er klar og velger våpen
							Loadout.loadout(Kartet.readmap(map), Kartet.mapSize());
							System.out.println(data);
							
						}
						else {
							//Må lese kartet her!
							
							Object kart[][] = Kartet.readmap(map);
							
							System.out.println("Rounde " + runde + " is started");
							//Sjekke om det er min tur?
							if(Players.isMe(map)) {
								System.out.println("Lets move!");
								//La oss sjekke hvor vi kan flytte oss
								
								int posJ = Players.myPosJ(map);
								int posK = Players.myPosK(map);
								
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
								
								
								
								System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
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
						}
					}
					else if(message.equals("endturn")) {
						System.out.println("Rounde is done");
						
					}
					else {
						//Unknown message!
						System.out.println("Ukjent data motatt fra server " + data);
					}
					
				}
				Thread.sleep(500);
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
