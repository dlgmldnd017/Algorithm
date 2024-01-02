import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int r[], c[];
	static int map[][], tmp[][];

	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		r = new int[2];
		c = new int[2];
		map = new int[R][C];

		int idx=0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j]==-1) {
					r[idx]=i;
					c[idx]=j;
					idx++;
				}
			}
		}

		solve();
		System.out.println(countDust());
	}

	static int countDust() {
		int sum=0;

		for(int i[]:map) {
			for(int j:i) {
				if(j==0 || j==-1) continue;

				sum+=j;
			}
		}

		return sum;
	}

	static void solve() {
		// T만큼 돌기.
		for(int t=1; t<=T; t++) {
			tmp = new int[R][C];
			// 미세먼지 확산
			spreadDust();
			
			tmp = new int[R][C];
			// 공기청정기 실행
			excuteAirCleaner();
		}
	}

	static void spreadDust() {
		// 확산된 후의 원산지의 값 변경은 map에 저장
		// 확산된 값은 tmp에 저장
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==0) continue;

				int sum=0;
				// 4방향으로 탐색
				for(int k=0; k<4; k++) {
					int ny = i+dy[k];
					int nx = j+dx[k];

					// 범위를 벗어나거나 로봇청소기가 있다면 확산하지 않음.
					if(!inRange(ny, nx) || map[ny][nx]==-1) continue;

					sum += map[i][j]/5;

					tmp[ny][nx] += map[i][j]/5;
				}

				// 확산시킨 만큼 뺀다.
				map[i][j] -= sum;
			}
		}

		// 그런 다음, 다 더해줌.
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(tmp[i][j]==0) continue;
				map[i][j] += tmp[i][j];
			}
		}

		//		print();
	}

	static void print() {
		for(int i[]:map) {
			for(int j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void excuteAirCleaner() {
		// 반시계 방향
		int r1=r[0], c1=c[0];
		int printX=C-1, printY=r1, t=1;

		for(int k=0; k<2; k++) {

			for(int i=0; i<printX; i++) {
				int dust = map[r1][c1];
				c1 += t;

				if(dust==-1 || dust==0) continue;

				tmp[r1][c1]=dust;
			}

			t *= -1;

			for(int i=0; i<printY; i++) {
				int dust = map[r1][c1];
				r1 += t;

				if(dust==0) continue;

				if(map[r1][c1]!=-1) tmp[r1][c1]=dust;
			}
		}


		// 시계 방향
		int r2=r[1], c2=c[1];
		printY=R-r2; printX=C-1; t=1;
		
		if(r2!=R-1) {
			for(int k=0; k<2; k++) {
				for(int i=0; i<printX; i++) {
					int dust = map[r2][c2];
					c2 += t;

					if(dust==-1 || dust==0) continue;
					
					tmp[r2][c2]=dust;
				}

				for(int i=0; i<printY-1; i++) {
					int dust = map[r2][c2];
					r2 += t;

					if(dust==0) continue;
					
					if(map[r2][c2]!=-1) tmp[r2][c2]=dust;
				}
				
				t *= -1;
			}
		}
		copy();
	}

	static void copy() {
		// 반시계 방향
				int r1=r[0], c1=c[0];
				int printX=C-1, printY=r1, t=1;

				for(int k=0; k<2; k++) {
					for(int i=0; i<printX; i++) {
						if(map[r1][c1]!=-1) map[r1][c1] = tmp[r1][c1];
						c1 += t;
					}

					t *= -1;

					for(int i=0; i<printY; i++) {
						if(map[r1][c1]!=-1) map[r1][c1] = tmp[r1][c1];
						r1 += t;
					}
				}


				// 시계 방향
				int r2=r[1], c2=c[1];
				printY=R-r2; printX=C-1; t=1;
				
				if(r2!=R-1) {
					for(int k=0; k<2; k++) {
						for(int i=0; i<printX; i++) {
							if(map[r2][c2]!=-1) map[r2][c2] = tmp[r2][c2];
							c2 += t;
						}

						for(int i=0; i<printY-1; i++) {
							if(map[r2][c2]!=-1) map[r2][c2] = tmp[r2][c2];
							r2 += t;
						}
						
						t *= -1;
					}
				}
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<R) && (x>=0&&x<C);
	}
}