package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CLIMB1 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 일 수와 깊이이다.
	private static int M, N;
	
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[101][101];
			st = new StringTokenizer(sc.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			sb.append((solve(0, 0)/Math.pow(2, M))+"\n");
		}
		System.out.println(sb);
	}
	private static int solve(int days, int climbed) {
		// 기저 사례(1): M일이 모두 지난 경우
		if(days==M) return climbed>=N ? 1:0;
		
		// 메모제이션
		if(cache[days][climbed] != 0) return cache[days][climbed];
		
		return solve(days+1, climbed+1) + solve(days+1, climbed+2);
	}
}