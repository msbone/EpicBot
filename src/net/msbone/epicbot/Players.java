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
			System.out.println(currentplayer);
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
	System.out.println(spillerpo3);
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
	System.out.println(spillerpo3);
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
	
	
}
