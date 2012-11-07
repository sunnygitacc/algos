
/*
 * Problem: Given an array with 0's and 1's find the minimum size of sub-array containing exactly k-zeros
 * */
import java.lang.Math;
import java.util.HashMap;

public class FirstAlgorithm {

	// Preparing input for the actual algorithm.
	public static int[] inputArray;
	public static int inputArrayLength = 10;
	public static int k = 1;
	
	public static void main(String[] args) {
				
			try {
				inputArrayLength = Integer.parseInt(args[0]);	
				k = Integer.parseInt(args[1]);
			}
			catch (Exception e) {
				
			}
			
			// Randomize 0's and 1's
			inputArray = new int[inputArrayLength];
			for(int i=0; i < inputArrayLength; i++) {
				
				double value = Math.random();
				inputArray[i] = (value > 0.5) ? 1 : 0;		
				System.out.println("inputarray ["+i+"] = "+inputArray[i]);
			}
			// Call Algorithm with the input now.
			int size = MinArraySize(k);
			System.out.println("Min Sub Array of "+k+ " zeroes =" + size);
	}

	/*
	 * solution with some space. O(n) is a norm.
	 * TempArray will take all the zeroes indexes. Diff of start index and k+start index value is taken. 
	 * Min is calculated thus.    
	 * */
	
	public static int MinArraySize(int k) {
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		int iterator =0;
		for(int i=0; i < inputArrayLength; i++) {
			
			if(inputArray[i] == 1) {
				continue;
			}
			
			//temp.add(i);
			hm.put(iterator, i);
			System.out.println("hash ["+iterator+"] =" + i);
			iterator++;
			
		
		}
				
		int min = inputArrayLength;
		
		if(hm.isEmpty() || hm.size() < k ) {
			return 0;
		}

		for(int l =0; l < hm.size()-k; l++) {
			
			int var = hm.get(l+k-1) - hm.get(l) + 1;
			
			if(min > var) {
				min = var;
				System.out.println("Start: "+hm.get(l)+ " End: "+ hm.get(l+k-1));
			}
		}
		
		return min;
	}
}
