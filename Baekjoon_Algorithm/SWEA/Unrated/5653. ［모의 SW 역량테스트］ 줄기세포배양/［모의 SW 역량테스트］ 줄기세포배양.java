import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Cell implements Comparable<Cell>{
	int y, x, size, time, state;

	public Cell(int y, int x, int size, int time) {
		this.y = y;
		this.x = x;
		this.size = size;
		this.time = time;
		this.state=0;
	}

	@Override
	public int compareTo(Cell o) {
		return -(size-o.size);
	}
}

public class Solution {
	static int N, M, K, ans;
	static int map[][];
	
	static List<Cell> cell;
	
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= test_case; T++) {

			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N+K*2][M+K*2];
			cell = new ArrayList<Cell>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(sc.readLine());
				
				for(int j=0; j<M; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					
					if(tmp==0) continue;
					
					map[i+K][j+K] = tmp;
					cell.add(new Cell(i+K, j+K, tmp, tmp));
				}
			}
			
			solve();
			ans=count();
			sb.append("#" + T + " " + ans + "\n");
		}
		System.out.println(sb);
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

	public static void solve() {
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
		
		for(int T=1; T<=K; T++) {
			
			// 활성화시키기
			while(!pq.isEmpty()) {
				Cell cur = pq.poll();
				
				for(int k=0; k<4; k++) {
					int ny = cur.y+dy[k];
					int nx = cur.x+dx[k];
					
					if(map[ny][nx]!=0) continue;
					
					map[ny][nx] = cur.size;
					cell.add(new Cell(ny, nx, cur.size, cur.size+T));
				}
			}
			
			// 비활성화 또는 활성화된 세포 죽이기
			for(int i=0; i<cell.size(); i++) {
				Cell cur = cell.get(i);
				
				// 죽은 세포는 비교할 필요 없음
				if(cur.state==-1) continue;
				
				// 비활성화 -> 활성화
				if(cur.time==T && cur.state==0) {
					cur.state=1;
					cur.time = T+cur.size;
					pq.add(cur);
				}
				
				// 활성화 -> 죽음
				else if(cur.time==T && cur.state==1) {
					cur.state=-1;
				}
			}	
		}
	}
	
	static int count() {
		int cnt=0;
		
		for(Cell cur : cell) {
			if(cur.state!=-1) cnt++;
		}
		
		return cnt;
	}
}