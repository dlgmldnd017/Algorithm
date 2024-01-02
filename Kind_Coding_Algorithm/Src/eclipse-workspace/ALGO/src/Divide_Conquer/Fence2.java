package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fence2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st ;
	private static StringBuilder builder = new StringBuilder();
	private static int[] h;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int c = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<c; i++) {
			int N = Integer.parseInt(sc.readLine());
			h = null;
			h = new int[N];
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				h[j] = Integer.parseInt(st.nextToken());
			}
			builder.append(solve(0, N-1) + "\n");
		}
		System.out.println(builder);
	}
	
	private static int solve(int left, int right) {
		// 기저사례: 판자가 하나일 경우 그대로 값 리턴
		if(left == right) return h[left];
		
		// 주어진 원소를 중간으로 나누고
		// 초기에 중간을 기준으로 왼쪽과 오른쪽으로 계속 분할
		int mid = (left+right) / 2;
		int ret = Math.max(solve(left, mid), solve(mid+1,right));
		
		// 중간을 기준으로 양쪽에 걸쳐서 존재하는
		// 직사각형을 위해 분할
		int lo=mid, hi=mid+1;
		int height = Math.min(h[lo], h[hi]);
		
		// height의 값은 양쪽에서 걸쳐서 존재하기에
		// 직사각형을 그리기 때문에 *2 필요
		ret = Math.max(ret, height*2);
		
		// 먼저, lo와 hi가 left, right 부분으로 옮길 수 없다면, 종료
		while(lo<left || hi<right) {
			
			// h[lo-1]<h[hi+1]이라면, hi를 옮김
			// 아니라면 lo를 옮김
			if(hi<right && (lo==left || h[lo-1] < h[hi+1])) {;
				height = Math.min(height, h[++hi]);
			}else {
				height = Math.min(height, h[--lo]);
			}
			
			// 최종적으로 값 계산
			ret = Math.max(ret, height * (hi-lo+1));
		}
		return ret;
	}
}
