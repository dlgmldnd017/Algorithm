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
	static int N, M, ans;
	static int map[][];
	
	static Queue<Node> virus = new LinkedList<Node>();
	
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		int safeCnt=0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) safeCnt++;
				else if(map[i][j]==2) virus.add(new Node(i, j));
			}
		}
		
		ans = Integer.MIN_VALUE;
		solve(0, 0, safeCnt, 3);
		System.out.println(ans);
	}

	static void solve(int y, int x, int safeCnt, int wallCnt) {
		if(ans>safeCnt) return;
		
		if(x==M) {
			solve(y+1, 0, safeCnt, wallCnt);
			return;
		}
		
		if(wallCnt==0) {
			int virusCnt = spreadVirus(map);
			ans = Math.max(ans, (safeCnt-3-virusCnt));
			return;
		}
		
		if(y==N) return;
		
		if(map[y][x]==0) {
			map[y][x]=1;
			solve(y, x+1, safeCnt, wallCnt-1);
			map[y][x]=0;
		}
		solve(y, x+1, safeCnt, wallCnt);
	}

	static int spreadVirus(int[][] copy) {
		int virucCnt=0;
		
		boolean visited[][] = new boolean[N][M];
		
		for(Node n : virus) {
			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(n.y, n.x));
			
			while(!q.isEmpty()) {
				Node cur = q.poll();
				
				for(int k=0; k<4; k++) {
					int ny = cur.y+dy[k];
					int nx = cur.x+dx[k];
					
					if(!inRange(ny, nx) || visited[ny][nx] || copy[ny][nx]!=0) continue;
					
					virucCnt++;
					visited[ny][nx]=true;
					q.add(new Node(ny, nx));
				}
			}
		}
		
		return virucCnt;
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}