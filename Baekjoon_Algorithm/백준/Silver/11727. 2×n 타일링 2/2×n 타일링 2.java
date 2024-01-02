import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, ans;
	static int cache[];

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(sc.readLine());
		
		cache = new int[N+1];
		
		System.out.println(tiling(N)%10007);
	}
	
	static int tiling(int width) {
		if(cache[width]!=0) return cache[width];
		
		if(width<=1) return 1;
		
		return cache[width] = (tiling(width-1) + tiling(width-2)*2)%10007;
	}
}