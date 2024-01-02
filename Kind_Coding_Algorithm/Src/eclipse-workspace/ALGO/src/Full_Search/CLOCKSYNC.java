package Full_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CLOCKSYNC {
	// 시계
	private static int[] clocks = new int[16];
	
	// 스위치 
	// ex) 0행에는 0, 1, 2번의 시계를 회전한다.
	private static int[][] switches = { 
			{0, 1, 2 }, { 3, 7, 9, 11 }, { 4, 10, 14, 15 }, { 0, 4, 5, 6, 7 }, { 6, 7, 8, 10, 12 },
            { 0, 2, 14, 15 }, { 3, 14, 15 }, { 4, 5, 7, 14, 15 }, { 1, 2, 3, 4, 5 }, { 3, 4, 5, 9, 13 }
	};
	
	// 각각 시계 개수와 스위치 개수
	private static int clocks_size=16;
	private static int switches_size=10;
	
	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int C = Integer.parseInt(sc.readLine());
		for(int i=0; i<C; i++) {
			
			// 현재 시계의 상태를 초기화하는 과정
			for(int y=0; y<4; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0+(y*4); x<4*(y+1); x++) {
					clocks[x] = (Integer.parseInt(st.nextToken())/3)%4;
				}
			}
			sb.append(solve(0)+"\n");
		}
		System.out.println(sb);
	}
	
	// 재귀 호출하는 메인 메서드
	private static int solve(int cnt) {
		// cnt가 스위치 개수만큼 온다면 바로 check() 수행
		if(cnt==switches_size) return check()? 0:9999;
		
		// 비교할 수 없을 만큼 
		// ret 값을 초기화
		int ret=9999;
		
		// 4번 이상 돌리면 다시 원위치로 시계가 돌아온다는
		// 아이디어를 가지고 4까지만 수행
		// ex) i가 0, 1, 2 --> 회전 | 4 --> 다음 경우의 수를 위해 다시 원위치
		for(int i=0; i<4; i++) {
			ret = Math.min(ret, i+solve(cnt+1));
			rotate(cnt);
		}
		return ret;
	}
	
	// 현재 시계 상태가 12시를 가르키고 있다면
	// true 리턴하는 메서드
	private static boolean check() {
		for(int i:clocks) {
			if(i!=0) {
				return false;
			}
		}
		return true;
	}
	
	// 시계를 회전하는 메서드
	private static void rotate(int cnt) {
		for(int i:switches[cnt]) {
			clocks[i] = (clocks[i]+1)%4;
		}
	}
}