package jportfolio.com.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;



public class Hugarian {
	
	enum LineType { NONE, HORIZONTAL, VERTICAL }

	public static void main(String[] args) {
		/*
		List<Person> listPerson = new ArrayList<>();
		Map<String,Integer> mapTask = new HashMap<>();
		Map<String,Integer> mapTask2 = new HashMap<>();
		Person p = null;
		
		for (int i = 1; i < 15; i++) {
			int d = randInt(5,20);
			mapTask.put("Task"+i, d);
			System.out.print("Task"+i+ " = "+d+", ");
		}
		System.out.println();
		
		for (int i = 1; i < 10; i++) {
			mapTask2 = new HashMap<>(mapTask);
			p = new Person("P"+i, mapTask2); // para cada pessoa colocamos as mesmas tarefas
			listPerson.add(p);
		}
		
		for(Person person: listPerson){
			System.out.println(person.getName());
			Set<String> keys = person.getMap().keySet();
			for(String k: keys){
				System.out.print(person.getMap().get(k)+", ");
			}
			System.out.println();
		}
		*/
		
		int[][]matrix = {
				{26,35,74,20},
				{26,36,72,22},
				{35,53,80,40},
				{38,48,81,41}
			};
		
		int size = matrix.length;
		
		int[][]min =findMin(matrix);
		int[][]m = minus(matrix, min);
		printMatrix(matrix);
		printMatrix(min);
		printMatrix(m);
		
		List<Line> minLines = getMinLines(m);
        System.out.printf("Min num of lines for example matrix is: %d\n", minLines.size());
        //createMark(m, minLines);
        int[][]t = transpose(m);
        int[][]minCol = findMin(t);
        int[][]minusCol = minus(t,minCol);
        int[][]normal = transpose(minusCol);
        System.out.println();
        printMatrix(normal);
        int[][] memory = copy(normal);
   
        List<Line> minls = getMinLines(normal);
        System.out.printf("Min num of lines for example matrix is: %d\n", minls.size());
        
        createMark(normal, minls);
        printMatrix(memory);
        printMatrix(normal);
        System.out.println(getMinMatrix(normal));
        

	}
	
	public static int[][] copy(int [][] matrix){
		int[][] copy = new int[matrix.length][matrix[0].length];	
		for (int i = 0; i < matrix.length;i++) {
			for (int j = 0; j <matrix[0].length;j++) {
				copy[i][j]=matrix[i][j];
			}
		}
		return copy;
	}

	
	public static int[][] findMin(int [][] matrix){
		int[][] min = new int[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length;i++) {
			int minRow = getMin(matrix[i]);
			for (int j = 0; j <matrix[0].length;j++) {
				min[i][j]=minRow;
			}
		}
		return min;
	}
	
	public static int[][] findMin(int [][] matrix, LineType line){
		int[][] min = new int[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length;i++) {
			int minRow = getMin(matrix[i]);
			for (int j = 0; j <matrix[0].length;j++) {
				min[i][j]=minRow;
			}
		}
		return min;
	}
	
	// Method for getting the maximum value
	  public static int getMax(int[] inputArray){ 
	    int maxValue = inputArray[0]; 
	    for(int i=1;i < inputArray.length;i++){ 
	      if(inputArray[i] > maxValue){ 
	         maxValue = inputArray[i]; 
	      } 
	    } 
	    return maxValue; 
	  }
	 
	  // Method for getting the minimum value
	  public static int getMin(int[] inputArray){ 
	    int minValue = inputArray[0]; 
	    for(int i=1;i<inputArray.length;i++){ 
	      if(inputArray[i] < minValue){ 
	        minValue = inputArray[i]; 
	      } 
	    } 
	    return minValue; 
	  } 
	  
	  // Method for getting the minimum value in matrix
	  public static int getMinMatrix(int[][] matrix){ 
	    int minValue = 2147483647;// big int
	    int l = matrix.length;
	    for(int i=0;i< l;i++){ 
	    	for(int j=0; j < l;j++){
	    		 if(matrix[i][j] > 0 && matrix[i][j] < minValue){ 
	    		        minValue = matrix[i][j]; 
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
	    //step2 find columns haven't 0

	
	    public static int[][] transpose(int[][] a) {
	        int[][] c = new int[a.length][a[0].length];
	        for (int i = 0; i < a.length; i++)
	            for (int j = 0; j < a[0].length; j++)
	                c[i][j] = a[j][i];
	        return c;
	    }
	    
	    public static int[][] createMark(int[][] matrix, List<Line> lines){
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
		
		public static void printMatrix(int[][] matrix){
			int length =matrix.length;
			
			for (int i = 0; i < length; i++) {
				for (int j=0; j < length; j++) {
					System.out.print(matrix[i][j]+" ");
				}
				System.out.println();
			}
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
	                // If exists another count of 0's equal to "max" but in this one has
	                // the same direction as the last added line, then replace it with this
	                // 
	                // The heuristic "fixes" the problem reported by @JustinWyss-Gallifent and @hkrish
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

	            // Once you've found the line (either horizontal or vertical) with the greater 0's count
	            // iterate over it's elements and substract the 0's from the other lines 
	            // Example:
	            //                          0's x col:
	            //           [ 0  1  2  3 ]  ->  1
	            //           [ 0  2  0  1 ]  ->  2
	            //           [ 0  4  3  5 ]  ->  1
	            //           [ 0  0  0  7 ]  ->  3
	            //             |  |  |  | 
	            //             v  v  v  v
	            // 0's x row: {4} 1  2  0 

	            //           [ X  1  2  3 ]  ->  0
	            //           [ X  2  0  1 ]  ->  1
	            //           [ X  4  3  5 ]  ->  0
	            //           [ X  0  0  7 ]  ->  2
	            //             |  |  |  | 
	            //             v  v  v  v
	            //            {0} 1  2  0 

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


}

class Person{
	
	private String name;
	private Map<String,Integer> map ;
	
	public Person(String name, Map<String,Integer> task){
		this.name = name;
		this.map = new HashMap<>();
		this.map = distanceMap(task); //pega cada tarefa e suas distancias e transforma para a distancia desta pessoa
	}
	
	private Map<String,Integer> distanceMap(Map<String,Integer> map){
		Set<String> keys = map.keySet();
		int d = 0;
		for(String k: keys){
			d =  map.get(k) - Hugarian.randInt(4,map.get(k)-1);
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
