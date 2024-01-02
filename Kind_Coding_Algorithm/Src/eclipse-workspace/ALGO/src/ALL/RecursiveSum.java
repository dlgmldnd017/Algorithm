package ALL;

public class RecursiveSum {
	public static void main(String[] args) {
		int ret = recursiveSum(6);
		System.out.println(ret);
	}
	
	private static int recursiveSum(int n) {
		if(n==1) return 1;
		return n + recursiveSum(n-1);
	}
}