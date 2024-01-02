import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x, cnt, curIsland;
	boolean isConnect;
	
	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public Node(int y, int x, int cnt, int curIsland) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.curIsland = curIsland;
	}
}

public class Main {
	static int N, ans;
	static int map[][];
	
	static int dy[] = {1, -1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	
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
		
		boolean visited[][] = new boolean[N][N];
		int cnt=1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0 || visited[i][j]) continue;
				
				checkCluster(i, j, cnt, visited);
				cnt++;
			}
		}
		
		ans = Integer.MAX_VALUE;
		L1:for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0) solve(i, j);
				
				if(ans==1) break L1;
			}
		}
		
		System.out.println(ans);
	}
	
	// 섬 확인을 위해 클러스터링하는 메소드.
	static void checkCluster(int y, int x, int flag, boolean visited[][]) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(y, x));
		
		visited[y][x]=true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			map[cur.y][cur.x] = flag;
			
			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx) || visited[ny][nx] || map[ny][nx]==0) continue;
				
				q.add(new Node(ny, nx));
				visited[ny][nx]=true;
			}
		}
	}
	
	// 다리 만들기를 해결하기 위한 메소드.
	static void solve(int y, int x) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(y, x, 0, map[y][x]));
		
		boolean visited[][] = new boolean[N][N];
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.cnt>ans) return;
			
			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx) || visited[ny][nx]) continue;
				
				// 현재 섬을 떠나 바다 위에서 다리를 만들고 있다면
				if(cur.isConnect) {
					
					// 다음 위치한 곳이 바다일 경우
					if(map[ny][nx]==0) {
						Node n = new Node(ny, nx, cur.cnt+1, cur.curIsland);
						n.isConnect=true;
						q.add(n);
						visited[ny][nx]=true;
					}
					
					// 다음 위치한 곳이 섬일 경우
					else if(cur.curIsland!=map[ny][nx]) {
						if(ans>cur.cnt) {
							ans = cur.cnt;
						}
						return;
					}
				}
				// 처음으로 바다에 다리를 만들고 있다면
				else {
					// 그러나 바로 육지일 경우
					if(cur.curIsland==map[ny][nx]) continue;
					else {
						Node n = new Node(ny, nx, cur.cnt+1, cur.curIsland);
						n.isConnect=true;
						q.add(n);
						visited[ny][nx]=true;
					}
				}
			}
		}
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}