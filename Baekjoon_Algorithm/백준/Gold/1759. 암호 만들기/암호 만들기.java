import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C, ans;
	static char[] str;
	static char[] code;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		str = new char[C];
		code = new char[L];

		st = new StringTokenizer(sc.readLine());
		for (int i = 0; i<C; i++) {
			str[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(str);
		solve(0, 0);
	}

	static void solve(int k, int idx) {
		if(idx==L) {
			if(isValid()) System.out.println(code);
			return;
		}

		for(int i=k; i<C; i++) {
			code[idx] = str[i];
			solve(i+1, idx+1);
		}
	}

	static boolean isValid() {
		int mo = 0;
		int ja = 0;

		for(char c:code) {
			if (c == 'a' || c == 'e' || c == 'i'  || c == 'o' || c == 'u') {
				mo++;
			} else {
				ja++;
			}
		}

		if (mo >= 1 && ja >= 2) {
			return true;
		}
		return false;
	}
}