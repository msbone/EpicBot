package net.msbone.epicbot;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class Weapon {

	public static boolean InRange(int posJ, int posK, int enemyJ, int enemyK, String weapon, Object[][] kart){
		//Do a check if player standing at posK/posJ can attack using weapon
		
		int laserRange = 4;
		
		//Top-right = J - 1
		//Bot-right = K + 1
		//Top-left = K - 1
		//Bot-left = J + 1
		
		if(weapon.equalsIgnoreCase("laser") && 1==1) {
			int topJ = posJ - laserRange;
			int topK = posK - laserRange;
			
			int botJ = posJ + laserRange;
			int botK = posK + laserRange;
						
			if(enemyJ < posJ && enemyK < posK && enemyJ > topJ && enemyK > topK) {
				//The point is over the player
				System.out.println("I have a player in sight, LETS SHOT");
				//FIRE
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				moveMessage.put("message", "action");
				moveMessage.put("type", "laser");
				moveMessage.put("direction", "up");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
				
				return true;
			}
			
			if(enemyJ > posJ && enemyK > posK && enemyJ < botJ && enemyK < botK) {
				//The point is under the player
				System.out.println("I have a player in sight");
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				moveMessage.put("message", "action");
				moveMessage.put("type", "laser");
				moveMessage.put("direction", "down");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
				return true;
			}
			else {
				System.out.println("No player in sight");
				System.out.println();
				
			}
			
		}
		return false;
	}
	

	
	
	
}
