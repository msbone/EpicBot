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
	
	private static int mortarRange = 2;
	
	
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
		
		
		if(weapon.equals("mortar")) {
			for(int x = 1; x < mortarRange+1; x++) {
					String shortest = "";
					
					int cur_distance = 100;
					int topJ = posJ + x;
					int topK = posK + x;

					int botJ = posJ - x;
					int botK = posK - x;
					
					int lefttopJ = posJ;
					int lefttopK = posK - x;
					
					int leftbotJ = posJ + x;
					int leftbotK = posK;
					
					int righttopJ = posJ - x;
					int righttopK = posK;
					
					int rightbotJ = posJ;
					int rightbotK = posK + x;
					
					//Which of this is the closest
					int top = (topJ+topK) - (enemyJ+enemyK);
					int bot = (botJ+botK) - (enemyJ+enemyK);
					int lefttop = lefttopJ+lefttopK - (enemyJ+enemyK);
					int righttop = righttopJ+righttopK - (enemyJ+enemyK);
					int leftbot = leftbotJ+leftbotK - (enemyJ+enemyK);
					int rightbot = rightbotJ+rightbotK - (enemyJ+enemyK);
					
					if(top < 0) {top = top *(-1);}
					if(bot < 0) {bot = bot *(-1);}
					if(lefttop < 0) {lefttop = lefttop *(-1);}
					if(righttop < 0) {righttop = righttop *(-1);}
					if(leftbot < 0) {leftbot = leftbot *(-1);}
					if(rightbot < 0) {rightbot = rightbot *(-1);}
					
					System.out.println(top + "-" + bot + "-" + lefttop + "-" + righttop + "-" + leftbot + "-" + rightbot);
					
					if(top < cur_distance) {
						shortest = "top";
						cur_distance = top;
					}
					if(bot < cur_distance) {
						shortest = "bot";
						cur_distance = bot;
					}
					if(lefttop < cur_distance) {
						shortest = "lefttop";
						cur_distance = lefttop;
					}
					if(righttop < cur_distance) {
						shortest = "righttop";
						cur_distance = righttop ;
					}
					if(leftbot < cur_distance) {
						shortest = "leftbot";
						cur_distance = leftbot;
					}
					if(rightbot < cur_distance) {
						shortest = "rightbot";
						cur_distance = rightbot;
					}
					
					
					
					///////////////////////////
					
					//Top-right = J - 1
					//Bot-right = K + 1
					//Top-left = K - 1
					//Bot-left = J + 1
					
					
					System.out.println(shortest);
					if(shortest.equals("top")) {
						//Send our minon top
						posJ --;
						posK --;
					}
					else if(shortest.equals("bot")) {
						//Send our minon bot
						posJ ++;
						posK ++;
						
					}
					else if(shortest.equals("lefttop")) {
						//Send our minon left top
						posK =- 1;
					}
					else if(shortest.equals("righttop")) {
						//Send our minon right top
						posJ =- 1;
					}
					else if(shortest.equals("leftbot")) {
						//Send our minon left bot
						posJ =+ 1;
						
					}
					else if(shortest.equals("rightbot")) {
						//Send our minon right bot
						posK =+ 1;
				}	
				
					if(cur_distance <= mortarRange) {
						//Calculate the distance in each access
						int distanceK = enemyK - posK;
						int distanceJ = enemyJ - posJ;
						//Convert to plus if negative
						
						if(distanceK < 0) {distanceK = distanceK * (-1);}
						if(distanceJ < 0) {distanceJ = distanceJ * (-1);}
						
						
						String path = distanceK+","+distanceJ;
						Gson gson = new Gson();
						Map<String, String> moveMessage = new HashMap<String, String>();
						moveMessage.put("message", "action");
						moveMessage.put("type", "mortar");
						moveMessage.put("coordinates", path);
						String json = gson.toJson(moveMessage);
						Client.sendMessage(json);
						return true;
					}
			}
		}
		
		
		
		if(weapon.equals("laser")) {
			//System.out.println("Laser");
			
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
				
				rightbotJ = posJ;
				rightbotK = posK + x;
				
				//System.out.println(topJ + " - " + topK);
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
			//System.out.println("Hit Stone");
			return false;
		}
	}
	return true;
}
	
	
	
}
