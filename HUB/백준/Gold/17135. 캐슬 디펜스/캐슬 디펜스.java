import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Archer implements Comparable<Archer>{
	int y, x, d;

	public Archer(int y, int x, int d) {
		super();
		this.y = y;
		this.x = x;
		this.d = d;
	}

	@Override
	public int compareTo(Archer o) {
		if(d==o.d) return x-o.x;
		return d-o.d;
	}
}

public class Main {
	static int N, M, D, cnt, idx, enemy, ans;
	static int[][] map;

	static ArrayList<Integer> arr = new ArrayList<Integer>();

	static boolean[][][] visited;

	static int[] dy = {0, -1, 0};
	static int[] dx = {-1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D= Integer.parseInt(st.nextToken());

		map = new int[N+1][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) enemy++;
			}
		}
		visited = new boolean[2000][N][M];

		makeCombi(0, 0);
		System.out.println(ans);
	}

	static void solve(int e) {
		PriorityQueue<Archer> pq = new PriorityQueue<Archer>();

		int tmp = N;
		while(true) {
			if(e==0 || tmp==0) return;
			Queue<Archer> q = new LinkedList<Archer>();

			for(int i=0; i<3; i++) {
				q.add(new Archer(tmp, arr.get(i), 1));

				while(!q.isEmpty()) {
					Archer a = q.poll();
					
					if(!canShoot(a.d)) continue;
					for(int k=0; k<3; k++) {
						int ny = a.y+dy[k];
						int nx = a.x+dx[k];

						if(ny==tmp || !inRange(ny, nx)) continue;

						if(map[ny][nx]==1 && !visited[idx][ny][nx]) {
							pq.add(new Archer(ny, nx, a.d+1));
							q.clear();
							break;
						}

						q.add(new Archer(ny, nx, a.d+1));
					}
				}
			}

			for(int i=0; i<3; i++) {
				if(pq.isEmpty()) break;
				
				Archer a = pq.poll();

				if(visited[idx][a.y][a.x]) continue;
				visited[idx][a.y][a.x]=true;
				e--;
				cnt++;
			}
			tmp--;
			pq.clear();
		}
	}

	static void makeCombi(int depth, int w) {
		if(depth==3) {
			cnt=0;
			solve(enemy);
			ans = Math.max(ans, cnt);
			idx++;
			return;
		}

		for(int i=w; i<M; i++) {
			arr.add(i);
			makeCombi(depth+1, i+1);
			arr.remove(arr.size()-1);
		}
	}

	static boolean canShoot(int d) {
		return d<=D;
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<M);
	}
}