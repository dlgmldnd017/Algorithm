import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int pos, ans;

	public Node(int pos, int ans) {
		super();
		this.pos = pos;
		this.ans = ans;
	}
}

public class Main {
	static int N, K, ans=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		solve();
		System.out.println(ans);
	}

	static void solve() {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[100_001];
		q.add(new Node(N, 0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int pos = cur.pos;
			
			if(pos == K) {
				ans = cur.ans;
				return;
			}
			
			if(visited[pos]) continue;
			visited[pos]=true;
			
			if(pos-1>=0) q.add(new Node(pos-1, cur.ans+1));
			if(pos+1<=100000) q.add(new Node(pos+1, cur.ans+1));
			if(pos*2<=100000) q.add(new Node(pos*2, cur.ans+1));
		}
	}
}