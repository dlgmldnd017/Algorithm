package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Polyomino {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	
	private static int MOD = 10*1000*1000;
	
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[101][101];
			N = Integer.parseInt(sc.readLine());
			
			// 맨 윗줄부터 1개, 2개, ... , N개까지
			// 놓는 경우의 수를 세기 위해 j<=N까지
			int result=0;
			for(int j=1; j<=N; j++) {
				result += solve(N, j);
			}
			sb.append(result%MOD+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int n, int first) {
		if(n==first) return 1;
		
		if(cache[n][first]!=0) return cache[n][first];
		
		int ret=0;
		for(int second=1; second<=n-first; second++) {
			int add = second+first-1;
			add *= solve(n-first, second);
			add %= MOD;
			ret += add;
			ret %= MOD;
		}
		return cache[n][first] = ret;
	}
}