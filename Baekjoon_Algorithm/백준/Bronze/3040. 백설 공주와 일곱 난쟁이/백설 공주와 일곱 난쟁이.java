import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static int N, ans;
	static int[] map;
	static ArrayList<Integer> arr;
	static boolean isFind;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = 9;
		arr = new ArrayList();
		map = new int[N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) {
			map[i]=Integer.parseInt(sc.readLine());
		}
		isFind=false;
		solve(0, 0);
	}


	public static void solve(int depth, int sum) {
		if(depth==7) { 
			if(sum==100) {
				isFind=true;
				for(int i=0; i<arr.size(); i++) {
					System.out.println(arr.get(i));
				}
				return;
			}
			return;
		}

		int here = arr.isEmpty()?0:arr.size();
		for(int next=here; next<N; next++) {
			if(!visited[next]) {
				visited[next]=true;
				arr.add(map[next]);
				solve(depth+1, sum+map[next]);
				if(isFind) return;
				arr.remove(arr.size()-1);
				visited[next]=false;
			}
		}
	}
}