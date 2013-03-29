package net.msbone.epicbot;

public class Mining {
	
	private static int rubidium = 0;
	private static int explosium = 0;
	private static int scrap = 0;
	
	
	public static void mine(int posJ, int posK) {
		
	}
	
	public static int closestMineRange(String type, int myPosJ, int myPosK) {
		//Finding the closet mine of type
		
		int closest = 100;
		int closestJ = 0;
		int closestK = 0;
		Object[][] kart = Kartet.kart;
		for(int j = 1; j < Kartet.mapSize(); j++) {
			for(int k = 1; k < Kartet.mapSize(); k++) {
				if(kart[j][k].equals(type)) {
					int distance = k+j;
					if(distance < closest){
						//This one is now the closest
						//My pos - mine pos
						closest = (myPosJ+myPosK) - distance;
						//Convert to plus if negative
						if(closest < 0) {
							closest = closest*-1;
						}
						
						closestJ = j;
						closestK = k;
					}
				}
			}
		}
		System.out.println("Closest way is at " + closestJ + " - " + closestK + " and have a distance of " + closest);
		return closest;
	}
	
	public static int closestMineJ(String type, int myPosJ, int myPosK) {
		//Finding the closet mine of type
		
		int closest = 100;
		int closestJ = 0;
		int closestK = 0;
		Object[][] kart = Kartet.kart;
		for(int j = 0; j < Kartet.mapSize(); j++) {
			for(int k = 0; k < Kartet.mapSize(); k++) {
				if(kart[j][k].equals(type)) {
					if(k+j < closest){
						//This one is now the closest
						//My pos - mine pos
						closest = (myPosJ+myPosK) - (k+j);
						//Convert to plus if negative
						if(closest < 0) {
							closest = closest*-1;
						}
						
						closestJ = j;
						closestK = k;
					}
				}
			}
		}
		return closestK;
	}
	
	public static int closestMineK(String type, int myPosJ, int myPosK) {
		//Finding the closet mine of type
		
		int closest = 100;
		int closestJ = 0;
		int closestK = 0;
		Object[][] kart = Kartet.kart;
		for(int j = 0; j < Kartet.mapSize(); j++) {
			for(int k = 0; k < Kartet.mapSize(); k++) {
				if(kart[j][k].equals(type)) {
					if(k+j < closest){
						//This one is now the closest
						//My pos - mine pos
						closest = (myPosJ+myPosK) - (k+j);
						//Convert to plus if negative
						if(closest < 0) {
							closest = closest*-1;
						}
						
						closestJ = j;
						closestK = k;
					}
				}
			}
		}
		
		return closestK;
	}
	
	
}
