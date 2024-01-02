package Brute_Force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_1436_2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(sc.readLine());
		solve(N);
	}
	
	public static void solve(int n) {
		int count = 1;
		int digit = 0; 
		int num = 0;	
		
		while(true) {
			if (digit % 1000 == 666) {
				for (int i = 0; i < 1000; i++) {
					if (count == n) {
						System.out.print(digit * 1000 + i);
						return;
					}
					count++;
					num++;
				}
				digit++;
				
			}else if (digit % 100 == 66) {
				num = 600;
				for (int i = 0; i < 100; i++) {
					if (count == n) {
						System.out.print(digit * 1000 + num);
						return;
					}
					count++;
					num++;
				}
				digit++;
				
			}else if (digit % 10 == 6) {
				num = 660;
				for (int i = 0; i < 10; i++) {
					if (count == n) {
						System.out.print(digit * 1000 + num);
						return;
					}
					num++;
					count++;
				}
				digit++;
				
			}else {
				num = 666;
				if (count == n) {
					System.out.print(digit * 1000 + num);
					return;
				}
				count++;
				digit++;
			}
		}
	}
}