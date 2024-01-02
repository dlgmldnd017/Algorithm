import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, ans;
	static int map[], cache[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {

			N = Integer.parseInt(sc.readLine());
			
			map = new int[N];
			cache = new int[N];
			
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
				cache[i] = 1;
			}
			
			ans = solve();
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	static int solve() {
		int max = Integer.MIN_VALUE;
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(map[i]>map[j]) {
					cache[i] = Math.max(cache[i], cache[j]+1);
				}
			}
			max = Math.max(max, cache[i]);
		}
		
		return max;
	}
}