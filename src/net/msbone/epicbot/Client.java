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
import java.util.Random;

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
								int posJ = Players.myPosJ(map);
								int posK = Players.myPosK(map);
								
								System.out.println("Lets move!");
								
								Random generator = new Random();
								Movement movement = new Movement();
								//La oss sjekke hvor vi kan flytte oss
								for(int move_count = 0; move_count < 3; move_count++){
									//La oss sjekke hvilken valg vi har :)
									boolean didmove = false;
									while(didmove == false) {
									int num = generator.nextInt(6);
									if(num == 1) {didmove = movement.canMoveTop(true, posJ, posK, kart);}
									else if(num == 2) {didmove = movement.canMoveBot(true, posJ, posK, kart);} 
									else if(num == 3) {didmove = movement.canMoveLeftTop(true, posJ, posK, kart);} 
									else if(num == 4) {didmove = movement.canMoveLeftBot(true, posJ, posK, kart);} 
									else if(num == 5) {didmove = movement.canMoveRightTop(true, posJ, posK, kart);} 
									else if(num == 6) {didmove = movement.canMoveRightBot(true, posJ, posK, kart);} 
									else{System.out.println(num); didmove = false;}
									}
									Thread.sleep(10);
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
				Thread.sleep(200);
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
		
}
