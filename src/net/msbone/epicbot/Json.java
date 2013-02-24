package net.msbone.epicbot;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Json {

	public String toString(String data, String what) {
		String[] tokens = data.split(",");
		
		
		return "";
	}
	
	public String toServer(Map<String, String> data) {
		//Gjør en map om the json string
		
		String string_done = "{";
			Set<Entry<String, String>> s=data.entrySet();
			Iterator<Entry<String, String>> it=s.iterator();
			while(it.hasNext()) {
	            Entry<String, String> m =it.next();
	            String key=(String)m.getKey();
	            String value=(String)m.getValue();
	            if(it.hasNext()) {
	            	 string_done += "\""+key+"\":\""+value+"\",";
	            }
	            else {
	            	string_done += "\""+key+"\":\""+value+"\"";
	            }
			}
			string_done += "}";
			
			return string_done;
			
		
	}
	
}
