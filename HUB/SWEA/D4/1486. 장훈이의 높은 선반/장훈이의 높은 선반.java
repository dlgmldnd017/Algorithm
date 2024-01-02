import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, B, ans;
	static int H[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			H = new int[N];
			
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}

			ans = Integer.MAX_VALUE;
			solve(0, 0);
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int depth, int sum) {
		if(B<=sum) {
			ans = Math.min(ans, sum-B);
			return;
		}
		
		if(depth==N) return;
		
		solve(depth+1, sum+H[depth]);
		solve(depth+1, sum);
	}
}