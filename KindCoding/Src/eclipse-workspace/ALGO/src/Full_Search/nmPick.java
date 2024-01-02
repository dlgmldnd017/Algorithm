package Full_Search;

import java.util.Stack;

public class nmPick {
	public static void main(String[] args) {
		printPick(7, new Stack<>(), 4);
	}

	public static void printPick(int n, Stack<Integer> A, int toPick) {
		if(toPick==0) {
			printStack(A);
			return;
		}
		
		int smallest = A.empty()? 0 : A.peek()+1;
		
		for(int next = smallest; next<n; next++) {
			A.push(next);
			printPick(n, A, toPick-1);
			A.pop();
		}
	}
	
	public static void printStack(Stack<Integer> A) {
		for(int i=0; i<A.size(); i++) {
			System.out.print(A.get(i) + " ");
		}
		System.out.println();
	}
}