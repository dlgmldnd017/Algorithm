import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, ans;
	static boolean[][] map;

	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(sc.readLine());
		map = new boolean[100][100];
		
		int sum=0;
		for(int k=0; k<N; k++) {
			st = new StringTokenizer(sc.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			
			for(int i=y; i<y+10; i++) {
				for(int j=x; j<x+10; j++) {
					if(!map[i][j]) {
						map[i][j]=true;
						sum++;
					}
				}
			}
		}
		System.out.println(sum);
	}
}
