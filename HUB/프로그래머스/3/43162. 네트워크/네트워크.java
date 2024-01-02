import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int ans;
    static boolean visited[];
	static List<Integer> list[];
    
    public int solution(int n, int[][] computers) {
        list = new ArrayList[n];
		for(int i=0; i<computers.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<computers.length; i++) {
			for(int j=0; j<computers[i].length; j++) {
				if(computers[i][j]==0 || i==j) continue;
				list[i].add(j);
			}
		}
		
		visited = new boolean[n];
		for(int i=0; i<n; i++) {
			if(visited[i]) continue;
			
			solve(i);
			ans++;
		}
        
        return ans;
    }
    static void solve(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			start = q.poll();
			
			if(visited[start]) continue;
			visited[start] = true;
			
			for(int next : list[start]) {
				if(visited[next]) continue;
				q.add(next);
			}
		}
	}
}