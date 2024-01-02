package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TRIANGLEPATH2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 삼각형의 크기 변수와 삼각형 안의 숫자 변수
	private static int N;
	private static int[][] triangle;
	
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[101][101];
			N = Integer.parseInt(sc.readLine());
			triangle = new int[N][N];
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					triangle[y][x] = Integer.parseInt(st.nextToken()); 
				}
			}
			sb.append(solve(0,0)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int y, int x) {
		if(triangle[y][x] == 0) return 0;
		
		// 기저 사례(1): 맨 밑층(N-1)까지 도달할 경우
		if(y==N-1) return triangle[y][x];
		
		if(cache[y][x] != 0) return cache[y][x];
		
		int ret=0;
		ret = triangle[y][x] + Math.max(solve(y+1, x), solve(y+1, x+1));
		
		return cache[y][x] = ret;
	}
}