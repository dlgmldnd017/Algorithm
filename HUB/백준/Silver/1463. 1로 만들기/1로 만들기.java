import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int cache[];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());

		cache = new int[N+1];
		cache[0]=0; cache[1]=0;
		solve();
		System.out.println(cache[N]);
	}
	
	static void solve(){
		for(int i=2; i<=N; i++) {
			cache[i] = cache[i-1]+1;
			if(i%2==0) cache[i] = Math.min(cache[i], cache[i/2]+1);
			if(i%3==0) cache[i] = Math.min(cache[i], cache[i/3]+1);
		}
	}
}