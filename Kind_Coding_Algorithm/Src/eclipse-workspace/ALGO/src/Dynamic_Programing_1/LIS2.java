package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LIS2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static ArrayList<Integer> S;
	
	private static int[] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			N = Integer.parseInt(sc.readLine());
			S = new ArrayList<>();
			cache = new int[101];
			
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				S.add(Integer.parseInt(st.nextToken()));
			}
			
			sb.append(solve(-1)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int start) {
		if(cache[start+1] != 0) return cache[start+1];
		
		// 어떤 원소이든 재귀호출을 무조건 1번 이상
		// 수행하므로 ret값은 0으로 초기화
		int ret=0;
		
		// start부터 next++하여 계속 비교
		for(int next=start+1; next<N; next++) {
			if(start==-1 || S.get(start) < S.get(next)) cache[start+1] = ret = Math.max(ret, solve(next)+1);
		}
		return ret;
	}
}