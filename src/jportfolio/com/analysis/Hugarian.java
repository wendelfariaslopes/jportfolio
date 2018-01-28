package jportfolio.com.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * The Hungarian algorithm consists of the four steps below. The first two steps
 * are executed once, while Steps 3 and 4 are repeated until an optimal
 * assignment is found. The input of the algorithm is an n by n square matrix
 * with only nonnegative elements.
 * 
 * @author wendellopes
 *
 */

public class Hugarian {
	
	private static int[][] original = null;

	enum LineType {
		NONE, HORIZONTAL, VERTICAL
	}
	

	public static void main(String[] args) {
		/*
		 * List<Person> listPerson = new ArrayList<>(); 
		 * Map<String,Integer> mapTask = new HashMap<>(); 
		 * Map<String,Integer> mapTask2 = new HashMap<>(); 
		 * 
		 * Person p = null;
		 * 
		 * for (int i = 1; i < 15; i++) { int d = randInt(5,20);
		 * mapTask.put("Task"+i, d); System.out.print("Task"+i+ " = "+d+", "); }
		 * System.out.println();
		 * 
		 * for (int i = 1; i < 10; i++) { mapTask2 = new HashMap<>(mapTask); p =
		 * new Person("P"+i, mapTask2); // para cada pessoa colocamos as mesmas
		 * tarefas listPerson.add(p); }
		 * 
		 * for(Person person: listPerson){ System.out.println(person.getName());
		 * Set<String> keys = person.getMap().keySet(); for(String k: keys){
		 * System.out.print(person.getMap().get(k)+", "); }
		 * System.out.println(); }
		 */
		
		//Random matrix for tests
		int[][] matrix = new int [18][18];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				int d = randInt(1,500);
				matrix[i][j] = d;
			}
		}
		
		// Calc Best Allocation
		long start = System.currentTimeMillis();
		calcAllocation(matrix);
		long finished = System.currentTimeMillis();
		
		System.out.println("Tempo de execucao = "+ (finished - start));

	}
	
	public static void calcAllocation(int[][] m){
		
		original = copy(m);
		
		printMatrix(m);
		//STEP 1
		int[][] matrixStep1 = step1(m);
		//printMatrix(matrixStep1);
		//STEP 2
		int[][] matrixStep2 = step2(matrixStep1);
		//printMatrix(matrixStep2);
		//STEP 3
		step3(matrixStep2);
		
	}

	/**
	 * Step 1: Subtract row minima For each row, find the lowest element and
	 * subtract it from each element in that row.
	 * 
	 * @param matrix
	 * @return
	 */
	private static int[][] step1(int[][] matrix) {
		//System.out.println("Step 1: Subtract row minima");
		int[][] min = findMin(matrix,LineType.HORIZONTAL);
		return minus(matrix, min);
	}

	/**
	 * Step 2: Subtract column minima Similarly, for each column, find the
	 * lowest element and subtract it from each element in that column.
	 * 
	 * @param matrix
	 * @return
	 */
	private static int[][] step2(int[][] matrix) {
		//System.out.println("Step 2: Subtract column minima");
		int[][] minV = findMin(matrix,LineType.VERTICAL);
		return minus(matrix, minV);
	}

	/**
	 * Step 3: Cover all zeros with a minimum number of lines Cover all zeros in
	 * the resulting matrix using a minimum number of horizontal and vertical
	 * lines. If n lines are required, an optimal assignment exists among the
	 * zeros. The algorithm stops. If less than n lines are required, continue
	 * with Step 4.
	 * 
	 * @param matrix
	 * @return
	 */
	private static void step3(int[][] matrix) {
		//System.out.println("Step 3: Cover all zeros with a minimum number of lines");
		
		int ciclos = 1;
		int[][] memoryMatrix = copy(matrix);
		//System.out.println("Print memoryMatrix   ");
		//printMatrix(memoryMatrix);
		
		List<Line> lines = getMinLines(memoryMatrix);
		
		int numberLines = lines.size();
		//System.out.printf("Min num of lines for example matrix is: %d\n", numberLines);
		
		if(numberLines < matrix.length){
			int[][] matrixMarked = createMark(matrix, getMinLines(matrix));
			//System.out.println("Print matrixMarked   ");
			//printMatrix(matrixMarked);
			int k = getMinMatrix(matrixMarked);
			//System.out.printf("get K : %d\n", k);
			step4(memoryMatrix, matrixMarked, lines, k, ciclos);
			ciclos++;
		}else{
			step5(memoryMatrix);
		}
	
	}

	/**
	 * Step 4: Create additional zeros Find the smallest element (call it k)
	 * that is not covered by a line in Step 3. Subtract k from all uncovered
	 * elements, and add k to all elements that are covered twice.
	 * 
	 * @param matrix
	 * @return
	 */
	private static void step4(int[][] matrix, int[][] matrixMarked, List<Line> lines, int k,int ciclos) {
		
		//System.out.println(ciclos +" Step 4: Create additional zeros Find the smallest element");
		final int SIZE = matrix.length;
		
		//Subtract k from all uncovered elements
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(matrixMarked[i][j] > 0){
					matrix[i][j] = matrix[i][j] - k;
				}
			}
		}
		
		//and add k to all elements that are covered twice.
		for (Line line : lines) {
			for (int i = 0; i < SIZE; i++) {
				int index = line.getLineIndex();
	
				if (line.isHorizontal()) {
					int value = matrix[index][i];
					matrix[index][i] = matrix[index][i] < 0 ? (value + k): value;
				} else {
					int value = matrix[i][index];
					matrix[i][index] = matrix[i][index] < 0 ? (value + k): value;
				}
			}
		}
		step3(matrix);
	}
	/**
	 * Find the best allocation or all best combination when more that one choice
	 */
	private static List<Cell> step5(int[][] matrix){
		List<Cell> list = new ArrayList<>();
		//System.out.println("Step 5: Finished");
		printMatrix(matrix);
	
		
		Map<String,Integer> unsortMapLines = new HashMap<>();
		
		//Map<String,Integer> unsortMapLines = new HashMap<>();
		
		int length = matrix.length;
		
		for (int i = 0; i < length; i++) {
			int lines = 0;
			String s = "";
			for (int j = 0; j < length; j++) {
//				if(matrix[i][j] == 0){
//					//s += "X["+i+","+j+"] ";
//					s += "Column["+j+"] ";
//					//System.out.print("X["+i+""+j+"] ");
//					lines++;
//				}else{
//					original[i][j]=0;
//					
//				}
//				
				if(matrix[i][j] != 0){
					original[i][j]=0;
				}
			}
			//unsortMapLines.put(s, lines);
			//System.out.print(lines);
			//System.out.println();
		}
		System.out.println();
		
		printMatrix(original);
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(original[i][j] != 0){
					for(int k = i+1; k < length;k++ ){
						original[k][j] = 0;
					}
				}
			}	
		}
		printOtimizationSum(original);
		printMatrix(original);
		
		
		
