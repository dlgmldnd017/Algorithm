import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
    static String ans="";
    
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
		
		for(int i=0; i<numbers.length; i++) {
			list.add(numbers[i]+"");
		}
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String option1 = s1 + s2;
				String option2 = s2 + s1;
				return option2.compareTo(option1);
			}
		});
		
		for(String k:list) {
			ans+=k;
		}
		
		if(ans.charAt(0)=='0') ans="0";
        return ans;
    }

}