import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int end, w;

	public Node(int end, int w) {
		this.end = end;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return w-o.w;
	}
}

public class Main {
	static int V, E, K;
	static int dist[];
	static ArrayList<Node> list[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(sc.readLine());
		
		dist = new int[V];
		list = new ArrayList[V];
		for(int i=0; i<V; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			
			list[y].add(new Node(x, w));
		}

		K-=1;
		solve();
		for(int i:dist) {
			if(i==Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(i);
		}
	}

	static void solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(K, 0));
		
		boolean visited[] = new boolean[V];
		dist[K] = 0;
		
		while(!pq.isEmpty()) {
			Node start = pq.poll();
			
			if(visited[start.end]) continue;
			visited[start.end]=true;
			
			for(Node next : list[start.end]) {
				if(dist[next.end]>dist[start.end]+next.w) {
					dist[next.end]=dist[start.end]+next.w;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
	}
}
