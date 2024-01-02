import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pipe{
	int y, x, dir;
	
	public Pipe(int y, int x, int dir) {
		this.y=y;
		this.x=x;
		this.dir=dir;
	}
}

public class Main {
	static int N, ans;
	static int[][] map;
	
	static boolean[][] visited;
	
	static int dy[] = {0, 1, 1};
	static int dx[] = {1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(map[N-1][N-1]==1) {
			System.out.println(0);
			return;
		}
		solve();
		System.out.println(ans);
	}
	
	static void solve() {
		Queue<Pipe> q = new LinkedList<>();
		visited = new boolean[N][N];
		
		q.add(new Pipe(0,1,0));
		visited[0][1]=true;
		
		while(!q.isEmpty()) {
			Pipe cur = q.poll();
			int y = cur.y;
			int x = cur.x;
			int dir=cur.dir;
					
			if((y==N-1)&&(x==N-1)) {
				ans++;
				continue;
			}
			
			int from, to;
			switch(dir) {
			case 0:
				from=0;
				to=1;
				break;
			case 1:
				from=1;
				to=2;
				break;
			default:
				from=0;
				to=2;
			}
			
			for(int k=from; k<to; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];

				if(!inRange(ny, nx)|| map[ny][nx]==1 || ((N-1)==y&&(N-2)>=x)&&dir==1 || ((N-2)>=y&&(N-1)==x)&&dir==0) continue;
				q.add(new Pipe(ny, nx, k));
			}
		
			for(int k=0; k<3; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx)|| map[ny][nx]==1) break;
				
				if(k==2) q.add(new Pipe(ny, nx, 2));
			}
		}
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}