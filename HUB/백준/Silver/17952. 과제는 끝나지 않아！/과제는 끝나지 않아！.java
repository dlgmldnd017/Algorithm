import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, idx, ans; // 각각 시간, 이전 업무 인덱스, 정답 변수들
	static int w[];	// 일인지 아닌지 저장하는 변수
	static int map[][]; // 일이라면 점수와 시간을 받는 변수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 시간을 입력받고, 초기화 진행
		N = Integer.parseInt(sc.readLine());
		w = new int[N];
		map = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			// 일인지 아닌지 확인하는 0과 1을 w에 저장하고 0이라면 continue
			w[i] = Integer.parseInt(st.nextToken());
			if(w[i]==0) continue;
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			
		}
		
		// 메서드 실행
		solve();
		System.out.println(ans);
	}

	static void solve() {
		
		// 시간만큼 반복
		for(int i=0; i<N; i++) {
			// 만약 일이 아니라면 
			if(w[i]==0) {
				// 이전 일의 위치를 찾아간다.
				if(idx>=0 && map[idx][1]!=0) {
					map[idx][1]--;
					
					// 만약 이전의 일이 끝났다면 점수 체크
					if(map[idx][1]==0) {
						ans += map[idx][0];
						
						// 이전의 일이 있는지 확인
						while(idx>=0 && map[idx][1]==0) idx--;
					}
				}
			}else { //일이라면 현재 일의 시간을 1분 --
				map[i][1]--;
				
				// 만약 1분짜리라면 점수를 바로 체크, 아니라면 idx에 값 저장
				if(map[i][1]==0) ans += map[i][0];
				else idx=i;
			}
		}
	}
}