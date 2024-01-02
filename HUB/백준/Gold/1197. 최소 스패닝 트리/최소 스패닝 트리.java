import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Graph implements Comparable<Graph>{
	int idx, d;
	
	public Graph(int idx, int d) {
		super();
		this.idx = idx;
		this.d = d;
	}

	@Override
	public int compareTo(Graph o) {
		return d-o.d;
	}
}

public class Main {
	static int N, M, ans;
	static int dist[];
	
	static List<Graph> list[];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N];
		list = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<Graph>();
		}
		dist[0]=0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			
			list[y].add(new Graph(x, d));
			list[x].add(new Graph(y, d));
		}

		solve();
		for(int i=1; i<N; i++) {
			ans += dist[i];
		}
		System.out.println(ans);
	}

	static void solve() {
		PriorityQueue<Graph> pq = new PriorityQueue<Graph>();
		pq.add(new Graph(0, 0));
		
		boolean visited[] = new boolean[N];
		
		while(!pq.isEmpty()) {
			Graph cur = pq.poll();
			
			if(visited[cur.idx]) continue;
			visited[cur.idx]=true;
			
			for(Graph g : list[cur.idx]) {
				if(visited[g.idx] || dist[g.idx]<g.d) continue;
				
				dist[g.idx] = g.d;
				pq.add(new Graph(g.idx, dist[g.idx]));
			}
		}
	}
}