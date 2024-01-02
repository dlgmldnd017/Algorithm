import java.util.*;

class Solution {
    static int ans;
    static boolean visited[];
    static String str="";
    static List<String> map = new ArrayList<>();
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        
        for(int i=0; i<numbers.length(); i++){
            solve(0, i+1, numbers);
        }
        
        for(int i=0; i<map.size(); i++){
            findAns(Integer.parseInt(map.get(i)));
        }
        
        return ans;
    }
    
    static void solve(int depth, int N, String numbers){
        if(depth==N){
            if(str.charAt(0)=='0' || map.contains(str)) return;
            
            map.add(str);
            return;
        }
        
        for(int i=0; i<numbers.length(); i++){
            if(visited[i]) continue;
            
            visited[i]=true;
            str+=numbers.charAt(i);
            
            solve(depth+1, N, numbers);
            
            visited[i]=false;
            str = str.substring(0, str.length()-1);
        }
    }
    
    static void findAns(int tmp){
        if(tmp<2) return;
        
        for(int i=2; i<tmp; i++){
            if(tmp%i==0) return;
        }
        
        ans++;
    }
}