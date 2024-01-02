package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WILDCARD1 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			String W = sc.readLine();
			int N = Integer.parseInt(sc.readLine());
			
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				String S = st.nextToken();
				if(solve(W, S)) {
					sb.append(S+" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean solve(String w, String s) {
		int pos=0;
		
		// 만약에 와일드 카드 패턴 위치가 '?' 이거나 패턴과 문자열이 같을 경우 
		// 현재 위치를 ++(통과)한다.
		while(pos<w.length() && pos<s.length() && (w.charAt(pos)=='?' || w.charAt(pos) ==s.charAt(pos))) 
			++pos;
		
		// 만약 위 조건 문에서 위치 값이 패턴 끝 값이랑 같을 경우
		// 문자열도 값 비교 후 대응되는 지 체크한다.
		if(pos==w.length()) return pos==s.length();
		
		// 패턴이 '*'로 시작하면 (pos+1)의 위치에서 재귀 호출한다.
		// 이때 문자열은 skip변수를 +1씩 올려서 해당 문자열 위치부터 재귀 호출한다.
		if(w.charAt(pos)=='*') {
			for(int skip=0; pos+skip<=s.length(); skip++) {
				if(solve(w.substring(pos+1), s.substring(pos+skip))) return true;
			}
		}
		return false;
	}
}