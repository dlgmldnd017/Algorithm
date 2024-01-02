import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R, ans;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<R; i++) {
			solve(Integer.parseInt(st.nextToken()));
		}
		
		for(int[] i:map) {
			for(int j:i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	public static void solve(int select) {
		switch (select) {
		case 1:
			solve1();
			return;
		case 2:
			solve2();
			return;
		case 3:
			solve3();
			return;
		case 4:
			solve4();
			return;
		case 5:
			solve5();
			return;
		case 6:
			solve6();
			return;
		}
	}

	public static void solve1(){
		int[][] tmp = new int[map.length][map[0].length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				tmp[i][j] = map[map.length-1-i][j];
			}
		}

		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	public static void solve2(){
		Stack<Integer> stack = new Stack<>();

		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				stack.push(map[i][j]);
			}
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = stack.pop();
			}
		}
	}

	public static void solve3(){
		int[][] tmp = new int[map[0].length][map.length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				tmp[j][map.length-1-i] = map[i][j];
			}
		}

		map = new int[map[0].length][map.length];
		for(int i=0; i<tmp.length; i++) {
			for(int j=0; j<tmp[0].length; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	public static void solve4(){
		int[][] tmp = new int[map[0].length][map.length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				tmp[j][i] = map[i][map[0].length-1-j];
			}
		}

		map = new int[map[0].length][map.length];
		for(int i=0; i<tmp.length; i++) {
			for(int j=0; j<tmp[0].length; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	public static void solve5(){
		int[][] tmp = new int[map.length][map[0].length];

		tmp = copy(0, 0, 0, map[0].length/2, tmp);
		tmp = copy(0, map[0].length/2, map.length/2, map[0].length/2, tmp);
		tmp = copy(map.length/2, map[0].length/2, map.length/2, 0, tmp);
		tmp = copy(map.length/2, 0, 0, 0, tmp);

		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	public static void solve6(){
		int[][] tmp = new int[map.length][map[0].length];
		tmp = copy(0, 0, map.length/2, 0, tmp);
		tmp = copy(map.length/2, 0, map.length/2, map[0].length/2, tmp);
		tmp = copy(map.length/2, map[0].length/2, 0, map[0].length/2, tmp);
		tmp = copy(0, map[0].length/2, 0, 0, tmp);

		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	public static int[][] copy(int sy, int sx, int ay, int ax, int[][] tmp) {
		for(int i=0; i<map.length/2; i++) {
			for(int j=0; j<map[0].length/2; j++) {
				tmp[ay+i][ax+j] = map[sy+i][sx+j];			
			}
		}
		return tmp;
	}
}