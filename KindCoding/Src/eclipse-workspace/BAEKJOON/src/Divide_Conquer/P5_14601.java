package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_14601 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	private static int[][] A;
	private static int num, N, x, y;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(sc.readLine());
		N = (int)Math.pow(2, N);
		A = new int[N+1][N+1];
		
		st = new StringTokenizer(sc.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
			
		A[x][y] = -1;
			
		solve(1, 1, N);
		print();
	}
	
	private static void solve(int x, int y, int size) {
		
		// 초기 타일은 1번부터 시작
		// 그 후는 ++ 하여 타일 번호를 지정
		num++;
		
		// 1,2,3,4 사분면을 나누기 위해 /2를 수행
        int ns=size/2;
        
        // 각각 1, 3, 2, 4 사분면에 대해 타일을 둘 것인지
        // 확인하는 check() 메서드를 수행하는 과정
        if(check(x,y,ns)) A[x+ns-1][y+ns-1]=num;
        if(check(x+ns,y,ns)) A[x+ns][y+ns-1]=num;
        if(check(x,y+ns,ns)) A[x+ns-1][y+ns]=num;
        if(check(x+ns,y+ns,ns)) A[x+ns][y+ns]=num;
        
        // 기저 사례(1): 타일(size)를 더 나눌 수 없을 경우
        if(size==2) return;

        // 1,2,3,4 사분면으로 쪼개어
        // 재귀호출을 수행한다.
        solve(x,y,ns);
        solve(x+ns,y,ns);
        solve(x,y+ns,ns);
        solve(x+ns,y+ns,ns);
	}
	
	// 해당 위치에 타일을 놓을 수 있는지 확인하는 메서드
	public static boolean check(int x,int y,int size){
        for(int i=x; i<x+size; i++){
            for(int j=y; j<y+size; j++){
                if(A[i][j] != 0) return false;
            }
        }
        return true;
    }
	
	// 이중 배열을 출력하는 메서드
	public static void print(){
        for(int i=N;i>=1;i--){
            for(int j=1;j<=N;j++){
                System.out.printf(A[j][i]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}