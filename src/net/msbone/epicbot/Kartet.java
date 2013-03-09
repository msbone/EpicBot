package net.msbone.epicbot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Kartet {

	private static int size;
	
	public static Object[][] readmap(Map<String, Object> map) {
		Gson gson = new Gson();
		
		//Read the map, then retur the map
		Type maptype =  new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> kartdata =  gson.fromJson(map.get("map").toString(), maptype);
		
		ArrayList<ArrayList> lines = new ArrayList<ArrayList>();
	 lines.addAll((Collection<ArrayList>) kartdata.get("data"));
	 
	 size = lines.size();
	 
	Object[][] kart = new Object[size][size];
	
	
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
	
}
