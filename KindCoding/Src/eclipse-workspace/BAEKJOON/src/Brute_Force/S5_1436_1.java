package Brute_Force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_1436_1 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(sc.readLine());
		solve(N);
		
		System.out.println(sb);
	}
	
	private static void solve(int n) {
		int num = 666;
		int count = 1;
		 
		while(count != n) {
			num++;
		    
			// int형인 num을 String으로 변환한 뒤, "666"이란 문자열이 있는지 검사
			if(String.valueOf(num).contains("666")) { 
				count++;
			}
		}
		System.out.print(num);
	}
}