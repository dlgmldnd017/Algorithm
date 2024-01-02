import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, ans;
	static int map[];
	
	static int cache[][];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= test_case; T++) {

			N = Integer.parseInt(sc.readLine());
			
			map = new int[N];
			int sum=0;
			
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
				sum += map[i];
			}
			
			// 합산 했을 때 나올 수 있는 크기 행
			// 순열 2^N인 열
			cache = new int[sum+1][1<<N];
			
			ans = solve(0, 0, 0, 0);
			
			sb.append("#" + T + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	static int solve(int depth, int left, int right, int visit) {
		
		// 기저사례(1): 왼쪽이 오른쪽보다 작을 경우
		if(left<right) return 0;
		
		// 캐시 히트
		if(cache[left][visit] !=0) return cache[left][visit];
		
		// 만약 모든 경우의 수를 구했을 경우
		// 캐시 저장
		if(depth==N) {
			ans++;
			return cache[left][visit]=1;
		}
		
		int cntSum=0;
		
		// 메모제이션을 위한 비트마스킹
		for(int i=0; i<N; i++) {
			if((visit&(1<<i))==0) {
				cntSum += solve(depth+1, left+map[i], right, visit | (1<<i));
				cntSum += solve(depth+1, left, right+map[i], visit | (1<<i));
			}
		}
		
		return cache[left][visit] = cntSum;
	}
}