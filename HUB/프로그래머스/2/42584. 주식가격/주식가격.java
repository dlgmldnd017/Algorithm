import java.util.*;

class Solution {
    static int N;
    static int[] ans;
    
    public int[] solution(int[] prices) {
        N = prices.length;
        ans = new int[N];
        
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                ans[i]++;
                if(prices[i]>prices[j]) break;
            }
        }
        
        return ans;
    }
}