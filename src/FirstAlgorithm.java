
/*
 * Problem: Given an array with 0's and 1's find the minimum size of sub-array containing exactly k-zeros
 * */
import java.lang.Math;

public class FirstAlgorithm {

	public static void main(String[] args) {
		
		// Preparing input for the actual algorithm.
			int[] inputArray;
			int inputArrayLength = 10;
			int k = 1;
			
			try {
				inputArrayLength = Integer.parseInt(args[0]);				
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
	
	public static int MinArraySize(int k) {
		
		return 0;
	}
}
