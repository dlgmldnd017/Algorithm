import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int size = nums.length/2;
        
        Set set = new HashSet();
        for(int i:nums){
            set.add(i);
        }
        if(size > set.size()){
            answer = set.size();
        }else{
            answer = size;
        }
        return answer;
    }
}