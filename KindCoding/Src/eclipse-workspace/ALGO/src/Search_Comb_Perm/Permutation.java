package Search_Comb_Perm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Permutation {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		int c = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<c; i++){
			st = new StringTokenizer(sc.readLine());
			int N = st.countTokens();
			int[] A = new int[N];
			
			for(int j=0; j<N; j++) {
				A[j] = Integer.parseInt(st.nextToken());
			}
			int[] arr = bringArr(A[0],A[1]);
			searchPerm(arr, 0, A[0], A[1]);
			sb.append("\n");
		}
		System.out.println("\n"+sb);
	}
	
	private static void searchPerm(int[] arr, int depth, int n, int m) {
	    if (m == depth) {
	        printArr(arr, m);
	        return;
	    }
	 
	    for (int i=depth; i<n; i++) {
	        swap(arr, depth, i);
	        searchPerm(arr, depth + 1, n, m);
	        swap(arr, depth, i);
	    }
	}
	private static int[] bringArr(int n, int m) {
		int[] ret = new int[n];
		
		for(int i=0; i<n; i++) {
			ret[i] = i;
		}
		return ret;
	}

	private static void swap(int[] arr, int depth, int i) {
	    int temp = arr[depth];
	    arr[depth] = arr[i];
	    arr[i] = temp;
	}
	
	private static void printArr(int[] arr, int r) {
		for(int i=0; i<r; i++) {
			sb.append(arr[i]+" ");
		}
		sb.append("\n");
	}
}