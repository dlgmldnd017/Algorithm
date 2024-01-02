import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, startY, startX, arriveY, arriveX, ans;
	static int map[][];
	
	static int dy[] = {0, 0, -1, 1};
	static int dx[] = {-1, 1, 0, 0};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			N = Integer.parseInt(sc.readLine());
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(sc.readLine());
				
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(sc.readLine());
			startY = Integer.parseInt(st.nextToken());
			startX = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(sc.readLine());
			arriveY = Integer.parseInt(st.nextToken());
			arriveX = Integer.parseInt(st.nextToken());
			
			ans=0;
			solve();
			if(ans==0) ans=-1;
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(startY, startX, 0));
		
		boolean visited[][] = new boolean[N][N];
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx) || visited[ny][nx] || map[ny][nx]==1) continue;
				
				if(ny==arriveY && nx==arriveX) {
					ans = cur.t+1;
					return;
				}
				
				// 소용돌이라면
				if(map[ny][nx]==2) {
					
					// 시간상 소용돌이가 없어졌다면
					if(cur.t%3==2) {
						visited[ny][nx]=true;
						q.add(new Node(ny, nx, cur.t+1));
					}
					// 아직 소용돌이가 존재한다면
					else {
						q.add(new Node(cur.y, cur.x, cur.t+1));
					}
				}else {
					visited[ny][nx]=true;
					q.add(new Node(ny, nx, cur.t+1));
				}
			}
		}
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}

class Node{
	int y, x, t;

	public Node(int y, int x, int t) {
		this.y = y;
		this.x = x;
		this.t = t;
	}
}