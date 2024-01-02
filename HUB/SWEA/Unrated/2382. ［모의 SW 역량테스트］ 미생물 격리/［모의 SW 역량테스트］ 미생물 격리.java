import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K, ans;
	
	static String map[][];
	
	static List<Node> list;
	
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = Integer.parseInt(sc.readLine());
		for(int t=1; t<=test_case; t++) {
			
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<Node>();
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(sc.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				
				list.add(new Node(y, x, cnt, dir, i));
			}
			
			solve();
			ans = findAns();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.println(sb);
	}
	
	static void solve() {
		for(int i=0; i<M; i++) {
			// 이동한다.
			move();
			
			// 겹치는 군집을 확인한다.
			checkOver();
		}
	}
	
	static void move() {
		map = new String[N][N];
		
		for(Node n : list) {
			if(n.isDeath) continue;
			
			n.y += dy[n.dir];
			n.x += dx[n.dir];
			
			if(map[n.y][n.x]==null) map[n.y][n.x]=""+n.idx+",";
			else map[n.y][n.x]+=n.idx+",";
			
			
			if(!inRedZone(n.y, n.x)) continue;
			
			n.cnt/=2;
			if(n.cnt==0) {
				n.isDeath=true;
				continue;
			}
			
			if(n.dir%2==0) n.dir+=1;
			else n.dir-=1;
		}
	}
	
	static boolean inRedZone(int y, int x) {
		return (y==0||x==0) || (y==N-1||x==N-1);
	}
	
	static void checkOver() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==null || map[i][j].length()<=1) continue;
				
				// 겹치는 부분 발생
				String str[] = map[i][j].split(",");
				
				int max=-1, maxIdx=-1, sum=0;
				for(int k=0; k<str.length; k++) {
					int idx = Integer.parseInt(str[k]);
					int cnt = list.get(idx).cnt;
					
					sum += cnt;
					if(max>=cnt) continue;
					
					max = list.get(idx).cnt;
					maxIdx = idx;
				}
				
				for(int k=0; k<str.length; k++) {
					int idx = Integer.parseInt(str[k]);
					
					if(maxIdx==idx) list.get(idx).cnt = sum;
					else list.get(idx).isDeath=true;
				}
			}
		}
	}
	
	static int findAns() {
		int cnt=0;
		
		for(Node n : list) {
			if(n.isDeath) continue;
			
			cnt += n.cnt;
		}
		
		return cnt;
	}
}

class Node{
	int y, x, cnt, dir, idx;
	boolean isDeath;
	
	public Node(int y, int x, int cnt, int dir, int idx) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.dir = dir;
		this.idx = idx;
	}
}