import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, W, B;
	static int[][] map;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			
			st = new StringTokenizer(sc.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(N, 0, 0);
		for(int i=0; i<sb.length(); i++) {
			if(sb.charAt(i)=='0') W++;
			else B++;
		}
		System.out.println(W+"\n"+B);
	}
	
	static void solve(int size, int R, int C) {
		int tmp = map[R][C];
		
		for(int i=R; i<R+size; i++) {
			for(int j=C; j<C+size; j++) {
				if(tmp!=map[i][j]) {
					solve(size/2, R, C);
					solve(size/2, R, C+size/2);
					solve(size/2, R+size/2, C);
					solve(size/2, R+size/2, C+size/2);
					return;
				}
			}
		}
		sb.append(tmp);
	}
}