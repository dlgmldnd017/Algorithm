package Divide_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree1 {
	private static String quadTree;
	private static char decompressed[][];

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		quadTree = sc.readLine();
		decompressed = new char[quadTree.length()][quadTree.length()];
		decompress(quadTree, 0, 0, 0, quadTree.length()-1); 
		
		// 압축 해제한 결과를 보여주는 이중 for문
		for(int i=0; i<quadTree.length(); i++) {
			for(int j=0; j<quadTree.length(); j++) {
				System.out.printf("%2c",decompressed[i][j]);
			}
			System.out.println();
		}
		sc.close();
	}
	
	// 압축 해제를 수행하는 함수
	private static int decompress(String s, int it, int y, int x, int size) {
        char head = s.charAt(it++);

        // 기저사례: head의 첫 글자가 b 또는 w 인 경우
        if (head == 'b' || head == 'w') {
            for (int dy = 0; dy < size; dy++) {
                for (int dx = 0; dx < size; dx++) {
                    decompressed[y + dy][x + dx] = head;
                }
            }
        } else {
        	// 네 부분을 쪼개 각각 순서대로 압축 해제한다.
        	// 위에서 아래로 각각 1,2,3,4사 분면을 재귀호출한다.
            int half = size / 2;
            it = decompress(s, it, y, x, half);
            it = decompress(s, it, y, x + half, half);
            it = decompress(s, it, y + half, x, half);
            it = decompress(s, it, y + half, x + half, half);
        }
        return it;
	}
}