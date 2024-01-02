import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int p[], map[][];
	
	static boolean visited[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());
		p = new int[N];
		
		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			int size = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<size; j++) {
				int end = Integer.parseInt(st.nextToken())-1;
				map[i][end]=1;
				map[end][i]=1;
			}
		}
		
		ans = Integer.MAX_VALUE;
		visited = new boolean[N];
		
		solve(0);
		if(ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}

	static void solve(int depth) {
		if(depth==N) {
			List<Integer> listA = new ArrayList<>();
			List<Integer> listB = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				if(visited[i]) listA.add(i);
				else listB.add(i);
			}
			
			if(listA.size()==0 || listB.size()==0) return;
			
			if(!check(listA) || !check(listB)) return;
			
			int sumA=0, sumB=0;
			
			for(int i : listA) {
				sumA += p[i];
			}
			
			for(int i : listB) {
				sumB += p[i];
			}
			
			int diff = Math.abs(sumA-sumB);
			ans = Math.min(ans, diff);
			
			return;
		}
		
		visited[depth]=true;
		solve(depth+1);
		
		visited[depth]=false;
		solve(depth+1);
	}
	
	static boolean check(List<Integer> list) {
		if(list.size()==1) return true;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(list.get(0));
		
		boolean visited2[] = new boolean[N];
		visited2[list.get(0)]=true;
		
		int cnt=1;
		while(!q.isEmpty()) {
			int start = q.poll();
			
			if(cnt==list.size()) return true;
			
			for(int next : list) {
				if(!visited2[next] && map[start][next]==1) {
					cnt++;
					q.add(next);
					visited2[next]=true;
				}
			}
		}
		
		return false;
	}
}