import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, ans;
	static int map[][];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= test_case; T++) {

			N = Integer.parseInt(sc.readLine());

			ans=0;
			
			if(N==1) ans=Integer.parseInt(sc.readLine());
			else {
				map = new int[N][N];
				
				for(int i=0; i<N; i++) {
					String str = sc.readLine();
					
					for(int j=0; j<N; j++) {
						map[i][j] = str.charAt(j)-'0';
					}
				}
				
				solve(N/2, N/2, N/2);
			}

			sb.append("#" + T + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int y, int x, int d) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int dist = Math.abs(i-y)+Math.abs(j-x);
				
				if(dist<=d) {
					ans+=map[i][j];
				}
			}
		}
	}
}