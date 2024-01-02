package Divide_Conquer;

public class nmSum {
	public static void main(String[] args) {
		System.out.println("n이 10일 때 " + fastSum(10));
	}
	
	private static int fastSum(int n) {
		if(n==1) return 1;
		if(n%2==1) return fastSum(n-1) + n;
		return 2*fastSum(n/2) + (n/2)*(n/2);
	}
}
