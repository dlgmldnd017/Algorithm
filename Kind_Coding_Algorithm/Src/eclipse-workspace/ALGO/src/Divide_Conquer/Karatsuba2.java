package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Karatsuba2 {
private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		String str1 = sc.readLine();
		String str2 = sc.readLine();
		
		ArrayList<Integer> a = toArrayList(str1);
		ArrayList<Integer> b = toArrayList(str2);
		
		ArrayList<Integer> c = karatsuba(a, b);
		for(int i=c.size()-1; i>=0; i--) {
			System.out.print(c.get(i));
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
	
	// 큰 두 정수를 분할 정복으로 곱하여 반환한다.
	public static ArrayList<Integer> karatsuba(ArrayList<Integer> a, ArrayList<Integer> b){
		int an = a.size(), bn = b.size();
		
		// a가 b보다 짧을 경우 둘을 바꾼다.
		if(an<bn) return karatsuba(b, a);
		
		// 기저 사례(1): a나 b가 비어있는 경우
		if(an==0 || bn==0) return new ArrayList<Integer>();
		
		// 기저 사례(2): a가 비교적 짧은 경우 O(N^2) 곱셈으로 변경한다.
		if(an<=50) return multiply(a,b);
		int half = an/2;
		
		// a와 b를 밑에서 half 자리와 나머지로 분리한다.
		ArrayList<Integer> x = new ArrayList<Integer>(a.subList(0, half));
		ArrayList<Integer> y = new ArrayList<Integer>(a.subList(half, an));
		ArrayList<Integer> w = new ArrayList<Integer>(b.subList(0, Math.min(bn,half)));
		ArrayList<Integer> z = new ArrayList<Integer>(b.subList(Math.min(bn, half), bn));
		
		// z2 = a1*b1
		ArrayList<Integer> z2 = karatsuba(y, w);
		
		// z0 = a0*b0
		ArrayList<Integer> z0 = karatsuba(x, w);
		z0 = addTo(x, z, 0);
		z0 = addTo(w, z, 0);
		
		// z1 = (a0*b0) - z0 - z2
		ArrayList<Integer> z1 = karatsuba(x, w);
		z1 = subFrom(z1, z0);
		z1 = subFrom(z1, z2);
		
		// ret = z0 + z1*10^half + z2*10^(half*2)
		ArrayList<Integer> ret = new ArrayList<>();
		ret = addTo(ret, z0, 0);
		ret = addTo(ret, z1, half);
		ret = addTo(ret, z2, half+half);
		
		return ret;
	}
	
	// a += b*(10^k)를 구현한다.
	private static ArrayList<Integer> addTo(ArrayList<Integer> a, ArrayList<Integer> b, int k){
		a = ensureSize(a, Math.max(a.size(), b.size()+k));
		
		for(int i=0; i<b.size(); i++) {
			a.set(i+k, a.get(i+k) + b.get(i));
		}
		a=normalize(a);
		return a;
	}
	
	// a -= b를 구현한다. a >= b를 가정한다.
	private static ArrayList<Integer> subFrom(ArrayList<Integer> a, ArrayList<Integer> b){
		a = ensureSize(a, Math.max(a.size(), b.size()+1));

		for(int i=0; i<b.size(); i++) {
			a.set(i, a.get(i) - b.get(i));
		}
		a=normalize(a);
		return a;
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
		
	// 입력받은 c를 size만큼 용량을 높이고 size를 올린다.
	private static ArrayList<Integer> ensureSize(ArrayList<Integer> c, int size){
		c.ensureCapacity(size);
		while(c.size()<size) {
			c.add(0);
		}
		return c;
	}
}