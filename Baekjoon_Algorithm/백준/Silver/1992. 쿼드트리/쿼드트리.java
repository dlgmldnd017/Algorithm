import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(sc.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = sc.readLine();

			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}

		solve(N, 0, 0);
	}

	static void solve(int cur, int R, int C) {
		if(print(cur, R, C)) {	// 쿼드 트리 값들이 모두 동일할 때
			System.out.print(map[R][C]); // 출력
			return;
		}

		// 만약 쿼드 트리 값들이 다르다면
		// 분할 정복 시작
		System.out.print("(");
		solve(cur/2, R, C);
		solve(cur/2, R, C+cur/2);
		solve(cur/2, R+cur/2, C);
		solve(cur/2, R+cur/2, C+cur/2);
		System.out.print(")");
	}

	// 쿼드 트리 값들이 모두 동일한지 확인
	static boolean print(int cur, int R, int C) {
		int tmp = map[R][C];

		for(int i=R; i<R+cur; i++) {
			for(int j=C; j<C+cur; j++) {
				if(tmp!=map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}