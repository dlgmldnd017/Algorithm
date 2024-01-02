package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WILDCARD2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static String W, S;
	private static int[][] cache;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			W = sc.readLine();
			int N = Integer.parseInt(sc.readLine());
			
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				cache = new int[101][101];
				
				S = st.nextToken();
				if(solve(0, 0)==1) {
					sb.append(S+" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	// 리턴 값이 -1이면 false, 1이면 true, 0이면 캐시값에 값 없음
	private static int solve(int w, int s) {
		// 메모이제이션
		if(cache[w][s] != 0) return cache[w][s];

		while(w<W.length() && s<S.length() && (W.charAt(w)=='?' || W.charAt(w)==S.charAt(s))) {
			++w;
			++s;
		}

		if(w==W.length()) return cache[w][s] = (s ==S.length())?1:-1;

		if(W.charAt(w)=='*') {
			for(int skip=0; s+skip<=S.length(); skip++) {
				if(solve(w+1, s+skip)==1) return cache[w][s] = 1;
			}
		}
		return cache[w][s]=-1;
	}
}