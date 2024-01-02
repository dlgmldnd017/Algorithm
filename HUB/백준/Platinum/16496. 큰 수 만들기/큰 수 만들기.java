import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static String ans="";
	static int numbers[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(sc.readLine());
		
		numbers = new int[N];
		
		List<String> list = new ArrayList<>();
		
		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<N; i++) {
			list.add(st.nextToken());
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
		System.out.println(ans);
	}
}