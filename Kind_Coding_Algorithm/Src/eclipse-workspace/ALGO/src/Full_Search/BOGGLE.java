package Full_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOGGLE {
	private static final int[] dx = { -1, -1, -1,  1,  1,  1,  0,  0 };
	private static final int[] dy = { -1,  0,  1, -1,  0,  1, -1,  1 };
	
	// 5x5 보드판
	static char[][] board = new char[5][5];
	
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<5; i++) {
			String tmp = sc.readLine();
			for(int j=0; j<5; j++) {
				board[i][j] = tmp.charAt(j);
			}
		}
		
		System.out.println();
		int C = Integer.parseInt(sc.readLine());
		String[] checkWords = new String[C];
		System.out.println();

		for(int i=0; i<C; i++) {
			checkWords[i] = sc.readLine();
		}
		System.out.println();
		
		for(int n = 0; n < checkWords.length; ++n) {
			System.out.print(checkWords[n] + " ");
			boolean isWord = false;
			
			for(int i = 0; i < 5; ++i) {
				for(int j = 0; j < 5; ++j) {
					if(hasWord(i, j, checkWords[n])) {
						isWord = true;
						break;
					}
				}
				if(isWord) break;
			}
			System.out.println();
			printBoard();
			if(isWord) System.out.println("-------> YES\n");
			else  System.out.println("-------> NO\n");
		}
	}
	private static boolean hasWord(int y, int x, String word) {
	    
		// 기저사례 1 : 시작 위치가 밖이면 무조건 실패
		if(!inRange(y, x)) return false;
	    
		// 기저사례 2 : 첫 글자가 일치하지 않으면 실패
		if(board[y][x] != word.charAt(0)) return false;
	    
		// 기저사례 3 : 단어 길이가 1이면 성공
		if(word.length() == 1) return true;
	    
		// 인접한 여덟 칸을 검사
		for(int direction = 0; direction < 8; ++direction) {
			int nextY = y + dy[direction];
			int nextX = x + dx[direction];
			// 다음 칸이 범위 안에 있는지, 첫 글자는 일치하는지 확인할 필요가 없음.
			if(hasWord(nextY, nextX, word.substring(1))) return true;
		}
		
		return false;
	}
	
	private static boolean inRange(int y, int x) {
		return (x >= 0 && x < 5) && (y >= 0 && y < 5);
	}
	
	private static void printBoard() {
		for(char i[]: board) {
			for(char j:i) {
				System.out.print(j +" ");
			}
			System.out.println();
		}
	}
}