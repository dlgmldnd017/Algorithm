import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N=9, ans;
	static int map[][];

	static boolean isFind;
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String str = sc.readLine();
			
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		solve(0, 0);
	}

	// 스도쿠를 해결하는 함수 solve()
	// 역할: 모든 2차원 배열을 돌며 값을 채웠을 때 중복되는 지 매번 확인.
	static void solve(int y, int x) {
		
		// 만약 이동할 수 있는 열이 없을 경우 y+1.
		if(x==N) {
			solve(y+1, 0);
			return;
		}
		
		// 2차원 배열 모두 채웠다면 출력후 재귀를 exit()로 탈출.
		if(y==N) {
			printAns();
			System.exit(0);
		}
		
		// 만약 채워야 하는 곳이 생긴다면.
		if(map[y][x]==0) {
			
			// 1~9까지 값을 넣는다.
			for(int k=1; k<=N; k++) {
				
				// 단, (1)행 (2)열 (3)3x3에서 중복되는 지 확인.
				if(isDuplicate(y, x, k)) continue;
				
				// 중복이 되지 않으면 값을 넣고 다음 열 이동.
				map[y][x]=k;
				solve(y, x+1);
			}
			
			// 1~9까지 되돌아 온다면 답이 될 수 없는 경우이므로
			// 다시 빈칸(0)을 채우고 이전 재귀 호출로 이동.
			map[y][x]=0;
			return;
		}
		
		// 빈칸이 아니라면 다음 열 이동.
		solve(y, x+1);
	}

	// (1)행 (2)열 (3)3x3에서 중복되는 지 확인하는 함수 isDuplicate()
	private static boolean isDuplicate(int y, int x, int k) {
		
		// 같은 행에서 중복이 있는지 확인.
		for(int i=0; i<N; i++) {
			if(map[y][i]==k) return true;
		}
		
		// 열에서 중복되는 곳이 있는지 확인.
		for(int i=0; i<N; i++) {
			if(map[i][x]==k) return true;
		}
		
		// 3x3 크기에서 중복된 곳이 있는지 확인.
		int row = (y/3)*3;
		int col = (x/3)*3;
		
		for(int i=row; i<row+3; i++) {
			for(int j=col; j<col+3; j++) {
				if(map[i][j]==k) return true;
			}
		}
		
		// 위 조건을 통과한다면 중복된 곳이 없음.
		return false;
	}

	// 정답을 출력하는 함수 printAns()
	private static void printAns() {
		for(int i[]:map) {
			for(int j:i) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
}