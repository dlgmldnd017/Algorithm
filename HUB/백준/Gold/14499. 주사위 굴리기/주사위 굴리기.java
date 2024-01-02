import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, y, x, K, ans;
	static int width[], height[], map[][];

	static List<Integer> order = new ArrayList<>();

	static int dy[] = {0, 0, -1, 1};
	static int dx[] = {1, -1, 0, 0};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(sc.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());

			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		width = new int[3];
		height = new int[4];

		st = new StringTokenizer(sc.readLine());
		for(int i=0; i<K; i++) {
			ans = solve(Integer.parseInt(st.nextToken())-1);
			if(ans==-1) continue;
			sb.append(ans+"\n");
		}

		System.out.println(sb);
	}

	static int solve(int dir) {
		// 현재 위치 업데이트
		y += dy[dir];
		x += dx[dir];

		// 만약 범위를 벗어나면 명령 무시함.
		if(!inRange(y, x)) {
			y -= dy[dir];
			x -= dx[dir];
			return -1;
		}
		
		// 주사위 돌리기
		rotateDice(dir);
				
		// (1) 현재 위치에 값이 있는지 확인
		if(map[y][x]!=0) {
			height[3] = map[y][x];
			map[y][x]=0;
		}
		else {
			map[y][x] = height[3];
		}
		
		// 윗면 리턴
		return width[1];
	}

	static void rotateDice(int dir){
		int copyHeight[] = height.clone();
		int copyWidth[] = width.clone();
		
		switch(dir) {
		// 동쪽
		case 0:
			copyWidth[0] = height[3];
			copyHeight[3] = width[2];
			copyHeight[1] = width[0];
			copyWidth[1] = width[0];
			copyWidth[2] = width[1];
			break;

		// 서쪽
		case 1:
			copyWidth[0] = width[1];
			copyWidth[1] = width[2];
			copyWidth[2] = height[3];
			copyHeight[3] = width[0];
			copyHeight[1] = width[2];
			break;

		// 북쪽
		case 2:
			copyHeight[0] = height[3];
			copyHeight[1] = height[0];
			copyWidth[1] = height[0];
			copyHeight[2] = height[1];
			copyHeight[3] = height[2];
			break;

		// 남쪽
		case 3:
			copyHeight[0] = height[1];
			copyHeight[1] = height[2];
			copyWidth[1] = height[2];
			copyHeight[2] = height[3];
			copyHeight[3] = height[0];
			break;
		}
		
		height = copyHeight.clone();
		width = copyWidth.clone();
	}

	static boolean inRange(int i, int j) {
		return (i>=0&&i<N) && (j>=0&&j<M);
	}
}