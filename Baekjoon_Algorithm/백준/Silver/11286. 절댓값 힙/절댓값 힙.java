import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{
	static int N; 
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1)==Math.abs(o2)) return o1-o2;
				else return Math.abs(o1)-Math.abs(o2);
			}
		});
		
		N = Integer.parseInt(sc.readLine());
		for(int k=0; k<N; k++) {
			int x=Integer.parseInt(sc.readLine());
			
			if(x==0) {
				if(pq.isEmpty()) sb.append(0+"\n");
				else sb.append(pq.poll()+"\n");
				continue;
			}
			pq.add(x);	
		}
		System.out.println(sb);
	}
}