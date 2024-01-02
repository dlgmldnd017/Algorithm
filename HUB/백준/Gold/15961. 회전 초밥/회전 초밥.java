import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, d, k, c, ans; // 각각 접시의 수, 초밥의 가짓수, 연속해서 먹는 수, 쿠폰 번호, 정답
	static int map[]; // 초밥의 상태를 저장
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 각각 필요한 변수 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 제일 끝에 있는 초밥을 선택하고 0, 1, 2를 선택해야 하므로
        // N+K-1 크기로 지정
        map = new int[N+k-1];
        for (int i = 0; i <N; i++) {
        	map[i] = Integer.parseInt(br.readLine());
        }
        
        // 그런 다음, 0, 1, 2를 저장
        for (int i = 0; i<k-1; i++) {
   		 map[N+i] = map[i];
        }

        // 답을 구하기 위해 메서드 실행
        solve();
        System.out.println(ans);
    }
    
    // 투 포인터 + 슬라이딩 윈도우
    static void solve() {
    	
    	// 조합의 수만큼 만들고 쿠폰번호를 1로 지정
         int[] visited = new int[d+1];
         visited[c] = 1;

         // 투 포인터 start, end와 종류를 알려주는 cnt;
         int start=0, end=0, cnt=1; //cnt가 1인것은 쿠폰번호를 포함함.

         // start+k에서 초밥 크기만큼 반복
         while (start+k < map.length) {
        	 
        	 // 해당하는 초밥 ++;
        	 visited[map[end]]++;
             
        	 // 만약 처음 ++한 거라면 종류 +1
             if (visited[map[end]] == 1) cnt++;

             // 만약 end가 k만큼 전진했다면
             // start를 움직일 차례
             if (end >= start+k) {
            	 
            	 // start가 먹었던 것을 --
                 visited[map[start]]--;
                 
                 // 만약 뺀 것이 0이라면 종류 -1
                 if (visited[map[start]] == 0) {
                     cnt--;
                 }
                 
                 // 그리고 start 포인터 움직임
                 start++;
             }

             // 계속해서 종류의 최대치를 구한다.
             ans = Math.max(ans, cnt);
             end++;
         }
    }
}