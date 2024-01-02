import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans;
	
	static int cache[][];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= test_case; T++) {

			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			ans = 0;
			if(N>M) {
				cache = new int[N+1][M+1];
				ans = solve(N, M);
			}
			else if(N<M) {
				cache = new int[M+1][N+1];
				ans = solve(M, N);
			}
			else ans=1;
			
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}

	static int solve(int n, int m) {
		if(cache[n][m]!=0) return cache[n][m];
		
		if(n==m || m==0) return 1;
		
		return cache[n][m] = solve(n-1, m)+solve(n-1, m-1);
	}
}