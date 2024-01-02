import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
	int idx, number;
	
	public Node(int idx, int number) {
		this.idx=idx;
		this.number=number;
	}
}

public class Main {
	static int N;
	static int[] ans;
	static Stack<Node> stack;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());
		ans = new int[N];

		st = new StringTokenizer(sc.readLine());
		stack = new Stack<>();
		stack.push(new Node(1, Integer.parseInt(st.nextToken())));
		
		for(int i=2; i<=N; i++) {
			Node cur = new Node(i, Integer.parseInt(st.nextToken()));
			
			if(cur.number > stack.peek().number) {
				stack.pop();
				int size = stack.size();
				
				for(int j=0; j<size; j++) {
					if(cur.number<stack.peek().number) {
						ans[i-1]=stack.peek().idx;
						break;
					}
					stack.pop();
				}
				stack.push(cur);
			}else {
				ans[i-1]=stack.peek().idx;
				stack.push(cur);
			}
		}

		for(int i : ans) {
			System.out.print(i+" ");
		}
	}
}