import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x;

	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {
	static int N, L, ans;
	static int[] sec;
	static int[][] map;
	
	static String[] str;
	
	static Queue<Node> q = new LinkedList<>();
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		map = new int[N+1][N+1];
		
		int size = Integer.parseInt(sc.readLine());
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x]=2;
		}
		
		L = Integer.parseInt(sc.readLine());
		sec = new int[L];
		str = new String[L];
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(sc.readLine());
			sec[i] = Integer.parseInt(st.nextToken());
			str[i] = st.nextToken();
		}
		
		solve();
		System.out.println(ans);
	}
	
	public static void solve() {
		Deque<Node> snake = new ArrayDeque<>();
		snake.add(new Node(1,1));
		
		int dir=0, time=0, idx=0;
		while(true) {
			time++;
			
			int ny = snake.getFirst().y+dy[dir];
			int nx = snake.getFirst().x+dx[dir];
			
			// 기저 사례(1): 이동 좌표가 범위 밖이거나 내 몸일경우
			if(!inRange(ny,nx) ||  map[ny][nx]==1) {
				ans = time;
				return;
			}
			
			if(map[ny][nx]==2) {
				map[ny][nx]=0;
			}else {
				Node tail = snake.pollLast();
				map[tail.y][tail.x]=0;
			}
			
			snake.addFirst(new Node(ny, nx));
			map[ny][nx]=1;
			
			// 만약 시간이 다 되면 방향 바꾸기
			if(idx!=L&&sec[idx]==time) {
				if(str[idx].equals("D")) dir = (dir+1)==4?0:dir+1;
				else dir = (dir-1)==-1?3:dir-1;
				idx++;
			}
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (y>=1&&y<=N) && (x>=1&&x<=N);
	}
}