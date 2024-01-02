package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ASYMTILING1 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int MOD = 1000000007;
	
	private static int[] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[101];
			N = Integer.parseInt(sc.readLine());
			sb.append(solve(N)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int width) {
		// 만약 홀수라면, (전체 타일링 방법의 수 - 대칭인 타일링 방법의 수)
		if(width%2==1) return (tiling(width) - tiling(width/2) + MOD) % MOD;
		
		// 만약 짝수라면, 
		//(전체 타일링 방법의 수 - (중간이 가로 2칸일 경우) - (중간 기준으로 왼쪽과 오른쪽 크기가 같은 경우))
		int ret = tiling(width);
		ret = (ret - tiling(width/2-1) + MOD) %MOD;	// 중간이 가로 2칸일 경우
		ret = (ret - tiling(width/2) + MOD) %MOD;	// 중간 기준으로 왼쪽과 오른쪽 크기가 같은 경우
		
		return ret;
	}
	
	private static int tiling(int width) {
		// 기저 사례(1): 더 이상 사각형을 쪼갤 수 없는 경우
		if(width<=1) return 1;
		
		if(cache[width]!=0) return cache[width];
		
		// 가로 두 칸을 없앨 경우: solve(width-2)
		// 세로 두 칸을 없앨 경우: solve(width-1)
		return cache[width] = (tiling(width-1) + tiling(width-2)) % MOD;
	}
}