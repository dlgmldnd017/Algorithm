import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x, cnt;

	public Node(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class Solution {
	static int N, W, H, ans;
	static int map[][];
	
	static int dy[] = {1, 0, 0, -1};
	static int dx[] = {0, -1, 1, 0};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(sc.readLine());
				
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			solve(0);
			if(ans==Integer.MAX_VALUE) ans=0;
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int cnt) {
		if(ans==0) return;
		
		if(cnt==N) {
			int count = findAns();
			ans = Math.min(ans, count);
			return;
		}
		
		int copy[][] = copyArr(map);
		
		for(int i=0; i<W; i++) {
			boolean flag=false;
			map = copyArr(copy);
			
			for(int j=0; j<H; j++) {
				if(map[j][i]!=0) {
					shootBall(j, i);
					flag = true;
					break;
				}
			}
			if(flag) solve(cnt+1);
		}
	}

	static void shootBall(int y, int x) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(y, x, map[y][x]));
		
		// 방문체크와 동시에 벽돌에 적힌 수만큼 깬다.
		boolean visited[][] = new boolean[H][W];
		while(!q.isEmpty()) {
			Node start = q.poll();
			map[start.y][start.x]=0;
			visited[start.y][start.x]=true;
			
			if(start.cnt==1) continue;
			
			for(int k=0; k<4; k++) {
				for(int l=1; l<=start.cnt-1; l++) {
					int ny = start.y+dy[k]*l;
					int nx = start.x+dx[k]*l;
					
					if(!inRange(ny, nx)) break;
					
					if(visited[ny][nx] || map[ny][nx]==0) continue;
					
					visited[ny][nx]=true;
					if(map[ny][nx]>1) q.add(new Node(ny, nx, map[ny][nx]));
					map[ny][nx]=0;
				}
			}
		}
		
		
		// 벽돌을 모두 부셨다면 공중에 있는 벽돌들이 있는지 체크하고
		// 있다면 공중에 뜨지 않게 한다.
		checkAir();
	}
	
	static void printArr() {
		for(int i[]:map) {
			for(int j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void checkAir() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int j=0; j<W; j++) {
			for(int i=H-1; i>=0; i--) {
				if(map[i][j]!=0) q.add(map[i][j]);
				map[i][j]=0;
			}
			for(int i=H-1; i>=0; i--) {
				if(q.isEmpty()) break;
				map[i][j] = q.poll();
			}
		}
	}
	
	static int findAns() {
		int cnt=0;
		for(int i[]:map) {
			for(int j:i) {
				if(j!=0) cnt++;
			}
		}
		return cnt;
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<H)&&(x>=0&&x<W);
	}
	
	static int[][] copyArr(int[][] copy) {
		int tmp[][] = new int[H][W];
		for(int i=0; i<H; i++) {
			tmp[i] = copy[i].clone();
		}
		return tmp;
	}
}