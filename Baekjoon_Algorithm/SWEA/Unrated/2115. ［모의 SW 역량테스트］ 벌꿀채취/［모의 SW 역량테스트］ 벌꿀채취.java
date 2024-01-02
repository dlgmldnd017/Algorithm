import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C, ans;
	static int map[][], dp[][];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(sc.readLine());
				
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp = new int[N][N];
			ans = Integer.MIN_VALUE;
			
			solve();
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				makeDP(i, j, 0, 0, 0);
			}
		}
		
		calcMax(0, 0, 0, 0);
	}
	
	static void makeDP(int i, int j, int sum, int sumAll, int depth) {
		if(sum>C) return;
		
		if(depth==M) {
			dp[i][j-M] = Math.max(dp[i][j-M], sumAll);
			return;
		}
		
		makeDP(i, j+1, sum+map[i][j], sumAll+(map[i][j]*map[i][j]), depth+1);
		makeDP(i, j+1, sum, sumAll, depth+1);
	}
	
	static void calcMax(int i, int j, int sum, int depth) {
		if(j+M>N) {
			calcMax(i+1, 0, sum, depth);
			return;
		}
		
		if(depth==2) {
			ans = Math.max(ans, sum);
			return;
		}
		
		if(i==N) return;
		
		calcMax(i, j+M, sum+dp[i][j], depth+1);
		calcMax(i, j+1, sum, depth);
	}
}