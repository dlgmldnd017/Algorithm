import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Core{
	int y, x;

	public Core(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Solution {
	static int N, max, ans;
	static int map[][];
	
	static ArrayList<Core> core;

	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= test_case; T++) {

			N = Integer.parseInt(sc.readLine());
			
			map = new int[N][N];
			core = new ArrayList<Core>();

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(sc.readLine());

				for(int j=0; j<N; j++) {
					int tmp = Integer.parseInt(st.nextToken());

					if(tmp==1 && (i!=0 && i!=N-1 && j!=0 && j!=N-1)) core.add(new Core(i, j));
					map[i][j] = tmp;
				}
			}			
			
			max =0;
			ans = Integer.MAX_VALUE;
			solve(0, 0, 0);
			if(ans==Integer.MAX_VALUE) ans=0;
			sb.append("#" + T + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	static void solve(int idx, int lineCnt, int coreCnt) {
		if(idx==core.size()) {
			if(lineCnt!=0) {
				if(coreCnt>max) {
					max = coreCnt;
					ans = lineCnt;
				}else if(coreCnt==max) {
					ans = Math.min(ans, lineCnt);
				}
			}
			return;
		}

		Core cur = core.get(idx);
		int y = cur.y;
		int x = cur.x;
		
		for(int k=0; k<4; k++) {
			if(canFill(y, x, k)) {
				int installCnt = fillLine(y, x, k, 2);
				solve(idx+1, lineCnt+installCnt, coreCnt+1);
				fillLine(y, x, k, 0);
			}
		}
		
		solve(idx+1, lineCnt, coreCnt);
	}
	
	static void print() {
		for(int[] i:map) {
			for(int j:i) {
				System.out.print(j+"  ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static boolean canFill(int y, int x, int k) {
		for(int s=1;; s++) {
			int ny = y+dy[k]*s;
			int nx = x+dx[k]*s;
			
			if(map[ny][nx]==1 || map[ny][nx]==2) return false;
			
			if(ny==0 || ny==N-1 || nx==0 || nx==N-1) return true;
		}
	}
	
	static int fillLine(int y, int x, int k, int flag) {
		int cnt=0;
		
		for(int s=1;; s++) {
			int ny = y+dy[k]*s;
			int nx = x+dx[k]*s;
			
			map[ny][nx]=flag;
			cnt++;
			if(ny==0 || ny==N-1 || nx==0 || nx==N-1) return cnt;
		}
	}
}