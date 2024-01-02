import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N=4, K, ans;
	static int score[] = {1, 2, 4, 8};
	static int dir[];

	static ArrayList<Integer> magnet[];

	static boolean visited[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int Test_Case = Integer.parseInt(sc.readLine());
		for (int T = 1; T <= Test_Case; T++) {
			K = Integer.parseInt(sc.readLine());

			magnet = new ArrayList[N];


			for(int i=0; i<N; i++) {
				magnet[i] = new ArrayList<>();
				
				st = new StringTokenizer(sc.readLine());
				for(int j=0; j<8; j++) {
					magnet[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			ans=0;
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(sc.readLine());
				int num = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());

				visited = new boolean[N];
				dir = new int[N];
				
				solve(num, d);
				rotate();
			}
			
			setScore();
			sb.append("#" + T + " " + ans + "\n");
		}
		System.out.println(sb);
	}

	static void solve(int num, int d) {
		switch (num) {
		case 0:
			visited[num]=true;
			dir[num] = d;
			if(!visited[num+1] && magnet[num].get(2) != magnet[num+1].get(6)) {
				solve(num+1, d==-1?1:-1);
			}
			break;

		case 1: case 2:
			visited[num]=true;
			dir[num] = d;
			
			if(!visited[num+1] && magnet[num].get(2) != magnet[num+1].get(6)) {
				solve(num+1, d==-1?1:-1);
			}
			
			if(!visited[num-1] && magnet[num].get(6) != magnet[num-1].get(2)) {
				solve(num-1, d==-1?1:-1);
			}
			
			break;

		case 3:
			visited[num]=true;
			dir[num]=d;
			
			if(!visited[num-1] && magnet[num-1].get(2) != magnet[num].get(6)) {
				solve(num-1, d==-1?1:-1);
			}
			break;
		}
	}
	
	static void rotate() {
		int from, to;

		for(int i=0; i<N; i++) {
			if(visited[i]) {
				if(dir[i]==1) {
					from = magnet[i].size()-1;
					to = 0;
				}else {
					from = 0;
					to = magnet[i].size()-1;
				}
				
				int tmp = magnet[i].get(from);
				magnet[i].remove(from);
				magnet[i].add(to, tmp);
			}
		}
	}

	static void setScore() {
		for(int i=0; i<N; i++) {
			if(magnet[i].get(0)==1) ans+=score[i];
		}
	}
}