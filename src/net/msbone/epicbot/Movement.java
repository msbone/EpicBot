package net.msbone.epicbot;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class Movement {

	//Top-right = J - 1
	//Bot-right = K + 1
	//Top-left = K - 1
	//Bot-left = J + 1

	public boolean walkable(int j, int k){
		Object[][] kart = Kartet.kart;
		int aSize = kart[0].length;
		if(j >= 0 && k >= 0 && j < aSize && k < aSize){
			if(!kart[j][k].equals("O") && !kart[j][k].equals("V") && !kart[j][k].equals("S")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//use this array to store directions that are not walkable
	public static String[] wrong = new String[6];
	
	//use this method to check if direction is inside the wrong direction array
	public boolean wrongC(String wrongD){
		for(int i = 0; i<6; i+=1){
			if(wrong[i] == wrongD){
				return false;
			}
			//System.out.println("wrongC");
		}
		return true;
	}
	
	//use this method to insert a wrong direction into the first open slot in the array
	public void wrongI(String wrongD){
		//System.out.println(wrongD);
		if(wrongD != ""){
			int i = 0;
			while(i<6){
				if(wrong[i] == null){
					//System.out.println("wrong " + i + " = " + wrong[i]);
					wrong[i] = wrongD;
					i = 7;
				}
				else{
					i+=1;
				}
			}
		}
		else{
			for(int i2 = 0; i2<6; i2+=1){
				wrong[i2] = null;
				//System.out.println("wrongI clear");
			}
		}
	}
	
	public int[][] entrance = new int[50][50];
	
	public boolean roundabout(int jr, int kr){
		boolean returnVR = false;
		
		if(jr >= 0 && kr >= 0){
			if(entrance[jr][kr] == 0){returnVR = true;}
		}
		
		return returnVR;
	}

	public String[] path(int j1, int k1, int j2, int k2, int c3){
		
		/*for(int jshit = 0; jshit < 50; jshit += 1){
			for(int kshit = 0; kshit < 50; kshit += 1){
				System.out.println(entrance[jshit][kshit]);
			}
		}*/
		
		int j1a = j1; //does not change in this function.
		int k1a = k1; //does not change in this function.
		
		int j2_d = j2;
		int j1_d = j1;
		int k2_d = k2;
		int k1_d = k1;
		
		if(j2_d < 0){j2_d = j2_d*-1;}
		if(j1_d < 0){j1_d = j1_d*-1;}
		if(k2_d < 0){k2_d = k2_d*-1;}
		if(k1_d < 0){k1_d = k1_d*-1;}
		
		int J = j2_d - j1_d;
		int K = k2_d - k1_d;
		
		//if(J < 0){J = J*(-1);}
		//if(K < 0){K = K*(-1);}
		
		int size = J+K+50;
		//System.out.println("size = " + size + " J = " + J + " K = " + K);
		if(size < 0){size = size*-1;}
		int c1 = 0;
		int c2 = 0;
		int c4 = 0;
		String[] moves = new String[100]; //an array for all the moves going to be tested
		
		//This loop builds an array for our test path, 
		//which is the path the runner will try to follow.
		
		
		/*
		 * The problem with the pathfinding now, is that the c1>J && c2<K etc
		 * do not find a direction.
		 */
		
		
		
		while(c4 < 6){ //while the J or the K coordinates is not equal to the goal
			//System.out.println("test_loop");
			if((((c1 < J && c2 < K) && wrongC("down")) || (c4 > 2 && shortest((j1+1), (k1+1), j2, k2, j1a, k1a))) && (moves[c3] == null) && roundabout((j1+1), (k1+1))){
				//System.out.println("down (" + (j1+1) + "," + (k1+1) + ")" + roundabout(j1+1, k1+1));
				c1 += 1;
				c2 += 1;
				j1 += 1;
				k1 += 1;
				//System.out.println(j1 + "," + k1);
					if(walkable(j1, k1)){
						moves[c3] = "down";
						entrance[j1][k1] = 1;
						//System.out.println("down walkable");
						wrongI("");
						//if(c4 > 3){wrongI("up");}
						c3 += 1;
					}
					else{
						wrongI("down");
						c1 -= 1;
						c2 -= 1;
						j1 -= 1;
						k1 -= 1;
					}
				
			}
			if((((c1 > J && c2 > K) && wrongC("up")) || (c4 > 2 && shortest((j1-1), (k1-1), j2, k2, j1a, k1a))) && (moves[c3] == null) && roundabout((j1-1), (k1-1))){
				//System.out.println("up (" + (j1-1) + "," + (k1-1) + ")" + roundabout(j1-1, k1-1));
				
				c1 -= 1;
				c2 -= 1;
				j1 -= 1;
				k1 -= 1;
				//System.out.println(j1 + "," + k1);
					if(walkable(j1, k1)){
						//System.out.println("up walkable");
						moves[c3] = "up";
						entrance[j1][k1] = 1;
						wrongI("");
						//if(c4 > 3){wrongI("down");}
						c3 += 1;
					}
					else{
						wrongI("up");
						c1 += 1;
						c2 += 1;
						j1 += 1;
						k1 += 1;
					}
			}
			if(((c1 < J && wrongC("left-down")) || (c4 > 2 && shortest((j1+1), k1, j2, k2, j1a, k1a))) && (moves[c3] == null) && roundabout((j1+1), k1)){
				//System.out.println("left-down (" + (j1+1) + "," + k1 + ")" + roundabout(j1+1, k1));
				
				c1 += 1;
				j1 += 1;
				//System.out.println(j1 + "," + k1);
					if(walkable(j1, k1)){
						moves[c3] = "left-down";
						entrance[j1][k1] = 1;
						//System.out.println("left-down walkable");
						wrongI("");
						//if(c4 > 3){wrongI("right-up");}
						c3 += 1;
					}
					else{
						wrongI("left-down");
						c1 -= 1;
						j1 -= 1;
					}
			}
			if(((c2 < K && wrongC("right-down"))|| (c4 > 2 && shortest(j1, (k1+1), j2, k2, j1a, k1a))) && (moves[c3] == null) && roundabout(j1, (k1+1))){
				//System.out.println("right-down (" + j1 + "," + (k1+1) + ")" + roundabout(j1, k1+1));
				
				c2 += 1;
				k1 += 1;
				//System.out.println(j1 + "," + k1);
					if(walkable(j1, k1)){
						moves[c3] = "right-down";
						entrance[j1][k1] = 1;
						//System.out.println("right-down walkable");
						wrongI("");
						//if(c4 > 3){wrongI("left-up");}
						c3 += 1;
					}
					else{
						wrongI("right-down");
						c2 -= 1;
						k1 -= 1;
					}
			}
			if(((c1 > J && wrongC("right-up")) || (c4 > 2 && shortest((j1-1), k1, j2, k2, j1a, k1a))) && (moves[c3] == null) && roundabout((j1-1), k1)){
				//System.out.println("right-up (" + (j1-1) + "," + k1 + ")" + roundabout(j1-1, k1));
				
				c1 -= 1;
				j1 -= 1;
				//System.out.println(j1 + "," + k1);
					if(walkable(j1, k1)){
						moves[c3] = "right-up";
						entrance[j1][k1] = 1;
						//System.out.println("right-up walkable");
						wrongI("");
						//if(c4 > 3){wrongI("left-down");}
						c3 += 1;
					}
					else{
						wrongI("right-up");
						c1 += 1;
						j1 += 1;
					}
			}
			if(((c2 > K && wrongC("left-up")) || (c4 > 2 && shortest(j1, (k1-1), j2, k2, j1a, k1a))) && (moves[c3] == null) && roundabout(j1, (k1-1))){
				//System.out.println("left-up (" + j1 + "," + (k1-1) + ")" + roundabout(j1, k1-1));
				
				c2 -= 1;
				k1 -= 1;
				//System.out.println(j1 + "," + k1);
					if(walkable(j1, k1)){
						moves[c3] = "left-up";
						entrance[j1][k1] = 1;
						//System.out.println("left-up walkable");
						wrongI("");
						//if(c4 > 3){wrongI("right-down");} //this is fucking up the program right now
						c3 += 1;
					}
					else{
						wrongI("left-up");
						c2 += 1;
						k1 += 1;
					}
			}
			c4 += 1;
		}
		return moves;
	}
	
	/*public boolean validate(int c1, int c2, int J, int K, int c4, boolean shortest, String[] moves, boolean wrongC, String direction){
		boolean returnV = false;
		
		
		
		return returnV;
	}*/
	
	public boolean shortest(int j1, int k1, int j2, int k2, int j1a, int k1a){
		boolean returnV = false;
		int lowest = 99999999;
		
		int j1t = j1a+1;
		int k1t = k1a+1;
		int jd = j2 - j1t;
		int kd = k2 - k1t;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int down = jd+kd;
		if(down < 0){down = down*(-1);}
		if(walkable((j1a+1), (k1a+1)) && wrongC("down")){lowest = down; //System.out.println("lowest = down");
		}
		
		j1t = j1a-1;
		k1t = k1a-1;
		jd = j2 - j1t;
		kd = k2 - k1t;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int up = jd+kd;
		if(up < 0){up = up*(-1);}
		if(up < lowest && walkable((j1a-1), (k1a-1)) && wrongC("up")){lowest = up; //System.out.println("lowest = up");
		}
		
		j1t = j1a+1;
		k1t = k1a;
		jd = j2 - j1t;
		kd = k2 - k1t;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int leftDown = jd+kd;
		if(leftDown < 0){leftDown = leftDown*(-1);}
		if(leftDown < lowest && walkable((j1a+1), (k1a)) && wrongC("left-down")){lowest = leftDown; //System.out.println("lowest = leftDown");
		}
		
		j1t = j1;
		k1t = k1a+1;
		jd = j2 - j1t;
		kd = k2 - k1t;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int rightDown = jd+kd;
		if(rightDown < 0){rightDown = rightDown*(-1);}
		if(rightDown < lowest && walkable((j1a), (k1a+1)) && wrongC("right-down")){lowest = rightDown; //System.out.println("lowest = rightDown");
		}
		
		j1t = j1a-1;
		k1t = k1a;
		jd = j2 - j1t;
		kd = k2 - k1t;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int rightUp = jd+kd;
		if(rightUp < 0){rightUp = rightUp*(-1);}
		if(rightUp < lowest && walkable((j1a-1), (k1a)) && wrongC("right-up")){lowest = rightUp; //System.out.println("lowest = rightUp");
		}
		
		j1t = j1a;
		k1t = k1a-1;
		jd = j2 - j1t;
		kd = k2 - k1t;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int leftUp = jd+kd;
		if(leftUp < 0){leftUp = leftUp*(-1);}
		if(leftUp < lowest && walkable((j1a), (k1a-1)) && wrongC("left-up")){lowest = leftUp; //System.out.println("lowest = leftUp");
		}
		
		jd = j2-j1;
		kd = k2-k1;
		if(jd < 0){jd = jd*(-1);}
		if(kd < 0){kd = kd*(-1);}
		int tryV = jd+kd;
		if(tryV < 0){tryV = tryV*(-1);}
		if(tryV <= lowest){
		lowest = tryV; 
		returnV = true;
		}	
		return returnV;
	}
}
