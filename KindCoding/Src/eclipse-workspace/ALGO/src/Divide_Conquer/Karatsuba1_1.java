package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Karatsuba1_1 {
	// 출력을 위한 변수
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int C = Integer.parseInt(sc.readLine());
		for(int i=0; i<C; i++) {
			String strA = sc.readLine();
			String strB = sc.readLine();
			
			int[] A = new int[strA.length()];
			int[] B = new int[strB.length()];
			
			A = initArr(A, strA);
			B = initArr(B, strB);
			
			sb.append(solve(A,B)+"\n");
		}
		System.out.println(sb);
	}
	
	// 큰 두 정수 곱을 수행하는
	// 메서드
	private static int solve(int[] a, int[] b) {
		int ret=0;
		
		int[] c = new int[a.length+b.length];
		c[0] = 0;
		
		for(int y=1; y<=b.length; y++) {
			for(int x=0; x<a.length; x++) {
				c[y+x]+=a[x]*b[y-1];
			}
		}
		c = normalize(c);
		printArr(c);
		return ret;
	}
	
	// 반올림을 수행하는 메서드
	private static int[] normalize(int[] c) {
		for(int i=c.length-1; i>=1; i--) {
			int a=c[i]/10;
			c[i] %= 10;
			c[i-1] += a;
			
			if(i==0 && a!=0){
				c[0] = a;
			}
		}
		printArr(c);
		return c;
	}
	
	// 입력받은 배열값 초기화를
	// 수행하는 메서드
	private static int[] initArr(int[] arr, String str) {
		for(int i=0; i<str.length(); i++) {
			arr[i] = str.charAt(i)-'0';
		}
		return arr;
	}
	
	// 출력하는 메서드
	private static void printArr(int[] c) {		
		int[] b;
		
		// 만약 c[0]의 값이 올림받은 값이
		// 아니라면 삭제
		if(c[0]==0) {
			b=new int[c.length-1];
			for(int i=0; i<b.length; i++) {
				b[i] = c[i+1];
			}
		}else {
			b=c;
		}
		for(int i:b) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}