import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans;
	static int map[][];

	static boolean visited[][];

	static int dy[] = {0, 0, -1, 1};
	static int dx[] = {-1, 1, 0, 0};

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

		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j]=true;
				solve(i, j, map[i][j], 1);
				visited[i][j]=false;
			}
		}
		
		System.out.println(ans);
	}

	static void solve(int y, int x, int sum, int depth) {
		if(depth==4) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int k=0; k<4; k++) {
			int ny = y+dy[k];
			int nx = x+dx[k];
			
			if(!inRange(ny, nx) || visited[ny][nx]) continue;
			
			if(depth==2) {
				visited[ny][nx]=true;
				solve(y, x, sum+map[ny][nx], depth+1);
				visited[ny][nx]=false;
			}
			
			visited[ny][nx]=true;
			solve(ny, nx, sum+map[ny][nx], depth+1);
			visited[ny][nx]=false;
		}
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N)&&(x>=0&&x<M);
	}
}