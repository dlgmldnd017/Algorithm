import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, M, K, ans;
	static int[] perm;
	static int[][] map, tmp, rotate, original;
	
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[K];
		
		map = new int[N+1][M+1];
		original = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(sc.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				original[i][j] = map[i][j];
			}
		}
		
		ans = Integer.MAX_VALUE;
		rotate = new int[K][3];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(sc.readLine());
			rotate[i][0] = Integer.parseInt(st.nextToken());
			rotate[i][1] = Integer.parseInt(st.nextToken());
			rotate[i][2] = Integer.parseInt(st.nextToken());
		}
		
		perm = new int[K];
		searchPerm(0);
		System.out.println(ans);
	}

	public static void solve(int y, int x, int k) {
		int R=y-k, C=x-k, print=k*2, t=1;
		tmp = new int[N+1][M+1];
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<print; j++) {
				int temp = map[R][C];
				C += t;
				tmp[R][C] = temp;
			}
			
			for(int j=0; j<print; j++) {
				int temp = map[R][C];
				R += t;
				tmp[R][C] = temp;
			}
			t *= (-1);
		}
		copyMap(R, C);
	}
	
	public static void copyMap(int R, int C) {
		for(int i=R; i<=N; i++) {
			for(int j=C; j<=M; j++) {
				if(tmp[i][j]!=0) map[i][j] = tmp[i][j];
			}
		}
	}
	
	public static void calc() {
		for(int i=1; i<=N; i++) {
			int sum=0;
			for(int j=1; j<=M; j++) {
				sum += map[i][j];
				map[i][j] = original[i][j];
			}
			ans = Math.min(ans, sum);
		}
	}
	
	static void searchPerm(int depth) {
        if(depth==K) {
            for(int i=0; i<depth; i++) {
        		int y = rotate[perm[i]][0];
        		int x = rotate[perm[i]][1];
        		int k = rotate[perm[i]][2];
        		
        		int z=k;
        		for(int j=0; j<k; j++) {
        			solve(y, x, z);
        			z--;
        		}
            }
            calc();
            return;
        }
        
        for(int i=0; i<K; i++) {
            if(!visited[i]) {
            	visited[i]=true;
            	perm[depth] = i;
                searchPerm(depth+1);
                visited[i]=false;
            }
        }
    }
}