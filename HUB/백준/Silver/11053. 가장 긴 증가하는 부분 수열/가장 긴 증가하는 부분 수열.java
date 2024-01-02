import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int map[], dp[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());

		dp = new int[N];
		Arrays.fill(dp, 1);
		
		map = new int[N];
		
		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		ans=1;
		solve();
		System.out.println(ans);
	}

	static void solve() {
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(map[i]<=map[j]) continue;
				
				dp[i] = Math.max(dp[i], dp[j]+1);
			}
			
			ans = Math.max(ans, dp[i]);
		}
	}
}
