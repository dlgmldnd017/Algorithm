import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R, ans;
	static int map[][];
	
	static boolean check, visited[][];
	
	static List<Node> list[];
	
	static int dy[] = {0, 0, -1, 1};
	static int dx[] = {-1, 1, 0, 0};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			int num=1;
			check = false;
			visited = new boolean[N][N];
			list = new ArrayList[N*N+1];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						list[num] = new ArrayList<Node>();
						solve(i, j, num++);
					}
				}
			}
			
			if(!check) break;
			updateMap(num);
			ans++;
		}
		System.out.println(ans);
		
	}

	static void solve(int y, int x, int num) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(y, x));
		
		visited[y][x]=true;
		list[num].add(new Node(y,x));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];
				
				if(!inRange(ny, nx) || visited[ny][nx]) continue;
				
				int value = Math.abs(map[cur.y][cur.x]-map[ny][nx]);
				if(L<=value && value<=R) {
					check=true;
					visited[ny][nx]=true;
					q.add(new Node(ny, nx));
					
					list[num].add(new Node(ny, nx));
				}
			}
		}
	}
	
	static void updateMap(int num) {
		for(int i=1; i<num; i++) {
			if(list[i].size()==1) continue;
			
			int sum=0;
			for(int j=0; j<list[i].size(); j++) {
				int y = list[i].get(j).y;
				int x = list[i].get(j).x;
				
				sum += map[y][x];
			}
			
			int value = (sum/list[i].size());
			for(int j=0; j<list[i].size(); j++) {
				int y = list[i].get(j).y;
				int x = list[i].get(j).x;
				
				map[y][x]=value;
			}
		}
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}

class Node{
	int y, x;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}