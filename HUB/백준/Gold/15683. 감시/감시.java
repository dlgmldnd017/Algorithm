import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV{
	int y, x, num;

	public CCTV(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
	}
}

public class Main {
	static int N, M, ans=Integer.MAX_VALUE;
	static int[][] map;
	
	static ArrayList<CCTV> list = new ArrayList<CCTV>();
	static boolean[][] visited;
	static int blind, cnt;
	
	static int dir[][][] = {
			{},
            { { 0 }, { 1 }, { 2 }, { 3 } },
            { { 0, 1 }, { 2, 3 } },
            { { 0, 2 }, { 2, 1 }, { 3, 1 }, { 0, 3 } },
            { { 1, 2, 3 }, { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 } },
            { { 0, 1, 2, 3 } }
	};
	
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]!=0 && map[i][j]!=6) list.add(new CCTV(i, j, map[i][j]));
				else if(map[i][j]==0) blind++;
			}
		}
		
		solve(0, 0);
		System.out.println(ans);
	}

	static void solve(int idx, int depth) {
		if(idx==list.size()) {
			ans = Math.min(ans, blind-depth);
			return;
		}
		
		int y =  list.get(idx).y;
		int x =  list.get(idx).x;
		int num = list.get(idx).num;
		
		for(int i=0; i<dir[num].length; i++) {
			int watched = simul(dir[num][i], -1, y, x);
			solve(idx+1, depth+watched);
			simul(dir[num][i], 1, y, x);
		}
	}
	
	static int simul(int dirs[], int flag, int y, int x) {
		int cnt=0;
		
		for(int i=0; i<dirs.length; i++) {
			for(int s=1;;s++) {
				int ny = y+dy[dirs[i]]*s;
				int nx = x+dx[dirs[i]]*s;
				
				if(inRange(ny, nx) && map[ny][nx]!=6) {
					if(map[ny][nx]>0) continue;
					else if(map[ny][nx]==0) cnt+=1;
					
					map[ny][nx] += flag;
				}else break;
			}
		}
		return cnt;
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}