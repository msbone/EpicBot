package net.msbone.epicbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Loadout{
	
	public static void loadout(Object[][] kart, ArrayList<ArrayList> lines) {
	
	//Vi definerer noen variabler
	double grass = 0;
    double tom = 0;
    double explodium = 0;
    double rubidium = 0;
    double scrap = 0;
    double rock = 0;
    double mortar = 2; //start veriden til mortar
    double droid = 2; //start veriden til droid
    double laser = 2; //start veriden til laser

    //Vi teller opp antallet av forskjellige blokker
    for(int c1 = 0; c1 < lines.size(); c1 += 1){
    	for(int c2 = 0; c2 < lines.size(); c2 += 1){
    		if(kart[c1][c2].equals("G")){grass += 1;}
    		if(kart[c1][c2].equals("V")){tom += 1;}
    		if(kart[c1][c2].equals("E")){explodium += 1;}
    		if(kart[c1][c2].equals("R")){rubidium += 1;}
    		if(kart[c1][c2].equals("C")){scrap += 1;}
    		if(kart[c1][c2].equals("O")){rock += 1;}
    	}
    }
    
    
    //vi sjekker forholdet mellom gress, stein og void(tom)
    double grass_rock = grass/rock;
    double grass_tom = grass/tom;
    
    if((grass_rock) <=  15){ //dersom det er mye stein i forhold til gress, skru ned verdien til laser og droid
    	laser -= 1;
    	droid -= 0.5;
    }
    
    if((grass_tom) <= 15){ //dersom det er mye void blokker i forhold til gress, skru ned verdien til laser og droid
    	droid -= 0.5;
    }
    
    //vi sjekker hvilke ressurser det finnes mest av
    if(explodium <= rubidium || explodium < scrap){
    	mortar -= 1;
    }
    
    if(rubidium < explodium || rubidium < scrap){
    	laser -= 1;
    }
    
    if(scrap < explodium || scrap < rubidium){
    	droid -= 1;
    }
    
    //definerer noen variabler
    double weapons[] = {mortar, droid, laser};
    double largest = weapons[0];
    double smallest = weapons[0];
    String primary_weapon = "laser";
    String secondary_weapon = "mortar";
    int c3 = 0;
    int c4 = 0;
    
    //finner det beste våpenet, og setter det til primary
    while(c3 <= 2){
    	if(weapons[c3] > largest){
    		largest = weapons[c3];
    		if(c3 == 0){primary_weapon = "mortar";}
    		if(c3 == 1){primary_weapon = "droid";}
    		if(c3 == 2){primary_weapon = "laser";}
    	}
    c3 += 1;
    }
    
    //Vi finner det andt beste våpenet, og setter det til secondary
    while(c4 <= 2){
    	//finner den laveste verdien
    	if(weapons[c4] < smallest){
    		smallest = weapons[c4];
    		System.out.println("smallest= " + smallest);
    	}
    	//finner den mellomste verdien
    	if(weapons[c4] > smallest && weapons[c4] < largest){
    			if(c4 == 0){secondary_weapon = "mortar";}
	    		if(c4 == 1){secondary_weapon = "droid";}
	    		if(c4 == 2){secondary_weapon = "laser";}
    	}
    c4 += 1;
    }
    
    //printer hvor mye som finnes av forskjellige tiles(blokker)
    System.out.println("grass: " + grass);
    System.out.println("tom: " + tom);
    System.out.println("explodium: " + explodium);
    System.out.println("rubidium: " + rubidium);
    System.out.println("scrap: " + scrap);
    System.out.println("rock: " + rock);
    
    
		//Vi sender loadout til serveren
		Map<String, String> connect = new HashMap<String, String>();
		connect.put("message", "loadout");
		connect.put("primary-weapon", primary_weapon);
		connect.put("secondary-weapon", secondary_weapon);
		Gson gson = new Gson();
		String json = gson.toJson(connect);
		Server.sendMessage(json);
	}
}
