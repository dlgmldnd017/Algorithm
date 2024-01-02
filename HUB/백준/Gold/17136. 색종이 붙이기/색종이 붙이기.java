import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N=10, ans=Integer.MAX_VALUE;
	static int tmp[] = {5, 5, 5, 5, 5};
	static int map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0, 0);
		if(ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}

	static void solve(int y, int x, int cnt) {
		if(y>=10) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(ans<=cnt) return;
		
		if(x>=10) {
			solve(y+1, 0, cnt);
			return;
		}
		
		if(map[y][x]==1) {
			for(int i=4; i>=0; i--) {
				if(tmp[i]>0 && canAttatch(y, x, i+1)) {
					tmp[i]--;
					attatch(y, x, i+1, 0);
					
					solve(y, x+1, cnt+1);
					
					attatch(y, x, i+1, 1);
					tmp[i]++;
				}
			}
		}else solve(y, x+1, cnt);
	}

	static void attatch(int y, int x, int size, int flag) {
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				map[i][j]=flag;
			}
		}
	}

	static boolean canAttatch(int y, int x, int size) {
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				if(!inRange(i, j)) return false;
				if(map[i][j]==0) return false;
			}
		}
		return true;
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}