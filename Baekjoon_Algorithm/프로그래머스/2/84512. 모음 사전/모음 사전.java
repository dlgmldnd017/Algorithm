import java.util.*;

class Solution {
    static int cnt, ans;
    static String list[] = {"A", "E", "I", "O", "U"};
    static String w;
    
    static boolean check;
    
    public int solution(String word) {
        w = word;
        solve(0, "");
        return ans;
    }
    
    static void solve(int depth, String str){
        if(check) return;
        
        if(str.equals(w)){
            check=true;
            ans = cnt;
        }
        
        if(depth>5) return;
        
        cnt++;
        
        for(int i=0; i<5; i++){
            System.out.println(str);
            solve(depth+1, str+list[i]);
        }
    }
}