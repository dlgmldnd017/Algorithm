import java.util.*;

class Solution {
    static int ans[];
    static boolean visited[];
    static List<Integer> list = new ArrayList<>();
    static List<Integer> map = new ArrayList<>();
    
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        
        for(int i=0; i<n; i++){
            int tmp = 100-(progresses[i]);
            
            if(tmp%speeds[i]==0) list.add(tmp/speeds[i]);
            else list.add((tmp/speeds[i])+1);
        }
        
        visited = new boolean[n];
            
        int w=0;
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            
            int tmp = list.get(i), cnt=1;
            visited[i]=true;
            w++;
            
            for(int j=i+1; j<n; j++){
                if(visited[j]) continue;
                
                if(tmp>=list.get(j)) {
                    visited[j]=true;
                    cnt++; w++;
                }else break;
            }
                
            map.add(cnt);
            if(w==n) break;
        }
            
        ans = new int[map.size()];
        for(int i=0; i<map.size(); i++){
            ans[i] = map.get(i);
        }
        
        return ans;
    }
}