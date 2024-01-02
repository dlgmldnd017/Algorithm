import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//    각각 스위치 수, 학생 수
	static int swiN, stuN;
	//    현재 스위치 상태
	static int[] stat;
	//    학생 성별과 받은 수의 상태값
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		swiN = Integer.parseInt(sc.readLine());
		
		stat = new int[swiN+1];
		st = new StringTokenizer(sc.readLine());
		for(int i=1; i<=swiN; i++) {
			stat[i] = Integer.parseInt(st.nextToken());
		}

		stuN = Integer.parseInt(sc.readLine());
		map = new int[stuN][2];
		for(int i=0; i<stuN; i++) {
			st = new StringTokenizer(sc.readLine());
			int XY = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			map[i][0] = XY;
			map[i][1] = n;
		}

		solve();
		printStat();
	}

	public static void solve() {
		for(int i=0; i<stuN; i++) {
			int XY = map[i][0];
			int n = map[i][1];
			
//			남성일 경우
			if(XY==1) {
				for(int j=1; j<=swiN; j++) {
					if((j)%n==0) {
						stat[j] = stat[j]==1?0:1;
					}
				}
//			여성일경우
			}else {
				int start = -1, end=-1;
				
				for(int j=1; j<=swiN; j++) {
					if(j%n==0) {
						start = j-1;
						end = j+1;
						while(true) {
							if(!inRange(start, end) || stat[start]!=stat[end]) break;
							stat[start] = stat[start]==1?0:1;
							stat[end] = stat[end]==1?0:1;
							start--; end++;
						}
						stat[j] = stat[j]==1?0:1;
						break;
					}
				}
			}
		}
	}
	
//	여성일 경우 대칭을 구할 때 범위안에 드는 것인지 확인하는 메서드
	public static boolean inRange(int j1, int j2) {
		return (j1>=1)&&(j2<=swiN);
	}
	
//	현재 스위치 상태를 출력하는 메서드
	public static void printStat() {
		for(int i=1; i<=swiN; i++) {
			System.out.print(stat[i] + " ");
			if(i%20==0) System.out.println();
		}
	}
}
