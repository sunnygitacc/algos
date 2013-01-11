package arrays;

/*
 * Everything related to bit operations and 0's, 1's in an array. 
 * Author: Sunil Kata
 * */

public class BitArray {

	public BitArray(){
		
	}
	
	/*
	 * Push all non-zero elements of the array to the right(end of array), in place.
	 * 
	 * One solution: Start at n with loop variable and index variable. index points to leftmost non-zero
	 * element so far, i.e., index is the start of rightmost sub-array that has no zeroes.
	 * If a non-zero element is found by the loop variable, index is decremented (meaning sub-array 
	 * increased towards left) by 1.
	 * Index and loop part only when a zero is encountered.  	  
	 * */
	
	public void nonZeroShift(int input[]) {
		int inputSize = input.length;
		
		if(inputSize <= 0) // invalid array
			return;

		int index = inputSize;	// This handles a border case, where the sub array of size 1.	

		for ( int i = inputSize-1; i > -1; i--) {
			if(input[i] != 0) {
				index--;
				input[index] = input[i];
			}
		}
		
		for ( int k = 0; k < index; k++ ) {
			input[k] = 0;
		}
		return;
	}
}
