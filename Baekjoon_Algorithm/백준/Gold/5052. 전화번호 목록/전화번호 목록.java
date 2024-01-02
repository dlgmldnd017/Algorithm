import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N;
	static String str[], ans;
	static Map<String, Integer> map;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(sc.readLine());
		for(int t=0; t<test_case; t++) {
			N = Integer.parseInt(sc.readLine());
			
			str = new String[N];
			map = new HashMap<>();
			
			for(int i=0; i<N; i++) {
				String tmp = sc.readLine();
				str[i]=tmp;
				map.put(tmp, i);
			}
			
			ans="";
			solve();
			sb.append(ans+"\n");
		}
		
		System.out.println(sb);
	}

	static void solve() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<str[i].length(); j++) {
				if(map.containsKey(str[i].substring(0, j))) {
					ans = "NO";
					return;
				}
			}
		}
		ans = "YES";
	}
}