package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QUANTIZE {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	// 각각 수열 A의 크기와 사용할 숫자의 수(1~10)
	private static int N, PARTS;
	
	// 양자화해야 할 수열
	private static int[] A;
	
	// A[]의 부분합을 저장한다.
	// ex) pSum[i] = A[0] + ... + A[i]
	private static int[] pSum;
	
	// A[]의 제곱의 부분합을 저장한다.
	// ex) pSqSum[i] = A[0]^2 + ... + A[i]^2
	private static int[] pSqSum;
	
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			PARTS = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			pSum = new int[N];
			pSqSum = new int[N];
			cache = new int[101][101];
			
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				A[y] = Integer.parseInt(st.nextToken());
			}
			preCalc();
			sb.append(solve(0, PARTS)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int from, int parts) {
		// 기저 사례(1): 모든 숫자를 다 양자화 했을 때
		if(from==N) return 0;
		
		// 기저 사례(2): 숫자는 아직 남았는데 더 묶을 수 없을 때 아주 큰 값을 반환
		if(parts==0) return 9999999;
		
		if(cache[from][parts] != 0) return cache[from][parts];
		
		int ret = 9999999;
		
		// 조각의 길이를 변화시켜 가며 최소치를 찾는다.
		for(int partSize=1; from+partSize<=N; partSize++) {
			cache[from][parts] = ret = Math.min(ret, minError(from, from+partSize-1)+solve(from+partSize, parts-1));
		}
		return ret;
	}
	
	// 오차 제곱의 합을 반환하는 메서드
	private static int minError(int lo, int hi) {
		int sum = pSum[hi] - (lo==0 ? 0:pSum[lo-1]);
		int sqSum = pSqSum[hi] - (lo==0 ? 0:pSqSum[lo-1]);
		
		int m = Math.round((float)sum / (hi - lo +1));
		return m*m* (hi-lo+1)- 2*m*(sum) + sqSum;
	}
	
	// 정렬, 수열 A에 대한 합, 제곱의 합을 구하는 메서드
	private static void preCalc() {
		Arrays.sort(A);
		pSum[0] = A[0];
		pSqSum[0] = A[0]*A[0];
		for(int i=1; i<N; i++) {
			pSum[i] = pSum[i-1] + A[i];
			pSqSum[i] = pSqSum[i-1]+A[i]*A[i];
		}
	}
}