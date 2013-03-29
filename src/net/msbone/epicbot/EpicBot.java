package net.msbone.epicbot;

public class EpicBot {
	
	public static String IP = "127.0.0.1";
	public static int PORT = 54321;
	public static String name = "Nerdvana";
	public static Client server;
	
	public static void main(String[] args) {
		if(args.length == 1) {
			name = args[0];
		}
		System.out.println("TG 13 | TG 13 | TG 13 | TG 13 | TG 13 | TG13 | TG13 | TG13 | TG13  TG13 |");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(name+" started - - - - - - - - - - - - - - - - - - - -   "+name+" started");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("This is an AI created by Ole Mathias and Johan-Henrik for TG13");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("This is version 0.7 -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("- - - NERDVANA NERDVANA NERDVANA NERDVANA NERDVANA NERDVANA NERDVANA - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" - - - - INITIALIZING WAR SEQUENCE - - - INITIALIZING WAR SEQUENCE - - - -");
		System.out.println(" - - - - INITIALIZING WAR SEQUENCE - - - INITIALIZING WAR SEQUENCE - - - -");
		System.out.println(" - - - - INITIALIZING WAR SEQUENCE - - - INITIALIZING WAR SEQUENCE - - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("SYSTEM READY TO FIGHT!");
		System.out.println("------------------------");
		System.out.println("---------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		server = new Client(IP,PORT,name);
		
	}
}
