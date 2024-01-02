import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int end, color;
	
	public Node(int end, int color) {
		this.end=end;
		this.color=color;
	}
}

public class Main {
	static int V, E, ans;
	static int[] c;
	static ArrayList<Node>[] arr;
	static boolean[] visited;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = Integer.parseInt(sc.readLine());
		for(int T=1; T<=test_case; T++) {
			
			st = new StringTokenizer(sc.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			arr = new ArrayList[V+1];
			for(int i=1; i<=V; i++) {
				arr[i] = new ArrayList<>();
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(sc.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				arr[y].add(new Node(x, -1));
				arr[x].add(new Node(y, -1));
			}
			
			ans=0;
			c = new int[V+1];
			visited = new boolean[V+1];
			
			for(int i=1; i<=V; i++) {
				if(!visited[i]) solve(i);
				
				for(Node n:arr[i]) {
					if(c[i]==c[n.end]) {
						ans=1;
						break;
					}
				}
			}
			
			if(ans==1) sb.append("NO\n");
			else sb.append("YES\n");
		}
		System.out.println(sb);
	}
	
	static void solve(int start) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, 1));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			start = cur.end;
			c[start] = cur.color;
			
			int color=-1;
			if(visited[start]) continue;
			visited[start]=true;
			
			if(cur.color==1) color=0;
			else color=1;
			
			for(Node n : arr[start]) {
				if(!visited[n.end]) {
					q.add(new Node(n.end, color));
				}
			}
		}
	}
}