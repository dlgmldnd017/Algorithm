import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int map[][];

	static ArrayList<Integer> list;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		
		list = new ArrayList<Integer>();
		list.add(0);
		
		visited = new boolean[N];
		visited[0]=true;
		
		solve(0, 0);
		
		System.out.println(ans);
	}

	static void solve(int start,  int length) {
		if(list.size()==N) {
			if(map[list.get(list.size()-1)][0]!=0) {
				ans = Math.min(ans, length+map[list.get(list.size()-1)][0]);
				return;
			}
		}
		
		for(int i=1; i<N; i++) {
			if(!visited[i] && map[start][i]!=0) {
				visited[i]=true;
				list.add(i);
				
				solve(i, length+map[start][i]);
				
				visited[i]=false;
				list.remove(list.size()-1);
			}
		}
	}
}