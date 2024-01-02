package Full_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOARDCOVER {
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	// 4가지 타입의 시작 블록이며, 상대 좌표(y,x)를 알려준다.
	private static int[][][] coverType = {
				{{0,0},{1,0},{0,1}}, //┌
				{{0,0},{0,1},{1,1}}, //┐
				{{0,0},{1,0},{1,1}}, //└
				{{0,0},{1,0},{1,-1}} //┘
	};
	
	// 각각 높이, 넓이, 정답 수, 흰칸의 수를 의미한다.
	private static int H,W, ans, wc;
	
	// 보드판
	private static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(sc.readLine());
		
		
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(sc.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 흰색 칸의 수와 보드 초기화
			wc=0;
			board = new int[H][W];
			
			// board판에 흰색과 검은색을 채워 넣는 과정
			for(int y=0; y<H; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<W; x++) {
					String tmp = st.nextToken();
					
					// 흰색 칸은 0, 검은색 칸은 1
					board[y][x] = tmp.equals(".") ? 0:1;
					
					if(board[y][x]==0) {
						wc++;
					}
				}
			}
			
			// solve()를 호출하는 과정
			// 3의 배수라면 호출
			// 아니면 0 출력
			ans=0;
			if(wc%3==0) {
				solve(wc);
			}
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
	
	// 모든 경우의 수를 구해보는 핵심 메서드
	private static void solve(int WC) {
		
		// 재귀에서 흰색 칸의 수가 0이라면
		// 정답 카운트 ++
		if(WC==0) { 
			ans++;
			return;
		}
		
		for(int y=0; y<H; y++) {
			for(int x=0; x<W; x++) {
				if(board[y][x]==0) {
					for(int k=0; k<4; k++) {
						if(check(k, y, x)) {
							set(k, y, x, 1);
							solve(WC-3);
							set(k,y,x,0);
						}
					}
					return;
				}
			}
		}
	}
	
	// coverType 4가지가 보드판에 맞는지 확인하는 메서드
	private static boolean check(int k, int y, int x) {
		for(int i=0; i<3; i++) {
			int ny = y + coverType[k][i][0];
			int nx = x + coverType[k][i][1];
			
			if(!inRange(ny, nx) || board[ny][nx]==1) {
				return false;
			}
		}
		return true;
	}
	
	// 보드판 범위에 포함하는 지 확인하는 메서드
	private static boolean inRange(int ny, int nx) {
		return (ny>=0 && ny<H) && (nx>=0 && nx<W);
	}
	
	// 보드판에 흰색(0) 또는 검은색(1)으로 설정하는 메서드
	private static void set(int k, int y, int x, int c) {
		for(int i=0; i<3; i++) {
			int ny = y + coverType[k][i][0];
			int nx = x + coverType[k][i][1];
			board[ny][nx] = c;
		}
	}
}