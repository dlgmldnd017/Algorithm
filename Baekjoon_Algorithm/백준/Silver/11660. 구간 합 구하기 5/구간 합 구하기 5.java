import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans;
	static int[][] map, arr;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N+1][N+1];
		arr = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(sc.readLine());
			int curSum=0;
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = curSum + map[i][j];  
				curSum += map[i][j];
			}
		}
		
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(sc.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			ans=0;
			solve(x1, y1, x2, y2);
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}

	public static void solve(int x1, int y1, int x2, int y2) {
		for(int i=x1; i<=x2; i++) {
			ans += arr[i][y2] - arr[i][y1-1];
		}
	}
}