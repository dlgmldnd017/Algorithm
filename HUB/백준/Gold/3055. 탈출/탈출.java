import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int y, x, t;

    public Node(int y, int x, int t) {
        this.y = y;
        this.x = x;
        this.t = t;
    }
}

public class Main {
    static int R, C, ans;
    static int waterMap[][];
    static String map[][];
    
    static Queue<Node> q = new LinkedList<>();
    
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, 1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(sc.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        waterMap = new int[R][C];
        map = new String[R][C];
        
        int startY=0, startX=0;
        for(int i=0; i<R; i++) {
            String str = sc.readLine();
            
            for(int j=0; j<C; j++) {
                map[i][j] = str.charAt(j)+"";
                if(map[i][j].equals("S")) {
                    startY = i;
                    startX = j;
                }
                else if(map[i][j].equals("*")) q.add(new Node(i, j, 1));
            }
        }
        
        ans = Integer.MAX_VALUE;
        
        spreadWater();
        solve(startY, startX);
        if(ans==Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(ans);
    }

    static void solve(int startY, int startX) {
        Queue<Node> q2 = new LinkedList<Node>();
        q2.add(new Node(startY, startX, 1));
        
        boolean visited[][] = new boolean[R][C];
        visited[startY][startX]=true;
        
        while(!q2.isEmpty()) {
        	Node cur = q2.poll();
        	
        	for(int k=0; k<4; k++) {
        		int ny = cur.y+dy[k];
        		int nx = cur.x+dx[k];
        		
        		if(inRange(ny, nx) && !visited[ny][nx]) {
        			if(map[ny][nx].equals("D")) {
        				ans = cur.t;
        				return;
        			}
        			
        			if(map[ny][nx].equals("X") || (waterMap[ny][nx]!=0 && waterMap[ny][nx]<=cur.t+1)) continue;
        			visited[ny][nx]=true;
        			q2.add(new Node(ny, nx, cur.t+1));
        		}
        	}
        }
    }
    
    static void spreadWater() {
        boolean visited[][] = new boolean[R][C];
        
        while(!q.isEmpty()) {
        	Node cur = q.poll();
            
            for(int k=0; k<4; k++) {
                int ny = cur.y+dy[k];
                int nx = cur.x+dx[k];
                
                if(inRange(ny, nx) && !visited[ny][nx] && map[ny][nx].equals(".")) {
                    visited[ny][nx]=true;
                    waterMap[ny][nx] = cur.t+1;
                    q.add(new Node(ny, nx, cur.t+1));
                }
            }
        }
    }
    
    static boolean inRange(int y, int x) {
        return (y>=0&&y<R) && (x>=0&&x<C);
    }
}