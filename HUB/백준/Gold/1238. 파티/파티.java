import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int end, weight;

	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return weight-o.weight;
	}
}

public class Main {
	static int N, M, X, ans;
	
	static ArrayList<Node> map1[];
	static ArrayList<Node> map2[];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;

		map1 = new ArrayList[N];
		map2 = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			map1[i] = new ArrayList<Node>();
			map2[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			
			map1[y].add(new Node(x, w));
			map2[x].add(new Node(y, w));
		}
		
		int dist1[] = solve(map1);
		int dist2[] = solve(map2);
		
		for(int i=0; i<N; i++) {
			ans = Math.max(ans, dist1[i]+dist2[i]);
		}
		
		System.out.println(ans);
	}

	static int[] solve(ArrayList<Node> map[]) {
		int dist[] = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X]=0;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(X, 0));
		
		boolean visited[] = new boolean[N];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int start = cur.end;
			
			if(visited[start]) continue;
			visited[start]=true;
			
			for(Node next : map[start]) {
				if(!visited[next.end] && dist[next.end] > dist[start] + next.weight) {
					dist[next.end] = dist[start] + next.weight;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
		
		return dist;
	}
}