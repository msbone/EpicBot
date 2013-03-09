package net.msbone.epicbot;

public class EpicBot {
	
	public static String IP = "127.0.0.1";
	public static int PORT = 54321;
	public static String name = "SmartBot";
	public static Client server;
	
	public static void main(String[] args) {
		if(args.length == 1) {
			name = args[0];
		}
		System.out.println("TG 13 | TG 13 | TG 13 | TG 13 | TG 13 | TG13 | TG13 | TG13 | TG13  TG13 |");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(name+" started - - - - - - - - - - - - - - - - - - - -   "+name+" started");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("This is a AI created by Ole Mathias and Johan-Henrik for use on TG13");
		System.out.println("Lets beat all the other AI and WIN :)");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("This is version 0.1 -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("- - - NERDVANA NERDVANA NERDVANA NERDVANA NERDVANA NERDVANA NERDVAN - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("SYSTEM READY TO FIGHT!");
		System.out.println("------------------------");
		System.out.println("---------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		server = new Client(IP,PORT,name);
		
	}
}
