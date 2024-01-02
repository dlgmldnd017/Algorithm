import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Person{
	int y, x, cnt;

	public Person(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class Main {
	static int N, M, ans;
	static int map[][];

	static int dy[] = {0, 1, 0, -1};
	static int dx[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = sc.readLine();
			
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		if(N==1 && M==1){
			System.out.println(1);
			return;
		}
		
		solve();
		System.out.println(ans);
	}

	static void solve() {
		Queue<Person> q = new LinkedList<Person>();
		q.add(new Person(0, 0, 0));
		
		int visited[][][] = new int[2][N][M];
		visited[0][0][0]=1;
		
		while(true) {
			Person cur = q.poll();
			
			for(int k=0; k<4; k++) {
				int ny = cur.y + dy[k];
				int nx = cur.x + dx[k];
				
				if(!inRange(ny, nx)) continue;
				
				if(map[ny][nx]==0) {
					if(visited[cur.cnt][ny][nx]==0) {
						q.add(new Person(ny, nx, cur.cnt));
						visited[cur.cnt][ny][nx] = visited[cur.cnt][cur.y][cur.x]+1;
						
						if(ny==N-1 && nx==M-1) {
							ans = visited[cur.cnt][ny][nx];
							return;
						}
					}
				}else {
					if(cur.cnt==0) {
						if(visited[1][ny][nx]==0) {
							q.add(new Person(ny, nx, 1));
							visited[1][ny][nx] = visited[0][cur.y][cur.x]+1;
							
							if(ny==N-1 && nx==M-1) {
								ans = visited[cur.cnt][ny][nx];
								return;
							}
						}
					}
				}
			}
			
			if(q.isEmpty()) {
				ans=-1;
				return;
			}
		}
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}