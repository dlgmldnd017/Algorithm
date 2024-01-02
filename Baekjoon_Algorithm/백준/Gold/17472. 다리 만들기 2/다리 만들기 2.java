import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
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

	@Override
	public int compareTo(Node o) {
		return x-o.x;
	}
}

class MST implements Comparable<MST>{
	int start, end, d;

	public MST(int start, int end, int d) {
		super();
		this.start = start;
		this.end = end;
		this.d = d;
	}



	@Override
	public int compareTo(MST o) {
		return d-o.d;
	}
}

public class Main {
	static int N, M, ans;
	static int map[][];
	
	static List<Node> list[];
	static PriorityQueue<MST> pq;
	
	static int dy[] = {1, -1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	
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
		
		list = new ArrayList[7];
		boolean visited[][] = new boolean[N][M];
		int cnt=1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0 || visited[i][j]) continue;
				
				list[cnt] = new ArrayList<Node>();
				checkCluster(i, j, cnt, visited);
				cnt++;
			}
		}
		
		pq = new PriorityQueue<>();
        for(int i=1; i<cnt; i++) {
        	for(int j=0; j<list[i].size(); j++) {
        		Node n = list[i].get(j);
        		
        		for(int k=0; k<4; k++) {
        			solve(n.y, n.x, i, k, -1);
        		}
        	}
        }
		
        mst(cnt);
        System.out.println(ans);
	}
	
	// 섬 확인을 위해 클러스터링하는 메소드.
	static void checkCluster(int y, int x, int flag, boolean visited[][]) {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(y, x));
		
		visited[y][x]=true;
		
		list[flag].add(new Node(y, x));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			map[cur.y][cur.x] = flag;
			
			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx) || visited[ny][nx] || map[ny][nx]==0) continue;
				
				q.add(new Node(ny, nx));
				visited[ny][nx]=true;
				
				list[flag].add(new Node(ny, nx));
			}
		}
	}
	
	// 다리 만들기2를 해결하기 위한 메소드.
	static void solve(int y, int x, int curIsland, int dir, int length) {
		if(map[y][x]!=0 && map[y][x]!=curIsland) {
			if(length>=2) pq.add(new MST(curIsland, map[y][x], length));
			return;
		}
		
		int ny = y+dy[dir];
		int nx = x+dx[dir];
		if(!inRange(ny, nx) || map[ny][nx]==curIsland) return;
		solve(ny, nx, curIsland, dir, length+1);
	}
	
	static void mst(int length) {
		// 전처리 작업(1)
		List<Node> map[] = new ArrayList[length];
		for(int i=1; i<length; i++) {
			map[i] = new ArrayList<Node>();
		}
		// 전처리 작업(2)
		while(!pq.isEmpty()) {
			MST cur = pq.poll();
			map[cur.start].add(new Node(cur.end, cur.d));
		}
		
		// 최소 신장 트리
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(1, 0));
		
		int dist[] = new int[length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1]=0;
		
		boolean visited[] = new boolean[length];
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(visited[cur.y]) continue;
			visited[cur.y]=true;
			
			for(Node n : map[cur.y]) {
				if(visited[n.y] || dist[n.y]<n.x) continue;
				
				dist[n.y] = n.x;
				q.add(new Node(n.y, dist[n.y]));
			}
		}
		
		for(int i=2; i<length; i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				ans = -1;
				return;
			}
			ans += dist[i];
		}
	}
	
	// 범위체크 메소드.

	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}