package Full_Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TSP {
	
	// 도시의 수
	private static int N;
	
	// 각 도시간의 거리
	private static int[][] dis;
	
	// 각 도시 방문 여부
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int C = Integer.parseInt(sc.readLine());
		for(int i=0; i<C; i++) {
			N = Integer.parseInt(sc.readLine());
			dis = new int[N][N];
			visited = new boolean[N];
			
			// 각 도시간의 거리 입력
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(sc.readLine());
				for(int x=0; x<N; x++) {
					dis[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(shortestPath(new ArrayList<>(), visited, N, 0)+"\n");
		}
		System.out.println(sb);
	}
	
	// 가장 최단 거리를 찾는 함수
	private static int shortestPath(ArrayList<Integer> curCity, boolean[] visitied, int n, int curLength) {
		// 모든 도시를 방문한 경우
		if(curCity.size()==n) {
			return curLength+dis[curCity.get(0)][curCity.get(curCity.size()-1)];
		}
		
		// 초기 ret값을 비교할 수 없을 정도로
		// MAX_VALUE 값 설정
		int ret=Integer.MAX_VALUE;
		
		// 현재 도시 위치
		int here = curCity.isEmpty()?0:curCity.get(curCity.size()-1);
		
		// 재귀 호출 시작
		for(int next=0; next<n; next++) {
			if(!visitied[next]){
				visitied[next]=true;
				curCity.add(next);
				ret = Math.min(ret, shortestPath(curCity, visitied, n, curLength+dis[here][next]));
				visitied[next]=false;
				curCity.remove(curCity.size()-1);
			}
		}
		return ret;
	}
}