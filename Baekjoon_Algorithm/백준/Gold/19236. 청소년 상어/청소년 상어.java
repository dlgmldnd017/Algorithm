import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Fish{
	int y, x, dir;
	boolean death;
	public Fish(int y, int x, int dir, boolean death) {
		this.y = y;
		this.x = x;
		this.dir = dir;
		this.death = death;
	}
}

public class Main {
	static int N=4, ans;
	static int map[][];
	
	static Map<Integer, Fish> dir;
	
	static int dy[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int dx[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		dir = new HashMap<Integer, Fish>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			for(int j=0; j<N; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				int dirNum = Integer.parseInt(st.nextToken());
				
				map[i][j] = fishNum;
				dir.put(fishNum, new Fish(i, j, dirNum, false));
			}
		}

		solve(0, 0, 0, new HashMap<Integer, Fish>(dir), map);
		System.out.println(ans);
	}

	static void solve(int y, int x, int sum, Map<Integer, Fish> copy1, int[][] copy2) {
		if(!inRange(y, x) || map[y][x]==0) {
			ans = Math.max(ans, sum);
			return;
		}
		
		int fishNum = map[y][x];
		int fishDir = dir.get(fishNum).dir;
		
		map[y][x]=0;
		dir.get(fishNum).death=true;
		sum += fishNum;
		
		moveFish(y, x);
		
		copy1 = new HashMap<Integer, Fish>(dir);
		copy2 = copyArr(map);
		
		for(int i=1; i<=N; i++) {
			int ny = y+dy[fishDir]*i;
			int nx = x+dx[fishDir]*i;
			
			solve(ny, nx, sum, dir, map);
			dir = new HashMap<Integer, Fish>(copy1);
			map = copyArr(copy2);
		}
		dir.get(fishNum).death=false;
	}
	
	static int[][] copyArr(int[][] original) {
		int tmp[][] = new int[N][N];
		
		for(int i=0; i<N; i++) {
			tmp[i] = original[i].clone();
		}
		return tmp;
	}

	public static void moveFish(int r, int c) {
        for(int i=1; i<=16; i++) {
            if(dir.get(i).death) continue;

            // 현재 물고기 위치 받아오기
            int num = dir.get(i).dir;
            int y = dir.get(i).y;
            int x = dir.get(i).x;

            // 현재 위치에서 반시계로 돌기
            for(int k=1; k<=8; k++, num++) {
                if(num==9) num=1;

                int ny = y+dy[num];
                int nx = x+dx[num];

                // 범위를 벗어나거나 상어 위치라면 못가니깐 다시 다른 방향 탐색
                if(!inRange(ny, nx) || (ny==r&&nx==c)) continue;
                
                // 바라보는 방향에 물고기가 있다면 위치 변경하기
                if(map[ny][nx]!=0) {
                    int key = map[ny][nx];
                    map[ny][nx] = map[y][x];
                    map[y][x] = key;
                    dir.put(i, new Fish(ny, nx, num, false));
                    dir.put(key, new Fish(y, x, dir.get(key).dir, false));
                    break;
                }

                map[ny][nx] = map[y][x];
                map[y][x] = 0;
                dir.put(i, new Fish(ny, nx, num, false));
                break;
            }
        }
    }
	
	static boolean inRange(int y, int x) {
		return (y>=0&&y<N) && (x>=0&&x<N);
	}
}