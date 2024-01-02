import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static ArrayList<Integer> num = new ArrayList<Integer>();
	static ArrayList<Character> op = new ArrayList<Character>();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(sc.readLine());
		
		String str = sc.readLine();
		for(int i=0; i<N; i++) {
			 char tmp = str.charAt(i);
			 
			 if(tmp=='+' || tmp=='-' || tmp=='*') {
				 op.add(tmp);
				 continue;
			 }
			 num.add(tmp-'0');
		}
		
		ans = Integer.MIN_VALUE;
		solve(0, num.get(0));
		System.out.println(ans);
	}

	static void solve(int idx, int sum) {
		if(idx>=op.size()) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// 먼저 계산
		int nextSum1 = calc(op.get(idx), sum, num.get(idx+1));
		solve(idx+1, nextSum1);
		
		// 괄호 씌움
		if(idx+1<op.size()) {
			int nextSum2 = calc(op.get(idx+1), num.get(idx+1), num.get(idx+2));
			solve(idx+2, calc(op.get(idx), sum, nextSum2));
		}
	}
	
	static int calc(char c, int n1, int n2) {
		switch (c) {
		case '+':
			return n1+n2;
			
		case '-':
			return n1-n2;
			
		default:
			return n1*n2;
		}
	}
}