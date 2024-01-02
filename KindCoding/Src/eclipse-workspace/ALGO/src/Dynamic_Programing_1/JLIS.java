package Dynamic_Programing_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JLIS {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int N,M;
	private static int[] A, B;
	
	private static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		int C = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<C; i++) {
			cache = new int[101][101];
			
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N];
			M =  Integer.parseInt(st.nextToken());
			B = new int[M];
			
			st = new StringTokenizer(sc.readLine());
			for(int j=0; j<N; j++) {
				A[j] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(sc.readLine());
			for(int j=0; j<M; j++) {
				B[j] = Integer.parseInt(st.nextToken());
			}
			sb.append(solve(-1, -1)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(int indexA, int indexB) {
		// 캐시 확인
		if(cache[indexA+1][indexB+1] != 0) {
			System.out.println("cache hit!! "+(indexA+1)+":"+(indexB+1)+"="+cache[indexA+1][indexB+1]);
			return cache[indexA+1][indexB+1];
		}
		
		// 어떤 원소이든 재귀호출을 무조건 1번 이상
		// 수행하므로 ret값은 0으로 초기화 한다.
		int ret=0;
		
		// -1을 처음부터 넣어서 모두 비교를 할 수 있게 한다.
		// 그러기 위해서는 아래와 같은 초기값을 위한 long max가 필요하다.
		long a = (indexA==-1 ? -999:A[indexA]);
		long b = (indexB==-1 ? -999:B[indexB]);
		long max = Math.max(a, b);
		
		// 어떤 수열이 먼저와도 상관없다.
		// 여기서는 A수열 비교
		for(int nextA=indexA+1; nextA<N; nextA++) {
			if(max<A[nextA]) cache[indexA+1][indexB+1] = ret = Math.max(ret, solve(nextA, indexB)+1);
		}
		
		// 어떤 수열이 먼저와도 상관없다.
		// 여기서는 B수열 비교
		for(int nextB=indexB+1; nextB<M; nextB++) {
			if(max<B[nextB]) cache[indexA+1][indexB+1] = ret = Math.max(ret, solve(indexA, nextB)+1);
		}
		return ret;
	}
}