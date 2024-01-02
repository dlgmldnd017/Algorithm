import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Fish implements Comparable<Fish>{
	int y, x, size, eatCnt, d;

	public Fish(int y, int x, int size, int eatCnt, int d) {
		this.y = y;
		this.x = x;
		this.size = size;
		this.eatCnt = eatCnt;
		this.d = d;
	}

	@Override
	public int compareTo(Fish o) {
		if(d==o.d) {
			if(y==o.y) {
				return x-o.x;
			}else {
				return y-o.y;
			}
		}else {
			return d-o.d;
		}
	}
}

public class Main {
	static int N, ans;
	static int map[][];
	
	static Queue<Fish> q = new LinkedList<Fish>();
	static PriorityQueue<Fish> fish = new PriorityQueue<Fish>();
	
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]==9) {
					q.add(new Fish(i, j, 2, 0, 0));
					map[i][j]=0;
				}
			}
		}
		
		solve();
		System.out.println(ans);
	}

	static void solve() {
		while(true) {
			boolean visited[][] = new boolean[N][N];
			visited[q.peek().y][q.peek().x]=true;
			
			while(!q.isEmpty()) {
				Fish cur = q.poll();
				
				for(int k=0; k<4; k++) {
					int ny = cur.y+dy[k];
					int nx = cur.x+dx[k];
					
					if(!inRange(ny, nx) || visited[ny][nx] || !canGo(ny, nx, cur.size)) continue;
					
					if(map[ny][nx]!=0 && canEat(ny, nx, cur.size)) fish.add(new Fish(ny, nx, cur.size, cur.eatCnt+1, cur.d+1));
					
					q.add(new Fish(ny, nx, cur.size, cur.eatCnt, cur.d+1));
					visited[ny][nx]=true;
				}
			}
			
			if(fish.isEmpty()) return;
			
			Fish curFish = fish.poll();
			if(curFish.eatCnt==curFish.size) {
				curFish.size++;
				curFish.eatCnt=0;
			}
			
			map[curFish.y][curFish.x]=0;
			q.add(new Fish(curFish.y, curFish.x, curFish.size, curFish.eatCnt, 0));
			fish.clear();
			ans += curFish.d;
		}
	}
	
	static boolean canEat(int ny, int nx, int size) {
		return map[ny][nx]<size;
	}

	static boolean canGo(int ny, int nx, int size) {
		return map[ny][nx]<=size;
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}