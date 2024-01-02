import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int map[][];

	static long dp[][][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());

		map = new int[N+1][N+1];
		dp = new long[N+1][N+1][3];

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0]=1;
		solve();
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}
	
	static void solve() {
		for(int i=1; i<=N; i++) {
			for(int j=3; j<=N; j++) {
				if(map[i][j]==1) continue;
				
				// 가로
				dp[i][j][0] = dp[i][j-1][0]+dp[i][j-1][2];
				
				if(i==1) continue;
				
				// 세로
				dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				
				if(map[i-1][j]==1 || map[i][j-1]==1) continue;
				
				// 대각선
				dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			}
		}
	}
}