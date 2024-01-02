package Divide_Conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {
	// 원본과 임시 복사본을 위한 변수
	private static int[] src, tmp;
	
	// 출력을 위한 변수
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C = Integer.parseInt(sc.readLine());
		for(int i=0; i<C; i++) {
			int N = Integer.parseInt(sc.readLine());
			src = new int[N];
			tmp = new int[N];
			
			// 입력 값 초기화 과정
			st = new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				src[y]=Integer.parseInt(st.nextToken());
			}
			
			// 메인 메서드 호출
			mergeSort(0, src.length-1);
			/// 결과값 쓰기
			writeArray(src);
		}
		System.out.println(sb);
	}
	
	// 병합 정렬하는 주요 메서드
	private static void mergeSort(int start, int end) {
		// 기저사례 (1): 더 이상 쪼개지지 않을 경우
		if(start>=end) return;
		
		// mid를 구한 후 병합 정렬
		int mid=(start+end)/2;
		
		// mid를 기준으로 오른쪽
		mergeSort(start, mid);
		// mid를 기준으로 오른쪽
		mergeSort(mid+1, end);
		
		int p=start; int q = mid+1;
		int idx=start;
		
		// 다시 합치는 동시에 정렬 수행
		while(p<=mid || q<=end) {
			if(q>end || (p<=mid && src[p]<src[q])) {
				tmp[idx++] = src[p++];
			}else {
				tmp[idx++] = src[q++];
			}
		}
		
		// 임시 복사본을 원본에 다시 옮김.
		for(int i=start; i<end+1; i++) {
			src[i] = tmp[i];
		}
	}
	
	private static void writeArray(int[] A) {
		for(int i:A) {
			sb.append(i+" ");
		}
		sb.append("\n");
	}
}