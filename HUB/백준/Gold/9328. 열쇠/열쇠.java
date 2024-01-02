import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int y, x;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static int N, M, key, ans;
	static int visited[][];

	static char map[][];

	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for(int t=1; t<=test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			visited = new int[N+2][M+2];
			for(int i=0; i<=N+1; i++) {
				Arrays.fill(visited[i], -1);
			}
			
			map = new char[N+2][M+2];

			for(int i=1; i<=N; i++) {
				String str = sc.readLine();

				for(int j=1; j<=M; j++) {
					map[i][j] = str.charAt(j-1);
				}
			}

			key=0;
			String str = sc.readLine();
			if(!str.equals("0")) {
				for(int i=0; i<str.length(); i++) {
					int tmp = 1 << (str.charAt(i)-'a');
					key |= tmp;
				}
			}

			ans=0;
			solve(0, 0);
			sb.append(ans+"\n");
		}    

		System.out.println(sb);
	}

	static void solve(int y, int x) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(y, x));

		visited[y][x]=key;

		while(!q.isEmpty()) {
			Node cur = q.poll();

			for(int k=0; k<4; k++) {
				int ny = cur.y+dy[k];
				int nx = cur.x+dx[k];

				if(!inRange(ny, nx) || map[ny][nx]=='*' || visited[ny][nx]==key) continue;
				
				visited[ny][nx]=key;

				// 문을 만났을 때
				if('A'<=map[ny][nx] && map[ny][nx]<='Z') {
					int door = 1 << (map[ny][nx]-'A');

					if((door&key)==0) continue;
				}
				
				// 열쇠를 만났을 때
				else if('a'<=map[ny][nx] && map[ny][nx]<='z') {
					key |= (1 << map[ny][nx]-'a');
				}
				
				// 찾고 싶은 문서를 만났을 때
				else if(map[ny][nx]=='$') ans++;

				map[ny][nx]='.';
				q.add(new Node(ny, nx));
			}
		}
	}

	static boolean inRange(int y, int x) {
		return (y>=0&&y<=N+1) && (x>=0&&x<=M+1);
	}
}