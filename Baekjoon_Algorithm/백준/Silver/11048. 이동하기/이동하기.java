import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans;
	static int map[][];
	static int cache[][];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=1; i<M; i++) {
			map[0][i] += map[0][i-1];
		}

		for(int i=1; i<N; i++) {
			map[i][0] += map[i-1][0];
		}

		//		solve(1, 1, 0);
		for(int i=1; i<N; i++) {
			for(int j=1; j<M; j++) {
				map[i][j] += Math.max(map[i-1][j], map[i][j-1]);
			}
		}

		System.out.println(map[N-1][M-1]);
	}
	//
	//	static int solve(int y, int x, int cnt) {
	//		// 기저 사례
	//		if(y>=N  || x>=M) return 0;
	//
	//		return map[y][x] += Math.max(map[y-1][x], map[y][x-1]);
	//	}
}