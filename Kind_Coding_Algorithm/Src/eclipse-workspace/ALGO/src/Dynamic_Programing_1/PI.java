package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PI {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static String N;
	
	private static int[] cache;
	
	public static void main(String[] args) throws IOException {
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[10002];
			N = sc.readLine();
			sb.append(solve(0)+"\n");
		}
		System.out.println(sb);
	} 

	private static int solve(int begin) {
		// 기저사례(1): 수열을 더이상 쪼갤 수 없을 경우
		if(begin+3>N.length()) return 0;
		
		if(cache[begin] != 0) return cache[begin];
		
		// 초기 리턴값을 999을 잡고 분할을 시작한다.
		int ret = 999;
		for(int L=3; L<=5; L++) {
			if(begin+L <= N.length()) cache[begin] = ret = Math.min(ret, solve(begin+L) + classify(begin, begin+L));
		}
		return ret;
	}
	
	private static int classify(int a, int b) {
		String M = N.substring(a, b);
		
		// 모든 숫자가 같은지 확인하는 과정
		if(isEqual(M)) return 1;
		
		// 등차수열을 확인하는 과정
		boolean progressive = true;
		for(int i=0; i<M.length()-1; i++) {
			if((M.charAt(i+1) - M.charAt(i)) != (M.charAt(1) - M.charAt(0))) progressive = false;
		}
		
		// 등차수열이며 숫자가 1씩 단조 증가 또는 단조 감소한 경우
		// 난이도 2를 리턴
		if(progressive && Math.abs(M.charAt(1)-M.charAt(0))==1) return 2;
		
		// 두 개의 숫자가 번갈아가는 지 확인하는 과정
		boolean alternating = true;
		for(int i=0; i<M.length()-2; i++) {
			if(M.charAt(i) != M.charAt(i+2)) alternating = false;
		}
		
		// 두 개의 숫자가 번갈아가며 나타날 경우
		// 난이도 4를 리턴
		if(alternating) return 4;
		
		// 숫자가 등차 수열을 이룰 경우
		// 난이도 5를 리턴
		if(progressive) return 5;
		
		// 이외숫자 0리턴
		return 10;
	}
	
	// 모든 숫자가 같은지 확인하는 메서드
	private static boolean isEqual(String M) {
		for(int i=0; i<M.length()-1; i++) {
			if(M.charAt(i) != M.charAt(i+1)) return false;
		}
		return true;
	}
}