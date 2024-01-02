import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Mineral{
	int y, x;

	public Mineral(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static int R, C, N;
	static int A[];

	static char map[][];

	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	static StringBuilder sb = new StringBuilder();

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


		N = Integer.parseInt(sc.readLine());
		A = new int[N];

		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken())-1;
		}

		solve();
		printAns();
	}

	static void solve() {
		int t=1;

		for(int i=0; i<N; i++) {
			// 양수: 왼쪽 -> 오른쪽
			// 음수: 오른쪽 -> 왼쪽
			if(t>0) {
				// -> 방향으로 던지기
				throwL(i);
				checkCluster(i);
			}
			else {
				// <- 방향으로 던지기
				throwR(i);
				checkCluster(i);
			}
			t*=-1;
		}
	}

	static void checkCluster(int idx) {
		Queue<Mineral> q = new ArrayDeque<>();
		int visited[][] = new int[R][C];

		// 바닥에 붙어 있는 미네랄들 먼저 방문 체크를 1로 설정
		for(int i=0; i<C; i++) {
			if(visited[R-1][i]==0 && map[R-1][i]=='x') {
				visited[R-1][i]=1;
				q.add(new Mineral(R-1, i));
			}

			while(!q.isEmpty()) {
				Mineral cur = q.poll();

				for(int k=0; k<4; k++) {
					int ny = cur.y+dy[k];
					int nx = cur.x+dx[k];

					if(!inRange(ny, nx) || visited[ny][nx]==1) continue;

					if(map[ny][nx]=='x') {
						visited[ny][nx]=1;
						q.add(new Mineral(ny, nx));
					}
				}
			}
		}

		// 맨 아래 왼쪽부터
		for(int i=R-2; i>=0; i--) {
			for(int j=0; j<C; j++) {
				if(visited[i][j]==0 && map[i][j]=='x') {
					visited[i][j]=2;
					q.add(new Mineral(i, j));

					while(!q.isEmpty()) {
						Mineral cur = q.poll();

						for(int k=0; k<4; k++) {
							int ny = cur.y+dy[k];
							int nx = cur.x+dx[k];

							if(!inRange(ny, nx) || visited[ny][nx]==1 || visited[ny][nx]==2) continue;

							if(map[ny][nx]=='x') {
								visited[ny][nx]=2;
								q.add(new Mineral(ny, nx));
							}
						}
					}
					
					while(true) {
						boolean check=false;
						for(int x=0; x<C; x++) {
							for(int y=R-2; y>=0; y--) {
								if(visited[y][x]==2) {
									visited[y][x]=0;
									visited[y+1][x]=2;
									map[y][x]='.';
									map[y+1][x]='x';
									
									if(y+1==R-1 || visited[y+2][x]==1) check=true;
								}
							}
						}
						if(check) return;
					}
				}
			}
		}
	}

	static void throwL(int i) {
		int from = R-A[i]-1;

		for(int x=0; x<C; x++) {
			if(map[from][x]=='x') {
				map[from][x]='.';
				break;
			}
		}
	}

	static void throwR(int i) {
		int from = R-A[i]-1;

		for(int x=C-1; x>=0; x--) {
			if(map[from][x]=='x') {
				map[from][x]='.';
				break;
			}
		}
	}

	static void printAns() {
		for(char i[]:map) {
			for(char j:i) {
				System.out.print(j);
			}
			System.out.println();
		}
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<R)&&(x>=0&&x<C);
	}
}