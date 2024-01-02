import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int map[];
	
	static ArrayList<Integer> list = new ArrayList<>();

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());

		map = new int[N];
		
		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		list.add(map[0]);
		
		for(int i=1; i<N; i++) {
			if(map[i]>list.get(list.size()-1)) {
				list.add(map[i]);
			}
			else {
				int r = Collections.binarySearch(list, map[i]);
				if (r < 0) {
	                if (-r > list.size())
	                    list.add(map[i]);
	                else
	                    list.set(-r - 1, map[i]);
	            }
			}
		}
		
		System.out.println(list.size());
	}
//
//	private static int binaryInsert(int k) {
//		int left = 0;
//		int right = list.size()-1;
//		
//		while(left<right) {
//			int mid = (left+right)/2;
//			
//			if(map[mid]>=k) {
//				right = mid;
//			}
//			else {
//				left = mid+1;
//			}
//		}
//		return right;
//	}
}
