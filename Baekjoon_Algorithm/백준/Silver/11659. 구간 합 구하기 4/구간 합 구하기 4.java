import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N, T, ans;
	static int map[], sumAcc[];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N];
		sumAcc = new int[N];
		
		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		sumAcc[0] = map[0];
		for(int i=1; i<N; i++) {
			sumAcc[i] = sumAcc[i-1]+map[i];
		}
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			ans = solve(y-1, x-1);
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
	
	static int solve(int y, int x) {
		if(y==0) return sumAcc[x];
		else if(y==x) return map[y];
		
		return sumAcc[x]-sumAcc[y-1];
	}
}