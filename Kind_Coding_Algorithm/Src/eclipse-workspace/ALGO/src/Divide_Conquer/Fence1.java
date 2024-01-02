package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fence1 {
	// 출력을 위한 변수
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			int N=Integer.parseInt(sc.readLine());
			int[] A=new int[N];
			
			// 나무 판자의 높이를 입력받는 과정
			st = new StringTokenizer(sc.readLine());
			for(int x=0; x<N; x++) {
				A[x] = Integer.parseInt(st.nextToken());
			}
			sb.append(solve(A)+"\n");
		}
		System.out.println(sb);
	}
	
	// 직사각형의 최대 크기(넓이)를 가진 
	// 값을 찾는 주요 메서드
	private static int solve(int[] a) {
		int ret=0;
		
		for(int y=0; y<a.length; y++) {
			int min=a[y];
			for(int x=y; x<a.length; x++) {
				min = Math.min(min, a[x]);
				
				// (y-x+1) * min a[x];
				ret = Math.max(ret, min*(x-y+1));
			}
		}
		return ret;
	}
}