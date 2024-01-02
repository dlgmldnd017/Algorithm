import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, max, min;
	static int tmp[], opIdx[], map[];

	static String op[] = {"+", "-", "*", "/"};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			N = Integer.parseInt(sc.readLine());

			tmp = new int[4]; opIdx = new int[N-1];
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<4; i++) {
				tmp[i] = Integer.parseInt(st.nextToken());
			}

			map = new int[N];
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			solve(0);
			sb.append("#" + t + " " + Math.abs(max-min) + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int depth) {
		if(depth==N-1) {
			max = Math.max(max, calc());
			min = Math.min(min, calc());
			return;
		}

		for(int i=0; i<4; i++) {
			if(tmp[i]<=0) continue;

			tmp[i]--;
			opIdx[depth]=i;

			solve(depth+1);

			tmp[i]++;
		}
	}

	static int calc() {
		int sum = map[0];
		for(int i=1; i<N; i++) {
			switch(op[opIdx[i-1]]) {
			case "+":
				sum += map[i];
				break;
			case "-":
				sum -= map[i];
				break;
			case "*":
				sum *= map[i];
				break;
			default:
				sum /= map[i];
				break;
			}
		}

		return sum;
	}
}