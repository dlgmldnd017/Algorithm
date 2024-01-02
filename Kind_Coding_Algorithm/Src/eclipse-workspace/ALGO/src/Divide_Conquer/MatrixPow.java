package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MatrixPow {
	// 홀수를 위한 복사 행렬과 행령의 크기 
	private static int[][] Matrix;
	private static int N;
	
	// 출력을 위한 변수
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C = Integer.parseInt(sc.readLine());
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int[][] A = new int[N][N];
			
			// 행렬에 값을 넣는 과정
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					A[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			// 홀수가 나오면 사용하는 행렬
			// 예) 2^7 = (2^6)*MATRIX
			Matrix = A;
			
			// 주요 메서드 실행과 동시에 값 저장
			writeArr(solve(A, B));
		}
		System.out.println(sb);
	}
	
	// 거듭제곱 행렬을 수행하기 위해
	// 분할 정복하는 주요 메서드
	private static int[][] solve(int[][] a, int b) {
		
		// 기저사례(1): 지수가 1이라면 바로 행렬 리턴
		if(b==1) return a;
		
		// 분할 시작
		int[][] ret = solve(a, b/2);
		
		// 행렬 곱 수행
		ret = multiplyMatrix(ret, ret);
		
		// 홀수라면 아까 위해서 말했던 Matrix 곱
		if(b%2==1) ret = multiplyMatrix(ret, Matrix);
		
		return ret;
	}
	
	// a와 b 행렬을 서로 곱셈을 수행하는 메서드
	private static int[][] multiplyMatrix(int[][] a, int[][] b){
		int[][] ret = new int[N][N];
		
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					ret[i][j]+=a[i][k]*b[k][j];
				}
			}
		}
		return ret;
	}
	
	// 거듭제곱의 결과를 %1000으로 수행하여 출력하는 메서드
	private static void writeArr(int[][] a) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append((a[i][j]%1000) +" ");
			}
			sb.append("\n");
		}
		sb.append("\n");
	}
}