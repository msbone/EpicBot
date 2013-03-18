package net.msbone.epicbot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Kartet {

	private static int size;
	public static Object[][] kart;
	
	public static Object[][] readmap(Map<String, Object> map) {
		Gson gson = new Gson();
		
		//Read the map, then retur the map
		Type maptype =  new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> kartdata =  gson.fromJson(map.get("map").toString(), maptype);
		
		ArrayList<ArrayList> lines = new ArrayList<ArrayList>();
	 lines.addAll((Collection<ArrayList>) kartdata.get("data"));
	 
	 size = lines.size();
	 
	kart = new Object[size][size];
	
	
	      for(int j = 0; j < size; j += 1) {
				ArrayList<ArrayList> line = new ArrayList<ArrayList>();
				line.addAll((Collection<ArrayList>) lines.get(j));
				for(int k = 0; k < size; k += 1) {
					kart[j][k] = line.get(k);
				}
	       }
		return kart;
	}
	
	public static int mapSize() {
		return size;
	}
	
	
	Movement walkable = new Movement();
	
	
	public void CreateWalkableArray(Object[][] kart){
		
		int num_walkable = 0;
		for(int j2 = 0; j2 < size; j2 += 1) {
			for(int k2 = 0; k2 < size; k2 += 1) {
				if(walkable.walkable(j2, k2)){
					num_walkable += 1;
				}
			}
        }
		int c = 0;
		String[] walkableArray = walkableArray = new String[num_walkable];
		for(int j = 0; j < size; j += 1) {
			for(int k = 0; k < size; k += 1) {
				if(walkable.walkable(j, k)){
					walkableArray[c] = j + "," + k;
					c += 1;
				}
			}
        }
		for(int c2 = 0; c2 < num_walkable; c2 += 1){
			
			//Commented out to save some console place :)
			//System.out.println("walkableArray[" + c2 + "] = " + walkableArray[c2]);
		}
		
	}
	
}
