package Full_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PICNIC {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static boolean[][] areFriends;
	
	public static void main(String[] args) throws Exception {
		int c = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<c; i++){
			st = new StringTokenizer(sc.readLine());
			int N = st.countTokens();
			int[] A = new int[N];
			
			for(int j=0; j<N; j++) {
				A[j] = Integer.parseInt(st.nextToken());
			}
			
			areFriends = new boolean[A[0]][A[0]];
			
			st = new StringTokenizer(sc.readLine());
			N = st.countTokens();
			int[] B = new int[N];
			
			for(int j=0; j<N; j++) {
				B[j] = Integer.parseInt(st.nextToken());
				if(j%2==1) {
					areFriends[B[j-1]][B[j]]=areFriends[B[j]][B[j-1]] = true;
				}
			}
			System.out.println(countPairings(new boolean[A[0]]));
		}
	}
	
	private static int countPairings(boolean[] stu) {
		int ret=0, idx=-1, n=stu.length;
		
		for(int i=0; i<n; i++) {
			if(!stu[i]) {
				idx=i;
				break;
			}
		}
		
		if(idx==-1) return 1;
		
		for(int pairWith=idx+1; pairWith<n; pairWith++) {
			if(!stu[pairWith] && areFriends[idx][pairWith]) {
				stu[idx]=stu[pairWith]=true;
				ret += countPairings(stu);
				stu[idx]=stu[pairWith]=false;
			}
		}
		return ret;
	}
}