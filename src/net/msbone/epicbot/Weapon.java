package net.msbone.epicbot;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class Weapon {

	public static boolean InRange(int posJ, int posK, int enemyJ, int enemyK, String weapon, Object[][] kart){
		//Do a check if player standing at posK/posJ can attack using weapon
		
		int laserRange = 5;
		
		if(weapon.equalsIgnoreCase("laser") && 1==1) {
			
			int topJfinal;
			int topKfinal;
			
			int botJfinal;
			int botKfinal;
			
			int lefttopJfinal;
			int lefttopKfinal;
			
			int leftbotJfinal;
			int leftbotKfinal;
			
			int righttopJfinal;
			int righttopKfinal;
			
			int rightbotJfinal;
			int rightbotKfinal;
			
			
			int x = 0;
			while(x < laserRange) {
				
				//Move to all the sides
				topJfinal = posJ + x;
				topKfinal = posK + x;
				
				botJfinal = posJ - x;
				botKfinal = posK - x;
				
				lefttopJfinal = posJ;
				lefttopKfinal = posK - x;
				
				leftbotJfinal = posJ + x;
				leftbotKfinal = posK;
				
				righttopJfinal = posJ - x;
				righttopKfinal = posK;
				
				rightbotJfinal = posJ + x;
				rightbotKfinal = posK;
				
				if(topJfinal == enemyJ && topKfinal == enemyK) {
					//Shoot down
					Gson gson = new Gson();
					Map<String, String> moveMessage = new HashMap<String, String>();
					moveMessage.put("message", "action");
					moveMessage.put("type", "laser");
					moveMessage.put("direction", "down");
					String json = gson.toJson(moveMessage);
					Client.sendMessage(json);
					return true;
				}
				
				if(botJfinal == enemyJ && botKfinal == enemyK) {
					//Shoot up
					Gson gson = new Gson();
					Map<String, String> moveMessage = new HashMap<String, String>();
					moveMessage.put("message", "action");
					moveMessage.put("type", "laser");
					moveMessage.put("direction", "up");
					String json = gson.toJson(moveMessage);
					Client.sendMessage(json);
					return true;
				}
				
				if(lefttopJfinal == enemyJ && lefttopKfinal == enemyK) {
					//Shoot left top
					Gson gson = new Gson();
					Map<String, String> moveMessage = new HashMap<String, String>();
					moveMessage.put("message", "action");
					moveMessage.put("type", "laser");
					moveMessage.put("direction", "left-up");
					String json = gson.toJson(moveMessage);
					Client.sendMessage(json);
					return true;
				}
				
				if(leftbotJfinal == enemyJ && leftbotKfinal == enemyK) {
					//Shoot left down
					Gson gson = new Gson();
					Map<String, String> moveMessage = new HashMap<String, String>();
					moveMessage.put("message", "action");
					moveMessage.put("type", "laser");
					moveMessage.put("direction", "left-down");
					String json = gson.toJson(moveMessage);
					Client.sendMessage(json);
					return true;
				}
				
				if(righttopJfinal == enemyJ && righttopKfinal == enemyK) {
					//Shoot right top
					Gson gson = new Gson();
					Map<String, String> moveMessage = new HashMap<String, String>();
					moveMessage.put("message", "action");
					moveMessage.put("type", "laser");
					moveMessage.put("direction", "right-up");
					String json = gson.toJson(moveMessage);
					Client.sendMessage(json);
					return true;
				}
				
				if(rightbotJfinal == enemyJ && rightbotKfinal == enemyK) {
					//Shoot right down
					Gson gson = new Gson();
					Map<String, String> moveMessage = new HashMap<String, String>();
					moveMessage.put("message", "action");
					moveMessage.put("type", "laser");
					moveMessage.put("direction", "right-down");
					String json = gson.toJson(moveMessage);
					Client.sendMessage(json);
					return true;
				}
				
				x++;
			}
			
		}
		return false;
	}
	

	
	
	
}
