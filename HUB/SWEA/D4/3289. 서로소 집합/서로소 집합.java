import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int parent[];
	static String ans;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int Test_Case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= Test_Case; T++) {
			
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
	        for(int i=1; i<=N; i++) {
	        	parent[i]=i;
	        }
	        
			ans="";
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(sc.readLine());
				int op = Integer.parseInt(st.nextToken());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				if(op==0) {
					union(n1, n2);
					continue;
				}
					
				if(find(n1)==find(n2)) ans+="1";
				else ans+="0";
			}
			sb.append("#" + T + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	static void union(int n1, int n2) {
		int parentN1 = find(n1);
		int parentN2 = find(n2);
		
		if(parentN1==parentN2) return;
		parent[parentN2] = parentN1;
	}
	
	static int find(int n) {
		if(parent[n]==n) return n;
		return parent[n] = find(parent[n]);
	}
}