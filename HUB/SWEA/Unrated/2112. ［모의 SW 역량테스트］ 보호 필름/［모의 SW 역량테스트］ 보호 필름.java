import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int D, W, K, ans;
	static int set[], map[][];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			set = new int[D];
			Arrays.fill(set, -1);
			
			map = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(sc.readLine());
				
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans=Integer.MAX_VALUE;
			solve(0, 0, 0);
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int y, int x, int k) {
		if(ans==0 || ans<k) return;
		
		if(y==D) {
			if(check()) ans = Math.min(ans, k);
			return;
		}
		
		solve(y+1, x, k);
		
		set[y]=0;
		solve(y+1, x, k+1);
		
		set[y]=1;
		solve(y+1, x, k+1);
		
		set[y]=-1;
	}

	static boolean check() {
		// 열
		for(int i=0; i<W; i++) {
			int value = map[0][i];
			int cnt=1;
			
			// 행
			for(int j=1; j<D; j++) {
				int tmp = map[j][i];
				
				if(set[j-1]!=-1) value = set[j-1];
				if(set[j]!=-1) tmp = set[j];
				
				if(value==tmp) cnt++;
				else {
					value = map[j][i];
					cnt=1;
				}
				if(cnt==K) break;
			}
			if(cnt!=K) return false;
		}
		
		return true;
	}
}