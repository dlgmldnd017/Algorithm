import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, L, ans;
	static int map[][];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {

			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][2];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(sc.readLine());
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}

			ans = Integer.MIN_VALUE;
			solve(0, 0, 0);
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	static void solve(int idx, int score, int kal) {
		if(kal>L) return;
		
		if(idx==N) {
			if(ans<score) ans=score;
			return;
		}
		
		solve(idx+1, score+map[idx][0], kal+map[idx][1]);
		solve(idx+1, score, kal);
	}
}
