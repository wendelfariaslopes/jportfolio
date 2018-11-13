package jportfolio.com.tools;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Tool {

	public static void main(String[] args) {
		
		int limit = 100;
		List<Obj> listObjs = new ArrayList<>();
		
		for(int i=0;i<limit;i++) {
			Obj o = new Obj(i,"v"+i,generate(1,10));
			System.out.println(o.getValueDouble());
			listObjs.add(o);
		}

		Comparator<Obj> c = Comparator.comparing(Obj::getValueDouble);
		
		Obj min = listObjs.stream().min(c).get();
		Obj max = listObjs.stream().max(c).get();
		
		System.out.println(min);
		System.out.println(max);
		
		
		Predicate<Obj> minimal = o -> o.getValueDouble() > 7.5;
		Predicate<Obj> maximal = o -> o.getValueDouble() < 8.5;
		
		List<Obj> filteredObjs = listObjs.stream().filter(minimal.and(maximal)).collect(Collectors.toList());
		filteredObjs.forEach(p->{System.out.println(p.getValueDouble());});

	}
	
	public static double generate(double min, double max) {
		return min + Math.random() * (max - min);
	}

}
