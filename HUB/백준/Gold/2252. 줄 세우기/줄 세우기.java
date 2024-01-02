import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans;
	static int dist[];
	static ArrayList<ArrayList<Integer>> arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N];
		arr = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			arr.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(sc.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			
			arr.get(y).add(x);
			dist[x]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=0; i<dist.length; i++) {
			if(dist[i]==0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int num = q.poll();
			System.out.print((num+1)+" ");
			
			ArrayList<Integer> list = arr.get(num);
			
			for(int i=0; i<list.size(); i++) {
				dist[list.get(i)]--;
				
				if(dist[list.get(i)]==0) q.add(list.get(i));
			}
		}
	}
}