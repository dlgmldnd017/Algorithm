import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int E, ans;
	
	public Node(int E, int ans) {
		this.E=E;
		this.ans=ans;
	}
}

public class Main {
	static int N, K[], ans;
	static int[][] map;
	
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		
		st = new StringTokenizer(sc.readLine());
		K = new int[2];
		K[0] = Integer.parseInt(st.nextToken());
		K[1] = Integer.parseInt(st.nextToken());
		
		int to = Integer.parseInt(sc.readLine());
		map = new int[N][N];
		for(int i=0; i<to; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			
			map[y][x]=1;
			map[x][y]=1;
		}
		
		solve();
		if(ans!=0) System.out.println(ans);
		else System.out.println(-1);
	}
	
	static void solve() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(K[0]-1, 0));
		visited = new boolean[N];
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			visited[cur.E] = true;
			
			if(cur.E == K[1]-1) {
				ans = cur.ans;
				return;
			}
			
			for(int i=0; i<N; i++) {
				if(!visited[i]&&map[cur.E][i]==1) {
					q.add(new Node(i, cur.ans+1));
				}
			}
		}
	}
}