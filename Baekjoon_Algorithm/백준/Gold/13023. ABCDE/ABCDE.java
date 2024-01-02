import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans;
	
	static ArrayList<Integer> list[];
	
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		visited = new boolean[N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			list[y].add(x);
			list[x].add(y);
		}
		
		for(int i=0; i<N; i++) {
			if(ans==0) solve(i, 1);
		}
		
		System.out.println(ans);
	}

	static void solve(int idx, int depth) {
		if(depth==5) {
			ans=1;
			return;
		}
		
		visited[idx]=true;
		for(int i:list[idx]) {
			if(!visited[i]) solve(i, depth+1);
		}
		visited[idx]=false;
	}
	
	static boolean checkFriend() {
		for(int i=0; i<N; i++) {
			if(!visited[i]) return false;
		}
		return true;
	}
}