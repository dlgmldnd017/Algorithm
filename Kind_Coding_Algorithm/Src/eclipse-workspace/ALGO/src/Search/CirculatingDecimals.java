package Search;

public class CirculatingDecimals {

	public static void main(String[] args) {
		printDecimal(3,8);
		printDecimal(4712,400);
	}
	
	public static void printDecimal(int a, int b) {
		int i = 0;
		
		while(a>0) {
			if(i++ == 1) System.out.print(".");
			System.out.print(a/b);
			a = (a%b)*10;
		}
		System.out.println();
	}
}