import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution{
	static int N, ans;
	static int map[];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = 10;
		for(int T=1; T<=test_case; T++) {
			
			N = Integer.parseInt(sc.readLine());
			
			map = new int[100];
			
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<100; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			
			ans=0;
			solve();
			sb.append("#"+T+" "+ans+"\n");
		}
		
		System.out.println(sb);
	}
	
	static void solve() {
		for(int i=0; i<N; i++) {
			Arrays.sort(map);
			map[0]++;
			map[99]--;
			
			if(map[0]>map[1]) {
				int tmp=map[0];
				map[0]=map[1];
				map[1]=tmp;
			}
			
			if(map[98]>map[99]) {
				int tmp=map[98];
				map[98]=map[99];
				map[99]=tmp;
			}
			
			ans = Math.abs(map[0]-map[99]);
			if(ans==1) return;
		}
	}
}