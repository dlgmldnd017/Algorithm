package ALL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 와일드 패턴 변수와 검사 받는 문자열 변수
	private static String W, S;
	
	// 검사 받는 문자열의 수
	private static int N;
	
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			W = sc.readLine();
			N = Integer.parseInt(sc.readLine());
			
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				cache = new int[101][101];
				S = st.nextToken();
				if(solve(0,0) == 1) {
					sb.append(S + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int w, int s) {
		if(cache[w][s] != 0) return cache[w][s];
		
		while(w<W.length() && s<S.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
			++w;
			++s;
		}
		
		if(w==W.length()) return cache[w][s] = (s==S.length() ? 1:-1);
		
		if(W.charAt(w)=='*') {
			for(int skip=0; skip+s<=S.length(); skip++) {
				if(solve(w+1, s+skip)==1) return cache[w][s] = 1;
			}
		}
		
		return cache[w][s] = -1;
	}
}