import java.util.*;

class Solution {
    static int cnt, ans;
    static int map[][];
    
    public int solution(int n, int[][] results) {
        map = new int[n+1][n+1];
        
        for(int i=0; i<results.length; i++){
            map[results[i][0]][results[i][1]]=1;
        }
        
        for(int i=1; i<=n; i++){    
            solveWin(i, n);
            solveLose(i, n);
            
            if(cnt==n-1) ans++;

            cnt=0;
        }
        
        return ans;
    }
    
    static void solveWin(int start, int n){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        
        boolean visited[] = new boolean[n+1];
        visited[start]=true;
        
        while(!q.isEmpty()){
            start = q.poll();
            
            for(int i=1; i<=n; i++){
                if(!visited[i] && map[i][start]==1){
                    q.add(i);
                    visited[i]=true;
                    cnt++;
                }
            }
        }
    }
    
    static void solveLose(int start, int n){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        
        boolean visited[] = new boolean[n+1];
        visited[start]=true;

        while(!q.isEmpty()){
            start = q.poll();
            
            for(int i=1; i<=n; i++){
                if(!visited[i] && map[start][i]==1){
                    q.add(i);
                    visited[i]=true;
                    cnt++;
                }
            }
        }
    }
}