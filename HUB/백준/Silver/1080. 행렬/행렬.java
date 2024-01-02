import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, M, ans;
	static int[][] A, B;

	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N][M];
		B = new int[N][M];
		
		for(int i=0; i<N;i++) {
			String tmp = sc.readLine();
			
			for(int j=0; j<M; j++) {
				A[i][j] = tmp.charAt(j)-'0';
			}
		}
		
		for(int i=0; i<N;i++) {
			String tmp = sc.readLine();
			
			for(int j=0; j<M; j++) {
				B[i][j] = tmp.charAt(j)-'0';
			}
		}
		
		solve();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(A[i][j]!=B[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(ans);
	}
	
	static void solve() {
		for(int y=0; y<=N-3; y++) {
			for(int x=0; x<=M-3; x++) {
				if(A[y][x]!=B[y][x]) {
					changeMatrix(y, x);
					ans++;
				}
			}
		}
	}
	
	static void changeMatrix(int y, int x) {
		for(int i=y; i<y+3; i++) {
			for(int j=x; j<x+3; j++) {
				A[i][j] = A[i][j]==0? 1:0;
			}
		}
	}
}