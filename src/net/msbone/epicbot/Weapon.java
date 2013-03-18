package net.msbone.epicbot;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class Weapon {

	private static String mainWeapon;
	private static String secWeapon;
	
	private static int mainWeaponLvL = 1;
	private static int secWeaponLvL = 1;
	
	private static int laserRange = 5;
	
	public static void setWeapons(String main, String sec) {
		mainWeapon = main;
		secWeapon = sec;
	}
	
	public static void upgrade(String weapon) {
		//TODO check if we can acctual upgrade the weapon
		
		if(weapon.equals(mainWeapon)) {
			mainWeaponLvL += 1;
			Gson gson = new Gson();
			Map<String, String> moveMessage = new HashMap<String, String>();
			moveMessage.put("message", "action");
			moveMessage.put("type", "upgrade");
			moveMessage.put("weapon", weapon);
			String json = gson.toJson(moveMessage);
			Client.sendMessage(json);
		}
		if(weapon.equals(secWeapon)) {
			secWeaponLvL += 1;
			Gson gson = new Gson();
			Map<String, String> moveMessage = new HashMap<String, String>();
			moveMessage.put("message", "action");
			moveMessage.put("type", "upgrade");
			moveMessage.put("weapon", weapon);
			String json = gson.toJson(moveMessage);
			Client.sendMessage(json);
		}
		
	}
	
	public static String mainWeapon() {
		return mainWeapon;
	}
	
	public static String secWeapon() {
		return secWeapon;
	}
	
	public static boolean InRange(int posJ, int posK, int enemyJ, int enemyK, String weapon, Object[][] kart){
		//Do a check if player standing at posK/posJ can attack using weapon
		
		if(weapon.equals("laser")) {
			System.out.println("Laser");
			
			int topJ;
			int topK;
			boolean top = true;
			
			int botJ;
			int botK;
			boolean bot = true;
			
			
			int lefttopJ;
			int lefttopK;
			boolean lefttop = true;
			
			int leftbotJ;
			int leftbotK;
			boolean leftbot = true;
			
			int righttopJ;
			int righttopK;
			boolean righttop = true;
			
			int rightbotJ;
			int rightbotK;
			boolean rightbot = true;
			
			
			
			int x = 0;
			while(x < laserRange) {
				
				//Move to all the sides
				topJ = posJ + x;
				topK = posK + x;
				
				
				botJ = posJ - x;
				botK = posK - x;
				
				lefttopJ = posJ;
				lefttopK = posK - x;
				
				leftbotJ = posJ + x;
				leftbotK = posK;
				
				righttopJ = posJ - x;
				righttopK = posK;
				
				rightbotJ = posJ + x;
				rightbotK = posK;
				
				System.out.println(topJ + " - " + topK);
				if(top && !canShoot(Kartet.mapSize(), topJ, topK)) {
					top = false;
				}
				if(bot && !canShoot(Kartet.mapSize(), botJ, botK)) {
					bot = false;
				}
				if(lefttop && !canShoot(Kartet.mapSize(), lefttopJ, lefttopK)) {
					lefttop = false;
				}
				if(leftbot && !canShoot(Kartet.mapSize(), leftbotJ, leftbotK)) {
					leftbot = false;
				}
				if(righttop && !canShoot(Kartet.mapSize(), righttopJ, righttopK)) {
					righttop = false;
				}
				if(rightbot && !canShoot(Kartet.mapSize(), rightbotJ, rightbotK)) {
					rightbot = false;
				}
				
				if(top && topJ == enemyJ && topK == enemyK) {
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
				
				if(bot && botJ == enemyJ && botK == enemyK) {
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
				
				if(lefttop && lefttopJ == enemyJ && lefttopK == enemyK) {
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
				
				if(leftbot && leftbotJ == enemyJ && leftbotK == enemyK) {
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
				
				if(righttop && righttopJ == enemyJ && righttopK == enemyK) {
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
				
				if(rightbot && rightbotJ == enemyJ && rightbotK == enemyK) {
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
	
public static boolean canShoot(int size, int j, int k) {
	if(j < 0 || k < 0 || j >= size || k >= size) {
		return false;
	}
	else {
		Object kart[][] = Kartet.kart;
		if(kart[j][k].equals("O")) {
			System.out.println("Hit Stone");
			return false;
		}
	}
	return true;
}
	
	
	
}
