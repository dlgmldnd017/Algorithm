package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TRIPATHCNT {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int[][] A;
	private static int N;
	
	private static int[][] countCache;
	private static int[][] pathCache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			countCache = new int[101][101];
			pathCache = new int[101][101];
			
			N = Integer.parseInt(sc.readLine());
			A = new int[N][N];
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					A[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append(solve(0, 0)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int y, int x) {
		if(y==N-1) return 1;
		
		if(countCache[y][x] != 0) return countCache[y][x];
		
		// 만약 (y+1, x+1)이 더 크다면 아래 오른쪽에서 시작
		// 만약 (y+1, x)이 더 크다면 아래에서 시작
		// 단, 둘 다 최대 경로가 같다면 둘다 카운트 ++
		if(path(y+1, x+1) >= path(y+1,x)) countCache[y][x] += solve(y+1, x+1);
		if(path(y+1, x+1) <= path(y+1,x)) countCache[y][x] += solve(y+1, x);
		
		return countCache[y][x];
	}
	
	// 삼각형 위의 최대 경로 합 구하는 메서드
	private static int path(int y, int x) {
		if(y==N-1) return A[y][x];
		
		if(pathCache[y][x] != 0) return pathCache[y][x];
		
		return pathCache[y][x] = Math.max(path(y+1, x)+A[y][x], path(y+1, x+1)+A[y][x]);
	}
}