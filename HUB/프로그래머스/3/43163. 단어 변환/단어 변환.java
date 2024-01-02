import java.util.*;

class Solution {
    static int ans;
    static boolean visited[];
    
    public int solution(String begin, String target, String[] words) {
       
        ans = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        
        solve(begin, target, words, 0);
        
        if(ans==Integer.MAX_VALUE) ans=0;
        return ans;
    }
    
    static void solve(String begin, String target, String[] words, int cnt){
        if(begin.equals(target)){
            if(ans>cnt) ans=cnt;
            return;
        }
        
        for(int i=0; i<words.length; i++){
            if(visited[i]) continue;
            
            int count=0;
            
            for(int j=0; j<words[i].length(); j++){
                if(begin.charAt(j)==words[i].charAt(j)) continue;
                
                count++;
            }
            
            if(count>1) continue;
            
            visited[i]=true;
            solve(words[i], target, words, cnt+1);
            visited[i]=false;
        }
    }
}