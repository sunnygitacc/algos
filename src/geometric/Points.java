package geometric;

import java.lang.Math;

/*
 * Closest Pairs.
 * Problem and solution can be found here http://en.wikipedia.org/wiki/Closest_pair_of_points_problem#Planar_case
 * 
 * Basically problem is to find the shortest distance between two points in 2D space.
 * 
 * Solution: Divide and conquer. Merge is crucial here. Min dist from left and Min dist from right are 
 * are only valuable if we could find the some min distance across the mid point too. 
 * Catch is to consider only those points which are at distance minimum of two sides. If 
 * points across the mid have much smaller distance that minimum of two sides, we take that 
 * as the new minimum of the two sides.
 * */

public class Points {

	public Points() {
			
	}
	
	public void closestPairs() {
		// Two dimensional Array. [][]? sort them then? Our own sort.
		int pointsX[] = new int[10];
		int pointsY[] = new int[10];
		
		// Create some input.
		for(int i =0; i < 10; i++) {			
			pointsX[i] = (int) Math.floor( 1 + 100 * Math.random());
			pointsY[i] = (int) Math.floor( 1 + 100 * Math.random());
		}
		
		printX(pointsX);
		printX(pointsY);
		// We have some points in the range 1 - 100. 
		// Now sort along the x axis. 
		quicksort(pointsX, pointsY, 0, pointsX.length-1);
		
		// Now divide and conquer on sorted points.
		
		closestPairBruteForce(pointsX, pointsY);
		double shortest = closestPairsOnSorted(pointsX, pointsY, 0, pointsX.length-1);
		System.out.println("\nShortest using smart algo is:" + shortest);
	}

	public void closestPairBruteForce(int x[], int y[]) {
		double shortestDist = Integer.MAX_VALUE;
		int index1 = Integer.MAX_VALUE;
		int index2 = Integer.MAX_VALUE;
		for(int i = 0; i < x.length; i++) {
			for ( int j = i+1; j < y.length; j++) {
				double dist = Distance(x[i], y[i], x[j], y[j]);
				if(shortestDist > dist) {
					shortestDist = dist;
					index1 = i;
					index2 = j;
				}
			}
		}
		
		System.out.print("Closest Pair is: ("+x[index1]+" , " + y[index1] + ") ("+x[index2]+" , "+y[index2] + ")" );
		System.out.println("\nDistance is :"+shortestDist);
	}
	
	public double Distance(int x1, int y1, int x2, int y2) {
		return Math.abs(Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)));
	}
	
	public double closestPairsOnSorted(int x[], int y[], int start, int end) {
		// find x mid. find left short points first. find right short points next. Merge step later
		int mid = (int) Math.floor((end + start )/2);
		double leftDist = 0;
		double rightDist = 0;
		
		if(start < mid-1) {
			leftDist = closestPairsOnSorted(x, y, start, mid-1);
			System.out.println("leftDist =" + leftDist);
		}
		
		if(mid+1 < end) {
			rightDist = closestPairsOnSorted(x, y, mid+1, end);
			System.out.println("rightDist =" + rightDist);
		}
		
		double minDist;
		double acrossDist;
		
		if(start >= mid-1 || mid+1 >= end) {
			return Integer.MAX_VALUE;
		}
		else {
			minDist = Math.min(leftDist, rightDist);
			
			// find the farthest index toward left.
			
			int indX = mid-1;
			double leftIndexDist = Distance(x[indX], y[indX], x[mid], y[mid]);
			while( leftIndexDist < minDist ) {
				indX--;
				if(indX == start - 1)
					break;
				leftIndexDist = Distance(x[indX], y[indX],x[mid], y[mid]);			
			}
			indX++;
			
			int indY = mid+1;
			double rightIndexDist = Distance(x[indY], y[indY], x[mid], y[mid]);
			while( rightIndexDist < minDist ) {
				indY++;
				if(indY == end + 1)
					break;
				rightIndexDist = Distance(x[indY], y[indY], x[mid], y[mid]);
			}
			indY--;
			acrossDist = Integer.MAX_VALUE;
			for ( int k = indX ; k <= indY; k++ ) {			
				for( int l = k+1; l < indY; l++) {
					double currentDist = Distance(x[k], y[k], x[l], y[l]);
					
					if( acrossDist > currentDist ) {						
						acrossDist = currentDist;
						System.out.println("("+x[k]+","+y[k]+") ("+x[l]+","+y[l]+") Dist="+acrossDist);						
					}
				}
			}
			
			if(acrossDist < minDist )
				return acrossDist;
			else
				return minDist;
		}
				
	}
	
	public void printX(int a[]) {
		for(int i=0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public void quicksort(int toBeSorted[], int toBeSettled[], int start, int end) {
			
			int position = separate(toBeSorted, toBeSettled, start, end);

			if(start < position-1 ) {
				quicksort(toBeSorted, toBeSettled, start, position-1);
			}
			if(position+1 < end ) {
				quicksort(toBeSorted, toBeSettled, position+1, end);
			}			
	}
	
	public int separate(int toBeSorted[], int toBeSettled[], int pivot, int end) {
		int leftIndex = pivot;
		int rightIndex = end;
		
		while(leftIndex <= rightIndex) {
		
			while(toBeSorted[leftIndex] <= toBeSorted[pivot] && leftIndex < end && leftIndex <= rightIndex) {
				leftIndex++;
			}
			
			while(toBeSorted[rightIndex] > toBeSorted[pivot] && rightIndex > pivot) {
				rightIndex--;
			}
	
			if(leftIndex < rightIndex) {
				swapBoth(toBeSorted, toBeSettled, leftIndex, rightIndex);
			}
			else {
				swapBoth(toBeSorted, toBeSettled, rightIndex, pivot);
				break;
			}
		}
					
		return rightIndex;	
	}
	
	public void swapBoth(int a[], int b[], int left, int right) {
		int temp1 = a[left];
		int temp2 = b[left];
		
		a[left] = a[right];
		b[left] = b[right];
		
		a[right] = temp1;
		b[right] = temp2;
	}
}
