package ALL;

import java.util.Scanner;

public class AllSearchCases {
	static int k=1;
	
	static void restart(String t, int A, int B) {
        if (0 < A) restart(t + "A", A - 1, B);
        if (0 < B) restart(t + "B", A, B - 1);

        if (A == 0 && B == 0) System.out.println(k++ +": " + t);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();

        restart("", A, B);
    }
}