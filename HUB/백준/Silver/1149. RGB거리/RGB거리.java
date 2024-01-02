import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int tmp[] = {1, 1, 1};
	static int map[][];

	static int cache[];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());

		map = new int[N][N];

		cache = new int[N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();
	}

	static void solve() {
		for(int i=1; i<N; i++) {
			map[i][0] += Math.min(map[i-1][1], map[i-1][2]);
			map[i][1] += Math.min(map[i-1][0], map[i-1][2]);
			map[i][2] += Math.min(map[i-1][0], map[i-1][1]);
		}
		System.out.println(Math.min(map[N-1][0], Math.min(map[N-1][1], map[N-1][2])));
	}
}