import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	
	static boolean visited[];
	
	static List<Integer> map[];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = Integer.parseInt(sc.readLine());
		for(int t=1; t<=test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(sc.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				map[y].add(x);
				map[x].add(y);
			}
			
			visited = new boolean[N+1];
			
			int cnt=0;
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				
				solve(i);
				cnt++;
			}
			
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb);
	}
	
	static void solve(int start) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(start);
		
		while(!q.isEmpty()) {
			start = q.poll();
			
			if(visited[start]) continue;
			visited[start]=true;
			
			for(int next : map[start]) {
				if(visited[next]) continue;
				q.add(next);
			}
		}
	}
}