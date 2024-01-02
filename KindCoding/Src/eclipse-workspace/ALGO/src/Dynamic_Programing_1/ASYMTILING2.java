package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ASYMTILING2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int MOD = 1000000007;
	
	private static int[] cache1, cache2;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache1 = new int[101];
			cache2 = new int[101];
			N = Integer.parseInt(sc.readLine());
			sb.append(solve(N)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int width) {
		// 기저 사례(1): 너비가 2 이하인 경우
		if(width<=2) return 0;
		
		// 메모제이션
		if(cache2[width]!=0) return cache2[width];
		
		int ret=cache2[width];
		
		// 각각 그림 (a), (b), (c), (d)를 나타낸다.
		ret = solve(width-2) % MOD; 
		ret = (ret + solve(width-4)) % MOD;
		ret = (ret + tiling(width-3)) % MOD;
		ret = (ret + tiling(width-3)) % MOD;
		
		return ret;
	}
	
	private static int tiling(int width) {
		// 기저 사례(1): 더 이상 사각형을 쪼갤 수 없는 경우
		if(width<=1) return 1;
		
		if(cache1[width]!=0) return cache1[width];
		
		// 가로 두 칸을 없앨 경우: solve(width-2)
		// 세로 두 칸을 없앨 경우: solve(width-1)
		return cache1[width] = (tiling(width-1) + tiling(width-2)) % MOD;
	}
}