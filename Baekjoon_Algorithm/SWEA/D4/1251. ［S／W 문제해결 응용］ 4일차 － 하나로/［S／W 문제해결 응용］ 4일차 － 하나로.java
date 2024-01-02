import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int end;
	double weight;

	public Node(int end, double weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return weight<o.weight? -1:1;
	}
}

public class Solution {
	static int N;
	static double ans;
	static int map[][];

	static double E;

	static PriorityQueue<Node> list = new PriorityQueue<>();

	static boolean visited[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;

		int Test_Case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= Test_Case; T++) {
			N = Integer.parseInt(sc.readLine());

			map = new int[N][N];
			visited = new boolean[N];
			
			st1 = new StringTokenizer(sc.readLine());
			st2 = new StringTokenizer(sc.readLine());

			for(int i=0; i<N; i++) {
				map[i][0] = Integer.parseInt(st1.nextToken());
				map[i][1] = Integer.parseInt(st2.nextToken());
			}

			E = Double.parseDouble(sc.readLine());

			ans=0;
			solve();
			sb.append("#" + T + " " + Math.round(ans) + "\n");
		}

		System.out.println(sb);
	}

	static void solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		
		int cnt=0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.end]) continue;
			visited[cur.end]=true;
			ans += cur.weight;
			cnt++;
			
			if(cnt==N) break;
			
			for(int i=1; i<N; i++) {
				if(!visited[i]) {
					pq.add(new Node(i, E*calcDistance(map[cur.end][0], map[i][0], map[cur.end][1], map[i][1])));
				}
			}
		}
	}

	static double calcDistance(int y1, int y2, int x1, int x2) {
		return Math.abs(Math.pow(y1-y2, 2) + Math.pow(x1-x2, 2));
	}
}