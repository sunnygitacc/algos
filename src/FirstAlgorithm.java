/*
 * Problem: Given an array with 0's and 1's find the minimum size of sub-array containing exactly k-zeros
 * Learnings at the bottom.
 * @author: Sunil Kata.
 * */


import java.lang.Math;
import java.util.HashMap;
import arrays.BitArray;
import geometric.Points;
import trees.BinaryTree;

public class FirstAlgorithm {

	// Preparing input for the actual algorithm.
	public static int[] inputArray;
	public static int inputArrayLength = 10;
	public static int k = 1;
	public static int[] dupArray;
	public static int destructiveIterator = 0;
	
	public static void main(String[] args) {
				
			try {
				inputArrayLength = Integer.parseInt(args[0]);	
				k = Integer.parseInt(args[1]);
			}
			catch (Exception e) {
				
			}
			
			// Randomize 0's and 1's
			inputArray = new int[10];
			dupArray = new int[inputArrayLength];
			for(int i=0; i < 10; i++) {
				
//				double value = Math.random();
//				inputArray[i] = (value > 0.9) ? 1 : 0;		
//				System.out.println("inputarray ["+i+"] = "+inputArray[i]);
//				dupArray[i] = inputArray[i];
				inputArray[i] = i;
 			}
			// Call Algorithm with the input now.
			//int size = MinArraySizeDestructive(k);
			//System.out.println("Min Sub Array of "+k+ " zeroes =" + size);

			// create BitArray object here. Call nonZeroShift
//			BitArray ba = new BitArray();
//			ba.nonZeroShift(inputArray);
//
//			for ( int i = 0; i < inputArray.length; i++ )
//				System.out.println("Array["+i+"] = "+inputArray[i]);
			
//			BitArray ba = new BitArray();
//			ba.Perm(inputArray, 0);
			
//			Points a = new Points();
//			a.closestPairs();
			
			BinaryTree bt = new BinaryTree();
			bt.createTree();
			//bt.traverseBreadthFirst();
			//bt.traverseDepthFirst();
			//bt.traverseDepthFirstRecursive(bt.root);
			//bt.LCARecursive(bt.root, 15, 18);
			bt.LCAIterative(20,14);
			//bt.printLCA();
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
			}
		}
		
		return min;
	}
	
	/*
	 * The Question is to do without space and O(n). 
	 * We could go destructive way. Replace the hash elements in the array itself, effectively 
	 * destroying the array. 
	 * We could recover the array. But not sure, of all the cases.
	 * */
	
	public static int MinArraySizeDestructive(int k) {
				
		for(int i=0; i< inputArrayLength; i++) {

			if(inputArray[i] == 0) {
				
				inputArray[destructiveIterator] = i;				
				destructiveIterator++;				
			}
		}
		
		int min = inputArrayLength;
		
		if(destructiveIterator == 0 || destructiveIterator < k) {
			recover();
			return 0;
		}
		
		for (int iter=0; iter < destructiveIterator-k; iter++) {
			
			int var = inputArray[iter+k-1] - inputArray[iter] + 1;
			
			if(min > var) {
				min = var;
			}
		}
		
		recover();
		
		System.out.println("Success!");
		return min;
	}
	
	public static void recover() {

		for(int recoverIter = destructiveIterator-1; recoverIter >= 0; recoverIter--) {
			
				inputArray[inputArray[recoverIter]] = 0;
		}
		
		for(int test=0; test < inputArrayLength; test++) {
			if(inputArray[test] !=0 ) {
				inputArray[test] = 1;
			}
			
			if(inputArray[test] != dupArray[test]) {
				
				System.out.println("Failed");				
			}
		}
	}
	/*
	 * Border cases are too important to be left out. 
	 * So for arrays that start at 0, it is very important to have an idea of 0, max cases all the time.
	 * All the time --> Every loop and at every function return.
	 * */
 }

