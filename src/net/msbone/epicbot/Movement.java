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
		String[] moves = new String[500]; //an array for all the moves going to be tested
		
		//This loop builds an array for our test path, 
		//which is the path the runner will try to follow.
		while(c1 != J || c2 != K){ //while the J or the K coordinates is not equal to the goal
			if(c1 < J && c2 < K){
				moves[c3] = "down";
				c1 += 1;
				c2 += 1;
			}
			else if(c1 > J && c2 > K){
				moves[c3] = "up";
				c1 -= 1;
				c2 -= 1;
			}
			else if(c1 < J){
				moves[c3] = "left-down";
				c1 += 1;
			}
			else if(c2 < K){
				moves[c3] = "right-down";
				c2 += 1;
			}
			else if(c1 > J){
				moves[c3] = "right-up";
				c1 -= 1;
			}
			else if(c2 > K){
				moves[c3] = "left-up";
				c2 -= 1;
			}
			else{
				moves[c3] = "done";
			}
			c3 += 1;
		}
		return moves;
	}
/*	
 * 
 *  We need to keep track of which step in the process to reach our goal we are at
 *  through rounds, as our goal may be far away.
 */
	
	public boolean reachedgoal(int j1, int k1, int j2, int k2){
		if(j1 != j2){
			return false;
		}
		if(k1 != k2){
			return false;
		}
		else{
			return true;
		}
	}
	
	//Top-right = J - 1
	//Bot-right = K + 1
	//Top-left = K - 1
	//Bot-left = J + 1
	
	public int[] closestjk(int j, int k, int j2, int k2){
		int jf = j;
		int kf = k;
		int lowest = 0;
		int distance = 0;
		for(int i = 1; i<=6; i+=1){
			switch(i){
			case 1: jf-=1; distance = (j2-jf) + (k2-kf); lowest = distance; break;
			case 2: jf+=1; break;
			case 3: kf-=1; break;
			case 4: kf+=1; break;
			case 5: jf+=1; kf+=1; break;
			case 6: jf-=1; kf-=1; break;
			}
			if(distance < 0){
				distance = distance*-1;
			}
			if(lowest < 0){
				lowest = lowest*-1;
			}
			distance = (j2-jf) + (k2-kf);
			if(distance < lowest){lowest = distance;}
		}
		for(int i = 1; i<=6; i+=1){ 
			switch(i){
			case 1: jf-=1; distance = (j2-jf) + (k2-kf); break;
			case 2: jf+=1; break;
			case 3: kf-=1; break;
			case 4: kf+=1; break;
			case 5: jf+=1; kf+=1; break;
			case 6: jf-=1; kf-=1; break;
			}
			if(distance < 0){
				distance = distance*-1;
			}
			distance = (j2-jf) + (k2-kf);
			if(distance == lowest){
				j = jf;
				k = kf;
			}
		}
	int[] coords = new int[2];
	coords[0] = j;
	coords[1] = k;
	return coords;
	}
	
	//Top-right = J - 1
	//Bot-right = K + 1
	//Top-left = K - 1
	//Bot-left = J + 1
	
	public void runner(String[] moves, int length, int j, int k, int j2, int k2){
		//Movement walkable = new Movement();
		//for(int c1 = 0; c1 <= length; c1 += 1){
		int c1 = 0;
		while(c1 < length){
			if(moves[c1] != null){
				switch(moves[c1]){
					case "up":
						j -= 1;
						k -= 1;
						if(!walkable(j, k)){
							/*if not walkable, we need to find a tile nearby that is.
							 * From that tile, we need to find a new path.
							 * This means that we need to remove the steps after the step we are at
							 * and insert new steps.
							 */
							
							// find a tile nearby that is walkable
							j += 1;
							k += 1;
							int[] coords = closestjk(j, k, j2, k2);
							j = coords[0];
							k = coords[1];
							path(j, k, j2, k2, c1); //use this to find a new path and edit the array */					
							
						}
						break;
					case "down":
						j += 1;
						k += 1;
						if(!walkable(j, k)){
							j -= 1;
							k -= 1;
							int[] coords = closestjk(j, k, j2, k2);
							j = coords[0];
							k = coords[1];
							path(j, k, j2, k2, c1);
							
						}
						break;
					case "right-up":
						j -= 1;
						if(!walkable(j, k)){
							j += 1;
							int[] coords = closestjk(j, k, j2, k2);
							j = coords[0];
							k = coords[1];
							path(j, k, j2, k2, c1);
						}
						break;
					case "right-down":
						k += 1;
						if(!walkable(j, k)){
							k -= 1;
							int[] coords = closestjk(j, k, j2, k2);
							j = coords[0];
							k = coords[1];
							path(j, k, j2, k2, c1);
							
						}
						break;
					case "left-up":
						k -= 1;
						if(!walkable(j, k)){
							k += 1;
							int[] coords = closestjk(j, k, j2, k2);
							j = coords[0];
							k = coords[1];
							path(j, k, j2, k2, c1);
							
						}
						break;
					case "left-down":
						j += 1;
						if(!walkable(j, k)){
							j -= 1;
							int[] coords = closestjk(j, k, j2, k2);
							j = coords[0];
							k = coords[1];
							path(j, k, j2, k2, c1);
	
						}
						break;
				}
			}
	c1+=1;
		} 
	}
	
	public String[] pathfinder(int j, int k, int j2, int k2){
		
		String moves[] = path(j, k, j2, k2, 0);
		int length = moves.length;
		runner(moves, length, j, k, j2, k2);
		
		if(moves[0] == null){moves[0] = "down";}
		if(moves[1] == null){moves[1] = "down";}
		if(moves[2] == null){moves[2] = "down";}
		
		return moves;
	}
}
