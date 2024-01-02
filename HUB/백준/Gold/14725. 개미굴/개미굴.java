import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());
		
		Node root = new Node();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			Node cur = root;
			
			for(int j=0; j<k; j++) {
				String str = st.nextToken();
				
				if(!cur.childs.containsKey(str)) {
					cur.childs.put(str, new Node());
				}
				
				cur = cur.childs.get(str);
			}
		}

		solve(root, "");
		System.out.println(sb);
	}

	static void solve(Node root, String bar) {
		Object[] key = root.childs.keySet().toArray();
		Arrays.sort(key);
		
		for(Object o : key) {
			sb.append(bar+o+"\n");
			solve(root.childs.get(o), bar+"--");
		}
	}
}

class Node{
	HashMap<String, Node> childs = new HashMap<>();
}