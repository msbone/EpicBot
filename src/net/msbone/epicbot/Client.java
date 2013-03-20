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
		//System.out.println("Message sent to server: " + message);
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
					//System.out.println(data);
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
							
							Kartet kartet = new Kartet();
							kartet.CreateWalkableArray(kart);
							
							
<<<<<<< HEAD
							//System.out.println("Rounde " + runde + " is started");
=======
							System.out.println("Roun de " + runde + " is started");
>>>>>>> 48497be1d84261a947597d7d8c57ace8b44a8ca6
							//Sjekke om det er min tur?
							if(Players.isMe(map)) {
								int posJ = Players.myPosJ(map);
								int posK = Players.myPosK(map);
								
								
								//La oss sjekke om vi kan angripe nokke :)
								
								//Comented out to save some console place
								System.out.println(Weapon.InRange(posJ, posK, Players.closestPlayerJ(map, posJ, posK), Players.closestPlayerK(map, posJ, posK), "laser", kart));
								
								
								//System.out.println("Lets move!");
								Random generator = new Random();
								Movement movement = new Movement();
								
								
								
								//La oss sjekke hvor vi kan flytte oss
								/*for(int move_count = 0; move_count < 3; move_count++){
									//La oss sjekke hvilken valg vi har :)
									boolean didmove = false;
									while(didmove == false) {
									int num = generator.nextInt(6);
									if(num == 0) {didmove = movement.canMoveTop(true, posJ, posK, kart);}
									else if(num == 1) {didmove = movement.canMoveBot(true, posJ, posK, kart);} 
									else if(num == 2) {didmove = movement.canMoveLeftTop(true, posJ, posK, kart);} 
									else if(num == 3) {didmove = movement.canMoveLeftBot(true, posJ, posK, kart);} 
									else if(num == 4) {didmove = movement.canMoveRightTop(true, posJ, posK, kart);} 
									else if(num == 5) {didmove = movement.canMoveRightBot(true, posJ, posK, kart);} 
									else{System.out.println(num); didmove = false;}
									}
									Thread.sleep(10);
								}
								*/
								
								int goalj = Players.closestPlayerJ(map, posJ, posK);
								int goalk = Players.closestPlayerK(map, posJ, posK);
								String moves[] = movement.pathfinder(posJ, posK, goalj, goalk);
								
								for(int i = 0; i<3; i+=1){
								
									Map<String, String> moveMessage = new HashMap<String, String>();    
									moveMessage.put("message", "action");
									moveMessage.put("type", "move");
									moveMessage.put("direction", moves[i]);
									String json = gson.toJson(moveMessage);
									Client.sendMessage(json);
								}
								
								
								String move_echo = "moves: " + moves[0] + " " + moves[1] + " " + moves[2] + " " + moves[3] + " " + moves[4] + " " + moves[5];
								System.out.println("our_pos: (" + posJ + "," + posK + ")");
								System.out.println("goal: (" + goalj + "," + goalk + ")");
								System.out.println(move_echo);
							}
						}
					}
					else if(message.equals("endturn")) {
						//System.out.println("Rounde is done");
						
					}
					else if(message.equals("action")) {
						
						
					}
					else {
						//Unknown message!
						//System.out.println("Ukjent data motatt fra server " + data);
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
