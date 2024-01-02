package Sort;

public class Insert {
	public static void main(String[] args) {
		int[] A = {4,3,1,5,2};
		
		insert(A);
		for(int i:A) {
			System.out.print(i + " ");
		}
	}
	
	public static void insert(int[] A){
		for(int i=0; i<A.length; i++) {
			int n = A[i];
			int j = i;
			
			while(j>0 && A[j-1] > A[j]) {
				int tmp = A[j-1];
				A[j-1] = A[j];
				A[j] = tmp;
				j--;
			}
		}
	}
}
