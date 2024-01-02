package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Karatsuba1_2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		String str1 = sc.readLine();
		String str2 = sc.readLine();
		
		ArrayList<Integer> a = toArrayList(str1);
		ArrayList<Integer> b = toArrayList(str2);
		
		ArrayList<Integer> c = multiply(a, b);
		for(int i=c.size()-1; i>=0; i--) {
			System.out.print(c.get(i) +" ");
		}
	}
	
	// ArrayList<Integer>로 변환하며
	// 123이 넘어 온다면, 321로 변환해줌.
	private static ArrayList<Integer> toArrayList(String str) throws IOException{
		ArrayList<Integer> c = new ArrayList<>();
		
		for(int i=str.length()-1; i>=0; i--) {
			c.add(str.charAt(i)-'0');
		}
		return c;
	}
	
	// 자릿수 올림을 처리하는 메서드
	private static ArrayList<Integer> normalize(ArrayList<Integer> num) {
		
		// 마지막 자리 수가 올림을 할 경우를 대비하여 0 삽입
		// ex) 9 14 -> 1 0 4 (여기서 마지막 자리수는 9 왼쪽을 의미함)
		num.add(0);
		
		for(int i=0; i<num.size()-1; i++) {
			
			// 음수일 경우
			if(num.get(i)<0) {
				int borrow = (Math.abs(num.get(i))+9)/10;
				num.set(i+1, num.get(i+1) - borrow);
				num.set(i, num.get(i) + borrow*10);
			}else { // 양수일 경우
				num.set(i+1, num.get(i+1) + num.get(i)/10);
				num.set(i, num.get(i)%10);
			}
		}
		
		// 마지막 자리수가 0 그대로라면 0을 삭제
		while(num.size()>1 && num.get(num.size()-1)==0) num.remove(num.size()-1);
		
		return num;
	}
	
	// 두 큰 정수를 삭제하는 메서드
	// 시간 복잡도: O(N^2)
	private static ArrayList<Integer> multiply(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> c = new ArrayList<>();
		
		// 각 size를 알기 위해 0을 a,b 사이즈만큼 삽입
		for(int i=0; i<a.size()+b.size(); i++) {
			c.add(0);
		}
		
		// 계산 수행
		for(int i=0; i<a.size(); i++) {
			for(int j=0; j<b.size(); j++) {
				c.set(i+j, c.get(i+j) + a.get(i)*b.get(j));
			}
		}
		
		// 자릿수 올림하고 결과값 리턴
		c = normalize(c);
		return c;
	}
}