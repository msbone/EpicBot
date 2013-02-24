package net.msbone.epicbot;

public class EpicBot {
	
	public static String IP = "127.0.0.1";
	public static int PORT = 54321;
	public static String name = "EpicBOT";
	public static Server server;
	
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(name+" started - - - - - - - - - - - - - - - - - - - - -  "+name+" started");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("This is a AI created by Ole Mathias for use on TG13");
		System.out.println("Lets beat all the other AI and WIN :)");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("This is version 1");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("- - - MSBONE MSBONE MSBONE MSBONE MSBONE MSBONE MSBONE MSBONE MSBONE - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("SYSTEM READY TO FIGHT!");
		System.out.println("---------------------");
		System.out.println("---------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		server = new Server(IP,PORT,name);
	}
}