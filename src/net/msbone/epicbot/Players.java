package net.msbone.epicbot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Players {
	public static boolean isMe(Map map) {
		ArrayList<ArrayList> players = new ArrayList<ArrayList>();
				 players.clear();
		players.addAll((Collection<ArrayList>) map.get("players"));
		Object spillerdata1 = players.get(0);
		String spillerdata = spillerdata1.toString();
		String[] spillerdata2 = spillerdata.split(",");
		String spillerdata3 = spillerdata2[4];
		String[] spillernavn = spillerdata3.split("=");
		String currentplayer = spillernavn[1];
		if(currentplayer.equals(EpicBot.name)) {
			//System.out.println(currentplayer);
			return true;
		}
		return false;
	}
	
	public static int myPosJ(Map map) {
		ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		 players.clear();
players.addAll((Collection<ArrayList>) map.get("players"));
Object spillerdata1 = players.get(0);
String spillerdata = spillerdata1.toString();
String[] spillerdata2 = spillerdata.split(",");
String spillerdata3 = spillerdata2[4];
String[] spillernavn = spillerdata3.split("=");
String currentplayer = spillernavn[1];
if(currentplayer.equals(EpicBot.name)) {
	String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
	String[] spillerpos2 = spillerpos.split("=");
	String spillerpo3 = spillerpos2[1];
	//System.out.println(spillerpo3);
	//Henter ut x/j og y/k 
	String[] spillerpos4 = spillerpo3.split(",");
	String spillerposJ = spillerpos4[0].replace(" ", "");
	String spillerposK = spillerpos4[1].replace(" ", "");;
	
	int posJ = Integer.parseInt(spillerposJ);
	int posK = Integer.parseInt(spillerposK);
	
	return posJ;
	
}
return 0;
	}
	
	public static int myPosK(Map map) {
ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		 players.clear();
players.addAll((Collection<ArrayList>) map.get("players"));
Object spillerdata1 = players.get(0);
String spillerdata = spillerdata1.toString();
String[] spillerdata2 = spillerdata.split(",");
String spillerdata3 = spillerdata2[4];
String[] spillernavn = spillerdata3.split("=");
String currentplayer = spillernavn[1];
if(currentplayer.equals(EpicBot.name)) {
	String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
	String[] spillerpos2 = spillerpos.split("=");
	String spillerpo3 = spillerpos2[1];
	//System.out.println(spillerpo3);
	//Henter ut x/j og y/k 
	String[] spillerpos4 = spillerpo3.split(",");
	String spillerposJ = spillerpos4[0].replace(" ", "");
	String spillerposK = spillerpos4[1].replace(" ", "");;
	
	int posJ = Integer.parseInt(spillerposJ);
	int posK = Integer.parseInt(spillerposK);
	
	return posK;
	
}
return 0;
	}
	
	public static int getPosOfPlayerK(String name, Map map) {
		ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		players.clear();
		players.addAll((Collection<ArrayList>) map.get("players"));
		int number_players = players.size();
		for(int x = 0; x < number_players; x = x+1) {
			Object spillerdata1 = players.get(x);
			String spillerdata = spillerdata1.toString();
			String[] spillerdata2 = spillerdata.split(",");
			String spillerdata3 = spillerdata2[4];
			String[] spillernavn = spillerdata3.split("=");
			String currentplayer = spillernavn[1];
			if(currentplayer == name) {
			//We found him, return his pos now	
				String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
				String[] spillerpos2 = spillerpos.split("=");
				String spillerpo3 = spillerpos2[1];
				//System.out.println(spillerpo3);
				//Henter ut x/j og y/k 
				String[] spillerpos4 = spillerpo3.split(",");
				String spillerposK = spillerpos4[1].replace(" ", "");;
				
				int posK = Integer.parseInt(spillerposK);
				return posK;
				
			}
		}
		return 0;
	}
	
	public static int getPosOfPlayerJ(String name, Map map) {
		ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		players.clear();
		players.addAll((Collection<ArrayList>) map.get("players"));
		int number_players = players.size();
		for(int x = 0; x < number_players; x = x+1) {
			Object spillerdata1 = players.get(x);
			String spillerdata = spillerdata1.toString();
			String[] spillerdata2 = spillerdata.split(",");
			String spillerdata3 = spillerdata2[4];
			String[] spillernavn = spillerdata3.split("=");
			String currentplayer = spillernavn[1];
			if(currentplayer == name) {
			//We found him, return his pos now	
				String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
				String[] spillerpos2 = spillerpos.split("=");
				String spillerpo3 = spillerpos2[1];
				//System.out.println(spillerpo3);
				//Henter ut x/j og y/k 
				String[] spillerpos4 = spillerpo3.split(",");
				String spillerposJ = spillerpos4[0].replace(" ", "");
				
				int posJ = Integer.parseInt(spillerposJ);
				return posJ;
				
			}
		}
		return 0;
	}
	
	
	public static int closestPlayerK(Map map, int posJ, int posK)  {
		String closestName = null;
		int closestJ = 0;
		int closestK = 0;
		int closest = 1000;
		
		ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		 players.clear();
players.addAll((Collection<ArrayList>) map.get("players"));
int number_players = players.size();
for(int x = 0; x < number_players; x = x+1) {
	Object spillerdata1 = players.get(x);
	String spillerdata = spillerdata1.toString();
	String[] spillerdata2 = spillerdata.split(",");
	String spillerdata3 = spillerdata2[4];
	String[] spillernavn = spillerdata3.split("=");
	String currentplayer = spillernavn[1];
	
	if(!currentplayer.equals(EpicBot.name)) {
		String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
		String[] spillerpos2 = spillerpos.split("=");
		String spillerpo3 = spillerpos2[1];
		//Henter ut x/j og y/k 
		String[] spillerpos4 = spillerpo3.split(",");
		String spillerposJ = spillerpos4[0].replace(" ", "");
		String spillerposK = spillerpos4[1].replace(" ", "");;
		
		int playerJ = Integer.parseInt(spillerposJ);
		int playerK = Integer.parseInt(spillerposK);
		int distanceJ = posJ - playerJ;
		int distanceK = posK - playerK;
		if(distanceJ < 0) {
			//Convert to +
			distanceJ = distanceJ + (2*(-1*distanceJ));
		}
		if(distanceK < 0) {
			//Convert to +
			distanceK = distanceK + (2*(-1*distanceK));
		}
		int distance = distanceJ + distanceK;
		//System.out.println("Distance to "+currentplayer+ " is " + distanceJ+":"+distanceK);
		//System.out.println(closest);
		if(distance < closest) {
			//System.out.println(distance + " < " + closest);
		closestJ = 	playerJ;
		closestK = 	playerK;
		closestName = currentplayer;
		closest = distance;
		}
		
	}
	
	
}
//System.out.println("The closest player is "+ closestName + " and is standing at "+closestJ+":"+closestK);
return closestK;
	}
	
	public static int closestPlayerJ(Map map, int posJ, int posK)  {
		String closestName = null;
		int closestJ = 0;
		int closestK = 0;
		int closest = 1000;
		
		ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		 players.clear();
players.addAll((Collection<ArrayList>) map.get("players"));
int number_players = players.size();
for(int x = 0; x < number_players; x = x+1) {
	Object spillerdata1 = players.get(x);
	String spillerdata = spillerdata1.toString();
	String[] spillerdata2 = spillerdata.split(",");
	String spillerdata3 = spillerdata2[4];
	String[] spillernavn = spillerdata3.split("=");
	String currentplayer = spillernavn[1];
	
	if(!currentplayer.equals(EpicBot.name)) {
		String spillerpos = spillerdata2[2] + ","+ spillerdata2[3];
		String[] spillerpos2 = spillerpos.split("=");
		String spillerpo3 = spillerpos2[1];
		//Henter ut x/j og y/k 
		String[] spillerpos4 = spillerpo3.split(",");
		String spillerposJ = spillerpos4[0].replace(" ", "");
		String spillerposK = spillerpos4[1].replace(" ", "");;
		
		int playerJ = Integer.parseInt(spillerposJ);
		int playerK = Integer.parseInt(spillerposK);
		int distanceJ = posJ - playerJ;
		int distanceK = posK - playerK;
		if(distanceJ < 0) {
			//Convert to +
			distanceJ = distanceJ + (2*(-1*distanceJ));
		}
		if(distanceK < 0) {
			//Convert to +
			distanceK = distanceK + (2*(-1*distanceK));
		}
		int distance = distanceJ + distanceK;
		//System.out.println("Distance to "+currentplayer+ " is " + distanceJ+":"+distanceK);
		System.out.println(closest);
		if(distance < closest) {
			//System.out.println(distance + " < " + closest);
		closestJ = 	playerJ;
		closestK = 	playerK;
		closestName = currentplayer;
		closest = distance;
		}
		
	}
	
	
}
//System.out.println("The closest player is "+ closestName + " and is standing at "+closestJ+":"+closestK);
return closestJ;
	}
	
	public String getPlayers(Map map) {
		String finalreturn = "";
		
ArrayList<ArrayList> players = new ArrayList<ArrayList>();
		 players.clear();
players.addAll((Collection<ArrayList>) map.get("players"));
int number_players = players.size();
for(int x = 0; x < number_players; x = x+1) {
	Object spillerdata1 = players.get(x);
	String spillerdata = spillerdata1.toString();
	String[] spillerdata2 = spillerdata.split(",");
	String spillerdata3 = spillerdata2[4];
	String[] spillernavn = spillerdata3.split("=");
	
	if(players.size() == x) {
		finalreturn += spillernavn[1];
	}
	else {
		finalreturn += spillernavn[1] + ",";
	}
}
		return finalreturn;
	}
	
	public void bestPlayerToAttack(int playerPosJ, int playerPosK, String Weapon, Map map) {
		//Find the best player to Attack
		
		//Get all the players
	 	String playersname = getPlayers(map);
		String[] playerlist = playersname.split(",");
		for(int x = 0; x < playerlist.length; x = x+1) {
		//System.out.println(playerlist[x]);
		}
		
		
		//Closest player is (Use the basic function for now, use Johan-Henrik method later)
		
		
		
		
		
	}
	
}
