package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TILING {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int MOD=1000000007;
	
	private static int[] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[101];
			
			sb.append(solve(Integer.parseInt(sc.readLine()))+"\n");
		}
		System.out.println(sb);
	}
	private static int solve(int width) {
		// 기저 사례(1): 더 이상 사각형을 쪼갤 수 없는 경우
		if(width<=1) return 1;
		
		if(cache[width]!=0) return cache[width];
		
		// 가로 두 칸을 없앨 경우: solve(width-2)
		// 세로 두 칸을 없앨 경우: solve(width-1)
		return cache[width] = (solve(width-1) + solve(width-2)) % MOD;
	}
}