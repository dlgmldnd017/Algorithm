import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V, ans;
	static boolean[] visited;
	static boolean[][] map;
	static ArrayList<Integer> arr;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		long cur = System.currentTimeMillis();
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		
		map = new boolean[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			map[y][x]=true;
			map[x][y]=true;			
		}
		
		visited = new boolean[N+1];
		arr = new ArrayList<>();
		visited[V]=true;
		solveDFS(V);
		
		sb.append("\n");
		
		visited = new boolean[N+1];
		solveBFS(V);
		
		System.out.println(sb);
	}
	
	public static void solveDFS(int start) {
		sb.append(start+" ");
		
		for(int next=1; next<N+1; next++) {
			if(!visited[next] && map[start][next]) {
				visited[next]=true;
				arr.add(next);
				solveDFS(next);
			}
		}
	}

	public static void solveBFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start]=true;

		while(!q.isEmpty()) {
			start = q.poll();

			sb.append(start + " ");

			for(int next=1; next<N+1; next++) {
				if(!visited[next] && map[start][next]) {
					visited[next]=true;
					q.add(next);
				}
			}
		}
	}
}