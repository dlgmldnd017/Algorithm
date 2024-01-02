package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUMPGAME2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int[][] board;
	private static int N;
	private static int[][] cache;
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			N = Integer.parseInt(sc.readLine());
			cache = new int[N][N];
			board = new int[N][N];
			
			// 캐시 배열 모두 -1로 초기화
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					cache[y][x] = -1;
				}
			}
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					board[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(solve(0,0)+"\n");
		}
		System.out.println(sb);
	}
	private static int solve(int y, int x) {
		// 기저 사례(1): 범위를 벗어나면 false
		if(y>=N || x>=N) return 0;
		
		// "끝" 지점에 도달한 경우 성공
		if(y==N-1 && x==N-1) return 1;
		
		// 메모이제이션
		if(cache[y][x] != -1) return cache[y][x];
		
		// jump 크기를 가지고 완전탐색 시작.
		int jumpSize = board[y][x];
		return cache[y][x] = (solve(y+jumpSize, x) | solve(y, x+jumpSize));
	}
}