import java.util.*;

class Solution {
    static int N, M, ans;
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};
    
    public int solution(int[][] maps) {
        N = maps.length; M = maps[0].length;
        ans = -1;
        
        solve(maps);
        return ans;
    }
    
    static void solve(int [][] maps){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));
        
        boolean visited[][] = new boolean[N][M];
        visited[0][0]=true;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int k=0; k<4; k++){
                int ny = cur.y+dy[k];
                int nx = cur.x+dx[k];
                
                if(!inRange(ny, nx) || visited[ny][nx] || maps[ny][nx]==0) continue;
                
                if(ny==N-1 && nx==M-1){
                    ans = cur.cnt+1;
                    return;
                }
                
                q.add(new Node(ny, nx, cur.cnt+1));
                visited[ny][nx]=true;
            }
        }
    }
    
    static boolean inRange(int y, int x){
        return (y>=0&&y<N) && (x>=0&&x<M);
    }
}

class Node{
    int y, x, cnt;
    
    public Node(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}