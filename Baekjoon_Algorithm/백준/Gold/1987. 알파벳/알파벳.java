import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, ans;
	static int tmp[];
	static char map[][];
	
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String str = sc.readLine();
			
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		tmp = new int[26];
		ans = Integer.MIN_VALUE;
		
		solve(0, 0, 0);
		if(ans==Integer.MIN_VALUE) ans=1;
		System.out.println(ans);
	}

	static void solve(int y, int x, int cnt) {
		if(tmp[map[y][x]-'A']>=1) {
			if(ans<cnt) ans=cnt;
			return;
		}
		
		tmp[map[y][x]-'A']++;
		for(int k=0; k<4; k++) {
			int ny = y+dy[k];
			int nx = x+dx[k];
			
			if(!inRange(ny, nx)) continue;
			
			solve(ny, nx, cnt+1);
		}
		tmp[map[y][x]-'A']--;
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<R) && (x>=0&&x<C);
	}
}