//		Map<String, Integer> sortedMap = sortByValue(unsortMapLines);
//		for (String k : sortedMap.keySet()) {
//			System.out.println(k+" "+sortedMap.get(k));
//		}

		//System.out.println();
//		Map<String, Integer> choiceMap = new HashMap<>();
//		List<String> listElimination = new ArrayList<>();
		//Map<String, Integer> sortedMap = sortByValue(unsortMapLines);
/*		for (String k : sortedMap.keySet()) {
			String[] elimination = k.split(" ");
			//System.out.println(k+" "+sortedMap.get(k)+" "+elimination.length );
			
			for(int e=0; e < elimination.length;e++){
				if(k.contains(elimination[e]) && sortedMap.get(k) > 1){
					//System.out.println("Contain "+ elimination[e]);
					choiceMap.put(elimination[e].trim(), sortedMap.get(k));
				}
			}
			
			//delete all elements more than 2
			if(sortedMap.get(k) > 1){
				listElimination.add(k);
			}

		}

		
		choiceMap = sortByValue(choiceMap);
		
//		System.out.println();
//		for (String k : sortedMap.keySet()) {
//			System.out.println(k+" "+sortedMap.get(k));
//		}	
		
		//System.out.println();
		//System.out.println("Map a retirar");
		for (String k : listElimination) {
			//System.out.println(k+" "+sortedMap.get(k));
			sortedMap.remove(k);
		}	
		//System.out.println();
		//System.out.println("Map limpo e a ser depositado");
		for (String k : sortedMap.keySet()) {
			//System.out.println(k+" "+sortedMap.get(k));
			choiceMap.put(k.trim(),sortedMap.get(k));
		}	
		choiceMap = sortByValue(choiceMap);
		//System.out.println();
		System.out.println("Map Escolhido");
		for (String k : choiceMap.keySet()) {
			//System.out.println(k+" "+choiceMap.get(k));
			System.out.print(k+"  "+choiceMap.get(k)+" ");
		}
*/
		return list;
	}

	public static int[][] copy(int[][] matrix) {
		int[][] copy = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		return copy;
	}

	private static int[][] findMin(int[][] matrix) {
		int[][] min = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			int minRow = getMin(matrix[i]);
			for (int j = 0; j < matrix[0].length; j++) {
				min[i][j] = minRow;
			}
		}
		return min;
	}

	public static int[][] findMin(int[][] matrix, LineType lineType) {
		int[][] min = new int[matrix.length][matrix[0].length];
		if (lineType == LineType.HORIZONTAL) {
			min = findMin(matrix);
		} else {
			matrix = transpose(matrix);
			min = transpose(findMin(matrix));
		}
		return min;
	}

	// Method for getting the maximum value
	public static int getMax(int[] inputArray) {
		int maxValue = inputArray[0];
		for (int i = 1; i < inputArray.length; i++) {
			if (inputArray[i] > maxValue) {
				maxValue = inputArray[i];
			}
		}
		return maxValue;
	}

	// Method for getting the minimum value
	public static int getMin(int[] inputArray) {
		int minValue = inputArray[0];
		for (int i = 1; i < inputArray.length; i++) {
			if (inputArray[i] < minValue) {
				minValue = inputArray[i];
			}
		}
		return minValue;
	}

	// Method for getting the minimum value in matrix
	public static int getMinMatrix(int[][] matrixMarked) {
		int minValue = 2147483647;// big int
		int l = matrixMarked.length;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				if (matrixMarked[i][j] > 0 && matrixMarked[i][j] < minValue) {
					minValue = matrixMarked[i][j];
				}
			}

		}
		return minValue;
	}

	// step1 - return C = A - B
	public static int[][] minus(int[][] a, int[][] b) {

		int[][] c = new int[a.length][a[0].length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
				c[i][j] = a[i][j] - b[i][j];
		return c;
	}
	// step2 find columns haven't 0

	private static int[][] transpose(int[][] a) {
		int[][] c = new int[a.length][a[0].length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
				c[i][j] = a[j][i];
		return c;
	}

	public static int[][] createMark(int[][] matrix, List<Line> lines) {
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix should be square!");
		}

		final int SIZE = matrix.length;
		for (Line line : lines) {
			for (int i = 0; i < SIZE; i++) {
				int index = line.getLineIndex();
				if (line.isHorizontal()) {
					matrix[index][i] = matrix[index][i] < 0 ? -3 : -1;
				} else {
					matrix[i][index] = matrix[i][index] < 0 ? -3 : -2;
				}
			}
		}
		return matrix;
	}

	public static int randInt(int min, int max) {
		// Usually this can be a field rather than a method variable
		Random rand = new Random();
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static void printMatrix(int[][] matrix) {
		int length = matrix.length;

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(matrix[i][j]<10){
					System.out.print(matrix[i][j] + "    ");
				}else if(matrix[i][j]>10 && matrix[i][j] < 100){
					System.out.print(matrix[i][j] + "   ");
				}else{
					System.out.print(matrix[i][j] + "  ");
				}
				
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printOtimizationSum(int[][] matrix) {
		int length = matrix.length;
		int sum = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(matrix[i][j]!=0){
					sum+=matrix[i][j];
					System.out.print("X["+i+""+j+"]="+matrix[i][j]+" ");
				}
			}
		}
		System.out.println(" Sum = "+sum);
		System.out.println();
	}

	private static class Line {
		int lineIndex;
		LineType rowType;

		Line(int lineIndex, LineType rowType) {
			this.lineIndex = lineIndex;
			this.rowType = rowType;
		}

		LineType getLineType() {
			return rowType;
		}

		int getLineIndex() {
			return lineIndex;
		}

		boolean isHorizontal() {
			return rowType == LineType.HORIZONTAL;
		}
	}

	private static boolean isZero(int[] array) {
		for (int e : array) {
			if (e != 0) {
				return false;
			}
		}
		return true;
	}

	public static List<Line> getMinLines(int[][] matrix) {
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Matrix should be square!");
		}

		final int SIZE = matrix.length;
		int[] zerosPerRow = new int[SIZE];
		int[] zerosPerCol = new int[SIZE];

		// Count the number of 0's per row and the number of 0's per column
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (matrix[i][j] == 0) {
					zerosPerRow[i]++;
					zerosPerCol[j]++;
				}
			}
		}

		// There should be at must SIZE lines,
		// initialize the list with an initial capacity of SIZE
		List<Line> lines = new ArrayList<Line>(SIZE);

		LineType lastInsertedLineType = LineType.NONE;

		// While there are 0's to count in either rows or colums...
		while (!isZero(zerosPerRow) && !isZero(zerosPerCol)) {
			// Search the largest count of 0's in both arrays
			int max = -1;
			Line lineWithMostZeros = null;
			for (int i = 0; i < SIZE; i++) {
				// If exists another count of 0's equal to "max" but in this one
				// has
				// the same direction as the last added line, then replace it
				// with this
				//
				// The heuristic "fixes" the problem reported by
				// @JustinWyss-Gallifent and @hkrish
				if (zerosPerRow[i] > max || (zerosPerRow[i] == max && lastInsertedLineType == LineType.HORIZONTAL)) {
					lineWithMostZeros = new Line(i, LineType.HORIZONTAL);
					max = zerosPerRow[i];
				}
			}

			for (int i = 0; i < SIZE; i++) {
				// Same as above
				if (zerosPerCol[i] > max || (zerosPerCol[i] == max && lastInsertedLineType == LineType.VERTICAL)) {
					lineWithMostZeros = new Line(i, LineType.VERTICAL);
					max = zerosPerCol[i];
				}
			}

			// Delete the 0 count from the line
			if (lineWithMostZeros.isHorizontal()) {
				zerosPerRow[lineWithMostZeros.getLineIndex()] = 0;
			} else {
				zerosPerCol[lineWithMostZeros.getLineIndex()] = 0;
			}

			// Once you've found the line (either horizontal or vertical) with
			// the greater 0's count
			// iterate over it's elements and substract the 0's from the other
			// lines
			// Example:
			// 0's x col:
			// [ 0 1 2 3 ] -> 1
			// [ 0 2 0 1 ] -> 2
			// [ 0 4 3 5 ] -> 1
			// [ 0 0 0 7 ] -> 3
			// | | | |
			// v v v v
			// 0's x row: {4} 1 2 0

			// [ X 1 2 3 ] -> 0
			// [ X 2 0 1 ] -> 1
			// [ X 4 3 5 ] -> 0
			// [ X 0 0 7 ] -> 2
			// | | | |
			// v v v v
			// {0} 1 2 0

			int index = lineWithMostZeros.getLineIndex();
			if (lineWithMostZeros.isHorizontal()) {
				for (int j = 0; j < SIZE; j++) {
					if (matrix[index][j] == 0) {
						zerosPerCol[j]--;
					}
				}
			} else {
				for (int j = 0; j < SIZE; j++) {
					if (matrix[j][index] == 0) {
						zerosPerRow[j]--;
					}
				}
			}

			// Add the line to the list of lines
			lines.add(lineWithMostZeros);
			lastInsertedLineType = lineWithMostZeros.getLineType();
		}
		return lines;
	}
	
	 private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

	        // 1. Convert Map to List of Map
	        List<Map.Entry<String, Integer>> list =
	                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

	        // 2. Sort list with Collections.sort(), provide a custom Comparator
	        //    Try switch the o1 o2 position for a different order
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2) {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });

	        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        /*
	        //classic iterator example
	        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
	            Map.Entry<String, Integer> entry = it.next();
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }*/


	        return sortedMap;
	    }

}

class Person {

	private String name;
	private Map<String, Integer> map;

	public Person(String name, Map<String, Integer> task) {
		this.name = name;
		this.map = new HashMap<>();
		this.map = distanceMap(task); // pega cada tarefa e suas distancias e
										// transforma para a distancia desta
										// pessoa
	}

	private Map<String, Integer> distanceMap(Map<String, Integer> map) {
		Set<String> keys = map.keySet();
		int d = 0;
		for (String k : keys) {
			d = map.get(k) - Hugarian.randInt(4, map.get(k) - 1);
			map.replace(k, d);
		}
		return map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

}

class Cell{
	
	int i;
	int j;
	int value;
	String info;

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
