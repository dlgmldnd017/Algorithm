import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, ans;
	static int map[][];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			N = Integer.parseInt(sc.readLine());
			M = Integer.parseInt(sc.readLine());
			
			map = new int[N][N];

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(sc.readLine());
				int y = Integer.parseInt(st.nextToken())-1;
				int x = Integer.parseInt(st.nextToken())-1;
				
				map[y][x] = 1;
			}
			
			ans=0;
			for(int i=0; i<N; i++) {
				if((gtSolve(i)+ltSolve(i))==N-1) ans++;
			}
			
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static int gtSolve(int start) {
		int cnt=0;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		
		boolean visited[] = new boolean[N];
		visited[start]=true;
		
		// 먼저, 키 큰 친구를 찾는다.
		while(!q.isEmpty()) {
			start = q.poll();
			
			for(int i=0; i<N; i++) {
				if(!visited[i] && map[start][i]==1) {
					cnt++;
					visited[i]=true;
					q.add(i);
				}
			}
		}
		
		return cnt;
	}
	
	static int ltSolve(int start) {
		int cnt=0;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		
		boolean visited[] = new boolean[N];
		visited[start]=true;
		
		// 먼저, 키 큰 친구를 찾는다.
		while(!q.isEmpty()) {
			start = q.poll();
			
			for(int i=0; i<N; i++) {
				if(!visited[i] && map[i][start]==1) {
					cnt++;
					visited[i]=true;
					q.add(i);
				}
			}
		}
		
		return cnt;
	}
}