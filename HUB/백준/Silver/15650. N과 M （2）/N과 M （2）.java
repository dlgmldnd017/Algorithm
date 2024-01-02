import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer> arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new ArrayList<>();
		visited = new boolean[N+1];
		solve(0);
	}

	public static void solve(int depth) {
		if(depth==M) {
			for(int i=0; i<arr.size(); i++) {
				System.out.print(arr.get(i) + " ");
			}
			System.out.println();
			return;
		}
		
		int here = arr.isEmpty()?1:arr.get(arr.size()-1)+1;
		for(int i=here; i<=N; i++) {

			if(!visited[i]) {
				visited[i]=true; 
				arr.add(i);
				solve(depth+1);
				arr.remove(arr.size()-1);
				visited[i]=false; 
			}
		}
	}
}