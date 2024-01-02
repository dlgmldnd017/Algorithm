import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> st1 = new Stack<>();
		
		if(s.charAt(0)==')') return false;
		
		for(int i=0; i<s.length(); i++) {
			char tmp = s.charAt(i);
			
			if(tmp=='(') st1.add(tmp);
			else {
				if(st1.size()==0) return false;
				else if(st1.peek()=='(') st1.pop();
			}
		}
		
		if(st1.size()==0) return true;
		else return false;
    }
}