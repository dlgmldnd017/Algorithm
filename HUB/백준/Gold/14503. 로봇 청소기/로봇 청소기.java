import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static int N, M, R, C, d, ans;
	static int map[][];

	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(sc.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans=1;
		solve();
		System.out.println(ans);
	}

	static void solve() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(R, C));
		
		boolean visited[][] = new boolean[N][M];
		visited[R][C]=true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			R = cur.y;
			C = cur.x;
			
			// 반시계 방향으로 돈다.
			for(int k=0; k<4; k++) {
				d--;
				if(d<0) d=3;
				
				int ny = R+dy[d];
				int nx = C+dx[d];

				if(!inRange(ny, nx) || map[ny][nx]==1 || visited[ny][nx]) continue; 
				
				q.add(new Node(ny, nx));
				visited[ny][nx]=true;
				ans++;
				break;
			}
			
			if(q.isEmpty()) {
				setRear();
				
				int ny = R+dy[d];
				int nx = C+dx[d];
				
				if(map[ny][nx]==1) return;
				
				q.add(new Node(ny, nx));
				
				setRear();
			}
		}
	}
		
	static void setRear() {
		d+=2;
		d%=4;
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}


