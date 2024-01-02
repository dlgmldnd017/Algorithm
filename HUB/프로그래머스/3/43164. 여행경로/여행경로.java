import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    static Map<String, List<Node>> map;
	static boolean isFinish, visited[];
	static int len;
	
	static String ans[];
    
    public String[] solution(String[][] tickets) {
        map = new HashMap<String, List<Node>>();
		visited = new boolean[tickets.length];
		len = tickets.length;
		ans = new String[tickets.length+1];
		
		for(int i=0; i<tickets.length; i++) {
			if(!map.containsKey(tickets[i][0])) map.put(tickets[i][0], new ArrayList<>());
			
			map.get(tickets[i][0]).add(new Node(tickets[i][1], i));
		}
		
        for(int i=0; i<len; i++) {
			Collections.sort(map.get(tickets[i][0]));
		}
        
		ans[0] = "ICN";
		solve(0, "ICN");
        return ans;
    }
    
    static void solve(int depth, String start) {
		if(depth==len) {
			isFinish=true;
			return;
		}
		
		if(!map.containsKey(start)) return;
		
		for(Node n : map.get(start)) {
			if(visited[n.idx] || isFinish) continue;
			ans[depth+1] = n.end;
			
			visited[n.idx]=true;
			solve(depth+1, n.end);
			visited[n.idx]=false;
		}
	}
}

class Node implements Comparable<Node>{
	String end;
	int idx;

	public Node(String end, int idx) {
		this.end = end;
		this.idx = idx;
	}

	@Override
	public int compareTo(Node o) {
		return end.compareTo(o.end);
	}
}