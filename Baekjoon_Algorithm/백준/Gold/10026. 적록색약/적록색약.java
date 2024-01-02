import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Color{
	int y, x;
	String color;
	
	public Color(int y, int x, String color) {
		this.y = y;
		this.x = x;
		this.color = color;
	}
}

public class Main {
	static int N, ans;
	static String map1[][], map2[][];
	
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		
		map1 = new String[N][N];
		map2 = new String[N][N];
		
		for(int i=0; i<N; i++) {
			String tmp = sc.readLine();
			
			for(int j=0; j<N; j++) {
				map1[i][j] = tmp.charAt(j)+"";
				
				if(tmp.charAt(j)=='R' || tmp.charAt(j)=='G') map2[i][j] = "R";
				else map2[i][j]="B";
			}
		}
		
		solve(map1); 
		ans=0;
		solve(map2);
		System.out.println(sb);
	}
	

	static void solve(String map[][]) {
		Queue<Color> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		int cnt=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]) continue;
				
				q.add(new Color(i, j, map[i][j]));
				visited[i][j]=true;
				
				while(!q.isEmpty()) {
					Color c = q.poll();
					int y = c.y;
					int x = c.x;
					String color = c.color;
					
					for(int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						
						if(!inRange(ny, nx) || visited[ny][nx]) continue;
						
						if(map[ny][nx].equals(color)) {
							q.add(new Color(ny, nx, color));
							visited[ny][nx]=true;
						}
					}
				}
				cnt++;
			}
		}
		sb.append(cnt+" ");
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}