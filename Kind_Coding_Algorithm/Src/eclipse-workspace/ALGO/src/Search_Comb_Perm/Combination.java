package Search_Comb_Perm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Combination {
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
			searchComb(A[0], A[1], new ArrayList<>());
			sb.append("\n");
		}
		System.out.println("\n"+sb);
	}

	private static void searchComb(int n, int m, ArrayList<Integer> arr) {
		
		// 뽑아야 할 숫자가 없다면
		// 출력 후 다시 새 조합을 찾음.
		if(m==0) {
			printArr(arr);
//			return;
		}
		
		int smallest = arr.isEmpty()? 0:arr.get(arr.size()-1)+1;
		
		for(int next=smallest; next<n; next++) {
			arr.add(next);
			searchComb(n, m-1, arr);
			arr.remove(arr.size()-1);
		}
	}
	
	private static void printArr(ArrayList<Integer> arr) {
		for(int i=0; i<arr.size(); i++) {
			sb.append(arr.get(i) + " ");
		}
		sb.append("\n");
	}
}