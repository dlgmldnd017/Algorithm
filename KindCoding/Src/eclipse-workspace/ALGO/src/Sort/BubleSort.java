package Sort;

public class BubleSort {
	static int k=1;
	
	public static void main(String[] args) {
		int[] A = {0,0,1,1,1,1,1,1,1,1};
		
		for(int i=0; i<A.length-1; i++) {
			for(int j=0; j<A.length-1-i; j++) {
				if(A[j]<A[j+1]) {
					int tmp = A[j];
					A[j] = A[j+1];
					A[j+1] = tmp;
					
					printArray(A);
				}
			}
		}
	}

	private static void printArray(int[] a) {
		System.out.print(k++ + ": ");
		
		for(int i:a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}