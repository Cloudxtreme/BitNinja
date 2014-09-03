package system;

import bean.Ninja;


public class Check {
	
	public static boolean hasLevel(Ninja n, int level){
		return (n.getLevel() >= level) ?  true : false; 
	}
	
}
