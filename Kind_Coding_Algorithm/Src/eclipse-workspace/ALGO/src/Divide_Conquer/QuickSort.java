package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {
	// 출력을 위한 변수
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C = Integer.parseInt(sc.readLine());
		for(int i=0; i<C; i++) {
			int N=Integer.parseInt(sc.readLine());
			int[] src=new int[N];
			
			
			// 입력 받은 값을 저장하는 과정
			st=new StringTokenizer(sc.readLine());
			for(int y=0; y<N; y++) {
				src[y]=Integer.parseInt(st.nextToken());
			}
			
			// 주요 메서드 호출
			quickSort(src, 0, N-1);
			// 값 출력을 위해 쓰기배열 호출
			writeArray(src);
		}
		System.out.println(sb);
	}
	
	// 분할하여 정복하는 퀵 정렬 (주요)메서드
	private static void quickSort(int[] src, int low, int high) {
		if(low>=high) return;
		
		// 미드의 기준 값을 구하기 위해 먼저 분할
		int mid = partition(src, low, high);
		// 왼쪽과 오른쪽 따로 분할 정복
		quickSort(src, low, mid-1);
		quickSort(src, mid, high);
	}
	
	// 분할 작업을 수행하는 메서드
	private static int partition(int[] src, int low, int high) {
		// 여기서는 가장 왼쪽을 피봇으로 기준을 한다.
		int pivot=src[low];
		
		while(low<=high) {	
			// 피봇 기준 작으면 왼쪽 아니면 스탑
			while(src[low]<pivot) low++;
			
			// 피봇 기준 크면 오른쪽 아니면 스탑
			while(src[high]>pivot) high--;
			
			// 값을 넣어주는 과정
			if(low<=high) {
				// swap() 수행하기
				int tmp=src[low];
				src[low]=src[high];
				src[high]=tmp;
				
				// swap() 수행 후 다음 칸 옮기기
				low++;
				high--;
			}
		}
		// 중간 값을 구하기 위해 low 리턴
		return low;
	}
	
	// 출력을 위한 메서드
	private static void writeArray(int[] A) {
		for(int i:A) {
			sb.append(i+" ");
		}
		sb.append("\n");
	}
}