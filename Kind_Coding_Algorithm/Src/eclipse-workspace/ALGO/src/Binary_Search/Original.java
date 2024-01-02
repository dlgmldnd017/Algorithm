package Binary_Search;

public class Original {
	public static void main(String[] args) {
		int[] A = {1,2,3,4,5,6,7,8,9};
		
		int result = binSearch(A, 3);
		System.out.println("위치는 " + result + " 입니다.");
	}
	
	static int binSearch(int[] A, int x) {
		int n = A.length;
		int lo = -1, hi = n;
		
		while(lo+1 <hi) {
			int mid = (lo+hi)/2;
			
			if(A[mid] < x) {
				lo = mid;
			}else {
				hi = mid;
			}
		}
		return hi;
	}
}