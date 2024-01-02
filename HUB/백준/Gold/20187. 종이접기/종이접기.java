import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k, h, ans;
	static int W, H, R, C;

	static int[][] map, copy;
	static String[] dir;

	static int[] dy = {2, 3, 0, 1};
	static int[] dx = {1, 0, 3, 2};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(sc.readLine());

		dir = new String[2*k];
		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<2*k; i++) {
			dir[i] = st.nextToken();
		}

		h = Integer.parseInt(sc.readLine());

		W = 1<<k;
		H = 1<<k;
		
		map = new int[W][H];
		copy = new int[2][2];

		solve();
		
		for(int[] i:map) {
			for(int j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}

	static void solve() {
		for(int i=0; i<2*k; i++) {
			switch (dir[i]) {
			case "L":
				W/=2;
				break;
			case "R":
				W/=2;
				C+=W;
				break;
			case "D":
				H/=2;
				R+=H;
				break;
			case "U":
				H/=2;
				break;
			}
		}
		R%=2;
		C%=2;
		copy[R][C]=h;
		LRrotate();
	}

	static void LRrotate() {
		if(C%2==0) {
			copy[R][C+1] = dx[copy[R][C]];
			UDrotate(1);
		}else {
			copy[R][C-1] = dx[copy[R][C]];
			UDrotate(-1);
		}
		copy();
	}

	static void UDrotate(int tmp) {
		if(R%2==0) {
			copy[R+1][C] = dy[copy[R][C]];
			copy[R+1][C+tmp] = dy[copy[R][C+tmp]];
		}else{
			copy[R-1][C] = dy[copy[R][C]];
			copy[R-1][C+tmp] = dy[copy[R][C+tmp]];
		}
	}

	static void copy() {
		for(int i=0; i<2*k; i+=2) {
			for(int j=0; j<2*k; j+=2) {
				for(int a=0; a<2; a++) {
					for(int b=0; b<2; b++) {
						map[i+a][j+b] = copy[a][b];
					}
				}
			}
		}
	}
}