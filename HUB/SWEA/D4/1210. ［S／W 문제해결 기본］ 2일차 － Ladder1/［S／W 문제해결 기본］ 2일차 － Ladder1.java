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

public class Solution {
	static int N=100, ans;
	static int map[][];
	
	static int dy[] = {0, 0, -1};
	static int dx[] = {-1, 1, 0};
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = 10;
		for (int T = 1; T <= test_case; T++) {
			String str = sc.readLine();

			map = new int[N][N];
			
			int R=0, C=0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(sc.readLine());
				
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j]==2) {
						R=i; C=j;
					}
				}
			}

			solve(R, C);
			sb.append("#" + T + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x));
		
		boolean visited[][] = new boolean[N][N];
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			y = cur.y;
			x = cur.x;
			
			if(y==0) {
				ans = x;
				return;
			}
			
			for(int k=0; k<3; k++) {
				int ny = y+dy[k];
				int nx = x+dx[k];
				
				if(!inRange(ny, nx) || map[ny][nx]==0) continue;
				
				if(!visited[ny][nx]) {
					q.add(new Node(ny,nx));
					visited[ny][nx]=true;
					break;
				}
			}
		}
	}
	
	static boolean inRange(int y, int x) {
		return (y<N&&y>=0) && (x<N&&x>=0);
	}
}