package net.msbone.epicbot;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class Movement {

	//Top-right = J - 1
	//Bot-right = K + 1
	//Top-left = K - 1
	//Bot-left = J + 1
	
	public boolean canMoveTop(boolean move, int posJ, int posK, Object kart[][]) {
		System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		System.out.println("Will try to move TOP");
		int blockInfrontJ = posJ + 1;
		int blockInfrontK = posK + 1;
		if(walkable(blockInfrontJ, blockInfrontK, kart)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				System.out.println("Moving to " + kart[blockInfrontJ][blockInfrontK] + " at " + blockInfrontJ + ":" + blockInfrontK);
				moveMessage.put("message", "action");
				moveMessage.put("type", "move");
				moveMessage.put("direction", "down");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
			}
			return true;
		}
		
		return false;
	}
	public boolean canMoveBot(boolean move, int posJ, int posK, Object kart[][]) {
		int blockInbackJ = posJ - 1;
		int blockInbackK = posK - 1;
		if(walkable(blockInbackJ, blockInbackK, kart)) {
			//Can move
			if(move){
				System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
				System.out.println("Will try to move BOT");
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				System.out.println("Moving to " + kart[blockInbackJ][blockInbackK] + " at " + blockInbackJ + ":" + blockInbackK);
				moveMessage.put("message", "action");
				moveMessage.put("type", "move");
				moveMessage.put("direction", "up");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
			}
			return true;
		}
		
		return false;
	}
	public boolean canMoveLeftTop(boolean move, int posJ, int posK, Object kart[][]) {
		System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		System.out.println("Will try to move LEFT TOP");
		int blockInleftTopK = posK - 1;
		int blockInleftTopJ = posJ;
		if(walkable(blockInleftTopJ, blockInleftTopK, kart)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				System.out.println("Moving to " + kart[blockInleftTopJ][blockInleftTopK] + " at " + blockInleftTopJ + ":" + blockInleftTopK);
				moveMessage.put("message", "action");
				moveMessage.put("type", "move");
				moveMessage.put("direction", "left-down");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
			}
			return true;
		}
		
		return false;
	}
	public boolean canMoveLeftBot(boolean move, int posJ, int posK, Object kart[][]) {
		System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		System.out.println("Will try to move LEFT BOT");
		int blockInleftBotK = posK + 1;
		int blockInleftBotJ = posJ;
		if(walkable(blockInleftBotJ, blockInleftBotK, kart)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				System.out.println("Moving to " + kart[blockInleftBotJ][blockInleftBotK] + " at " + blockInleftBotJ + ":" + blockInleftBotK);
				moveMessage.put("message", "action");
				moveMessage.put("type", "move");
				moveMessage.put("direction", "left-up");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
			}
			return true;
		}
		
		return false;
	}
	public boolean canMoveRightTop(boolean move, int posJ, int posK, Object kart[][]) {
		System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		System.out.println("Will try to move RIGHT TOP");
		int blockInrightTopK = posK;
		int blockInrightTopJ = posJ - 1;
		if(walkable(blockInrightTopJ, blockInrightTopK, kart)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				System.out.println("Moving to " + kart[blockInrightTopJ][blockInrightTopK] + " at " + blockInrightTopJ + ":" + blockInrightTopK);
				moveMessage.put("message", "action");
				moveMessage.put("type", "move");
				moveMessage.put("direction", "right-down");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
			}
			return true;
		}
		
		return false;
	}
	public boolean canMoveRightBot(boolean move, int posJ, int posK, Object kart[][]) {
		System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		System.out.println("Will try to move RIGHT BOT");
		int blockInrightBotK = posK + 1;
		int blockInrightBotJ = posJ;
		if(walkable(blockInrightBotJ, blockInrightBotK, kart)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				System.out.println("Moving to " + kart[blockInrightBotJ][blockInrightBotK] + " at " + blockInrightBotJ + ":" + blockInrightBotK);
				moveMessage.put("message", "action");
				moveMessage.put("type", "move");
				moveMessage.put("direction", "right-up");
				String json = gson.toJson(moveMessage);
				Client.sendMessage(json);
			}
			return true;
		}
		
		return false;
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
