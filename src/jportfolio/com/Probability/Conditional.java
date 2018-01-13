package jportfolio.com.Probability;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Conditional {
	
	public static Map<String,Integer> group;
	public static int totalObjects;

	public static void main(String[] args) {
		
		group = new HashMap<>();
		group.put("Red", 2);
		group.put("Blue", 3);
		
		int to = getTotalObjects();// count objects in group
		
		String key = "Red";
	//	System.out.println(group.get(key)+"/"+ to +" = "+prob(key));
		key = "Blue";
	//	System.out.println(group.get(key)+"/"+ to +" = "+prob(key));

		System.out.println(conditionalProb("Red:Blue"));
	
		

	}
	
	public static double conditionalProb(String formula){
		String[] elements = formula.split(":");
		double p = 1.0;
		for(String e: elements){
			p *= prob(e);
			System.out.println(e+" - "+p);
			
		}
		
		return p;
	}
	
	public static double prob(String obj){
		int objects = group.get(obj);
		double o = (double) objects;
		double to = (double) totalObjects;
		double d = o/to;
		group.replace(obj, objects - 1);
		return d;
	}

	public static int getTotalObjects() {
		Set<String> keys = group.keySet();
		for(String k: keys){
			totalObjects+=group.get(k);
		}
		return totalObjects;
	}

	public static void setTotalObjects(int totalObjects) {
		Conditional.totalObjects = totalObjects;
	}

}
