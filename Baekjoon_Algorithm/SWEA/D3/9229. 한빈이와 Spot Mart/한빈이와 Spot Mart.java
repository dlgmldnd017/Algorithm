import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution{
	static int M, W, ans;
	static int[] snack;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(sc.readLine());
		for(int T=1; T<=test_case; T++) {
			
			st = new StringTokenizer(sc.readLine());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			snack = new int[M];
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<M; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[M+1];
			ans=0;
			solve(0, 0);
			if(ans==0) ans=-1;
			sb.append("#"+T+" "+ans+"\n");
		}
		System.out.println(sb);
	}
	
	public static void solve(int depth, int sum) {
		if(depth==2) {
			if(sum<=W) ans = Math.max(ans, sum);
			return;
		}
		
		for(int i=0; i<M; i++) {
			if(!visited[i]) {
				visited[i]=true;
				solve(depth+1, sum+snack[i]);
				visited[i]=false;
			}
		}
	}
}