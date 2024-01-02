import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x, ans;

	public Node(int y, int x, int ans) {
		this.y=y;
		this.x=x;
		this.ans = ans;
	}
}

public class Main {
	static int N, M, ans;
	static int[][] map;
	
	static boolean[][] visited;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = sc.readLine();
			
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}

		solve();
		System.out.println(ans);
	}

	static void solve() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 1));
		visited = new boolean[N][M];
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int y = cur.y;
			int x = cur.x;
			
			if((y==N-1)&&(x==M-1)) {
				ans = cur.ans;
				return;
			}
			
			for(int k=0; k<4; k++) {
				int ny = y+dy[k];
				int nx = x+dx[k];
				
				if(!inRange(ny, nx) || map[ny][nx]==0 || visited[ny][nx]) continue;
				
				visited[ny][nx]=true;
				q.add(new Node(ny, nx, cur.ans+1));
			}
		}
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}