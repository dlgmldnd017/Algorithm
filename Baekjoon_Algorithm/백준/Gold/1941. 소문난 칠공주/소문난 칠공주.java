import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	static int N=5, ans;
	static int comb[], combX[], combY[];
	
	static String map[][];

	static boolean visited[][];
	
	static List<Node> list = new ArrayList<Node>();
	
	static int dy[] = {0, 1, 0, -1};
	static int dx[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		comb = new int[7];
		combX = new int[25];
		combY = new int[25];
		
		map = new String[N][N];
		
		visited = new boolean[N][N];
		
		for(int i=0; i<25; i++) {
            combX[i] = i % 5;
            combY[i] = i / 5;
        }
		
		for(int i=0; i<N; i++) {
			String str = sc.readLine();
			
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j)+"";
			}
		}
		
		makeComb(0, 0, 7);
		System.out.println(ans);
	}

	static void makeComb(int depth, int start, int end) {
		if(end==0) {
			solve();
			return;
		}
		
		if(depth==25) return;
		
		comb[start] = depth;
		makeComb(depth+1, start+1, end-1);
		makeComb(depth+1, start, end);
	}
	
	static void solve() {
		Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[7];
        
        visited[0] = true;
        q.add(comb[0]);
        int cnt = 1, sCnt = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(map[combY[cur]][combX[cur]].equals("S")) sCnt++;
            
            for(int i=0; i<4; i++) {
                for(int next=1; next<7; next++) {
                    if(!visited[next] && combX[cur]+dx[i] == combX[comb[next]] && combY[cur]+dy[i] == combY[comb[next]]) {
                        visited[next] = true;
                        q.add(comb[next]);
                        cnt++;
                    }
                }
            }
        }
        
        if(cnt == 7) {
            if(sCnt >=4) {
                ans++;
            }
        }
	}
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}