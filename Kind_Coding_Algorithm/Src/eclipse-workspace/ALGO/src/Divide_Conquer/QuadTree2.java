package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree2 {
	// 현재 쿼드 트리의 값 변수
	private static String quadTree;
	// iterator 변수
	private static int it;
	// 출력을 위한 변수
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			quadTree = sc.readLine(); 
			it=0;
			
			sb.append(solve()+"\n");
		}
		System.out.println(sb);
	}
	
	// 쿼드 트리를 해결하는 주요 메서드
	private static String solve() {
		// 먼저 하나의 값을 받는다.
		char pick = quadTree.charAt(it++);
		
		// 기저 사례(1): black이거나 white인 경우 해당 값 리턴
		if(pick=='b' || pick=='w') {
			return pick+"";
		}
		
		// x를 받는 기준으로 분할 시작하며
		// 각각 3,4,1,2 사분면을 뜻한다.
		String q3 = solve();
		String q4 = solve();
		String q1 = solve();
		String q2 = solve();
		
		return "x"+q1+q2+q3+q4;
	}
}