import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, R;
	static int MOD = 1234567891;
	static long ans, facto[];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = Integer.parseInt(sc.readLine());
		for(int t=1; t<=test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			facto = new long[N + 1];
			facto[1] = 1;
			for (int i=2; i<=N; i++) {
				facto[i] = (facto[i-1]*i)%MOD;
			}
			
			ans=0;
			solve();
			sb.append("#"+t+" "+ans+"\n");		
		}
		System.out.println(sb);
	}
	
	static void solve() {
		long up = facto[N];
		long bottom = (facto[R]*facto[N-R])%MOD;
		bottom = pow(bottom, MOD-2);
		
		ans = (up*bottom)%MOD;
	}
	
	static long pow(long a, int b) {
		if(b==0) return 1;
		else if(b==1) return a;
		else if(b%2==0) {
			long tmp = pow(a, b/2);
			return (tmp*tmp)%MOD;
		}
		
		long tmp = (a*pow(a,b-1))%MOD;
		return tmp;
		
	}
}
