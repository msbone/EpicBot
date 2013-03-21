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
		//System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		//System.out.println("Will try to move TOP");
		int blockInfrontJ = posJ + 1;
		int blockInfrontK = posK + 1;
		if(walkable(blockInfrontJ, blockInfrontK)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				//System.out.println("Moving to " + kart[blockInfrontJ][blockInfrontK] + " at " + blockInfrontJ + ":" + blockInfrontK);
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
		if(walkable(blockInbackJ, blockInbackK)) {
			//Can move
			if(move){
				//System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
				//System.out.println("Will try to move BOT");
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				//System.out.println("Moving to " + kart[blockInbackJ][blockInbackK] + " at " + blockInbackJ + ":" + blockInbackK);
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
		//System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		//System.out.println("Will try to move LEFT TOP");
		int blockInleftTopK = posK - 1;
		int blockInleftTopJ = posJ;
		if(walkable(blockInleftTopJ, blockInleftTopK)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				//System.out.println("Moving to " + kart[blockInleftTopJ][blockInleftTopK] + " at " + blockInleftTopJ + ":" + blockInleftTopK);
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
		//System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		//System.out.println("Will try to move LEFT BOT");
		int blockInleftBotK = posK + 1;
		int blockInleftBotJ = posJ;
		if(walkable(blockInleftBotJ, blockInleftBotK)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				//System.out.println("Moving to " + kart[blockInleftBotJ][blockInleftBotK] + " at " + blockInleftBotJ + ":" + blockInleftBotK);
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
		//System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		//System.out.println("Will try to move RIGHT TOP");
		int blockInrightTopK = posK;
		int blockInrightTopJ = posJ - 1;
		if(walkable(blockInrightTopJ, blockInrightTopK)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				//System.out.println("Moving to " + kart[blockInrightTopJ][blockInrightTopK] + " at " + blockInrightTopJ + ":" + blockInrightTopK);
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
		//System.out.println("The player is standing on " + kart[posJ][posK] + " at " + posJ + ":" + posK);
		//System.out.println("Will try to move RIGHT BOT");
		int blockInrightBotK = posK + 1;
		int blockInrightBotJ = posJ;
		if(walkable(blockInrightBotJ, blockInrightBotK)) {
			//Can move
			if(move){
				//Move!
				Gson gson = new Gson();
				Map<String, String> moveMessage = new HashMap<String, String>();
				//System.out.println("Moving to " + kart[blockInrightBotJ][blockInrightBotK] + " at " + blockInrightBotJ + ":" + blockInrightBotK);
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
	
	public boolean walkable(int j, int k){
		Object[][] kart = Kartet.kart;
		int aSize = kart[0].length;
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
	
	//use this method to check if direction is inside the wrong direction array
	public boolean wrongC(String wrongD){
		for(int i = 0; i<6; i+=1){
			if(wrong[i] == wrongD){
				return false;
			}
		}
		return true;
	}
	
	//use this method to insert a wrong direction into the first open slot in the array
	public void wrongI(String wrongD){
		if(wrongD != ""){
			int i = 0;
			while(i<6){
				if(wrong[i] == null){
					wrong[i] = wrongD;
					i = 7;
				}
			}
		}
		else{
			for(int i2 = 0; i2<6; i2+=1){
				wrong[i2] = null;
			}
		}
	}
	
	public static String[] wrong = new String[6];
	
	public String[] path(int j1, int k1, int j2, int k2, int c3){
		
		int j2_d = j2;
		int j1_d = j1;
		int k2_d = k2;
		int k1_d = k1;
		
		if(j2_d < 0){j2_d = j2_d*-1;}
		if(j1_d < 0){j1_d = j1_d*-1;}
		if(k2_d < 0){k2_d = k2_d*-1;}
		if(k1_d < 0){k1_d = k1_d*-1;}
		
		int J = j2_d - j1_d;
		int K = k2_d - k1_d;
		int size = (J+K)*5+15;
		if(size < 0){size = size*-1;}
		int c1 = 0;
		int c2 = 0;
		int c4 = 0;
		String[] moves = new String[6]; //an array for all the moves going to be tested
		
		//This loop builds an array for our test path, 
		//which is the path the runner will try to follow.
		while(c4 < 3){ //while the J or the K coordinates is not equal to the goal
			System.out.println("test_loop");
			if((c1 < J && c2 < K || c4 > 6) && (moves[c3] == null && wrongC("down"))){
				System.out.println("down");
				System.out.println(j1 + "," + k1);
				c1 += 1;
				c2 += 1;
				j1 += 1;
				k1 += 1;
				System.out.println(j1 + "," + k1);
				if(walkable(j1, k1)){
					moves[c3] = "down";
					System.out.println("down walkable");
					wrongI("");
					c3 += 1;
				}
				else{
					wrongI("down");
					c1 -= 1;
					c2 -= 1;
					j1 -= 1;
					k1 -= 1;
				}
			}
			if((c1 > J && c2 > K || c4 > 6) && (moves[c3] == null && wrongC("up"))){
				System.out.println("up");
				
				c1 -= 1;
				c2 -= 1;
				j1 -= 1;
				k1 -= 1;
				System.out.println(j1 + "," + k1);
				if(walkable(j1, k1)){
					System.out.println("up walkable");
					moves[c3] = "up";
					wrongI("");
					c3 += 1;
				}
				else{
					wrongI("up");
					c1 += 1;
					c2 += 1;
					j1 += 1;
					k1 += 1;
				}
			}
			if((c1 < J || c4 > 6) && (moves[c3] == null && wrongC("left-down"))){
				System.out.println("left-down");
				
				c1 += 1;
				j1 += 1;
				System.out.println(j1 + "," + k1);
				if(walkable(j1, k1)){
					moves[c3] = "left-down";
					System.out.println("left-down walkable");
					wrongI("");
					c3 += 1;
				}
				else{
					wrongI("left-down");
					c1 -= 1;
					j1 -= 1;
				}
			}
			if((c2 < K || c4 > 6) && (moves[c3] == null && wrongC("right-down"))){
				System.out.println("right-down");
				
				c2 += 1;
				k1 += 1;
				System.out.println(j1 + "," + k1);
				if(walkable(j1, k1)){
					moves[c3] = "right-down";
					System.out.println("right-down walkable");
					wrongI("");
					c3 += 1;
				}
				else{
					wrongI("right-down");
					c2 -= 1;
					k1 -= 1;
				}
			}
			if((c1 > J || c4 > 6) && (moves[c3] == null && wrongC("right-up"))){
				System.out.println("right-up");
				
				c1 -= 1;
				j1 -= 1;
				System.out.println(j1 + "," + k1);
				if(walkable(j1, k1)){
					moves[c3] = "right-up";
					System.out.println("right-up walkable");
					wrongI("");
					c3 += 1;
				}
				else{
					wrongI("right-up");
					c1 += 1;
					j1 += 1;
				}
			}
			if((c2 > K || c4 > 6) && (moves[c3] == null && wrongC("left-up"))){
				System.out.println("left-up");
				
				c2 -= 1;
				k1 -= 1;
				System.out.println(j1 + "," + k1);
				if(walkable(j1, k1)){
					moves[c3] = "left-up";
					System.out.println("left-up walkable");
					wrongI("");
					c3 += 1;
				}
				else{
					wrongI("left-up");
					c2 += 1;
					k1 += 1;
				}
			}
			c4 += 1;
		}
		return moves;
	}

}
