package system;

import java.util.Random;

public class Roller {
	private static Random randomGenerator = new Random();
	
	public static int roll(int limit){
		return (randomGenerator.nextInt(limit) + 1);
	}
}
