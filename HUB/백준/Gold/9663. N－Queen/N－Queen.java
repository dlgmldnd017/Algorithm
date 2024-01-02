
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	
	static int[] col;
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		col = new int[N+1];

		solve(1);
		System.out.println(ans);
	}
	
	static void solve(int row) {
		
		// 가지 치기: 직전까지 놓아진 상태로
		if(!isAvailable(row-1)) return;
		
		// 기저 사례
		if(row>N) {
			ans++;
			return;
		}
		
		// 유도 파트
		for(int c=1; c<=N; c++) {	// 0열부터 N-1열까지 시도
			col[row] = c;
			solve(row+1);
		}
	}
	
	static boolean isAvailable(int row) {// row: 마지막으로 놓아진 퀸의 행
		for(int i=1; i<row; i++) {
			if(col[i]==col[row] || row-i == Math.abs(col[row]-col[i])) return false;
		}
		return true;
	}
}