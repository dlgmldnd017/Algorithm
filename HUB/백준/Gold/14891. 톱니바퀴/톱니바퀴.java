import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int K, ans;
	static int d[];
	static boolean visited[];
	
	static List<Integer> wheels[] = new ArrayList[4];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i=0; i<4; i++) {
			wheels[i] = new ArrayList<Integer>();

			String input = sc.readLine();
			for(int j=0; j<input.length(); j++) {
				wheels[i].add(input.charAt(j)-'0');
			}
		}

		K = Integer.parseInt(sc.readLine());

		for(int i=0; i<K; i++) {
			st = new StringTokenizer(sc.readLine());
			int idx = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());

			d = new int[4];
			visited = new boolean[4];
			
			checkWheel(idx, dir);
			rotateWheel();
		}

		System.out.println(setScore());
	}
	
	static void checkWheel(int idx, int dir) {
		d[idx]=dir;
		visited[idx]=true;
		dir *= -1;
		
		switch(idx) {
		case 0:
			if(!visited[idx+1] && wheels[0].get(2) != wheels[1].get(6)) {
				checkWheel(1, dir);
			}
			break;

		case 3:
			if(!visited[idx-1] && wheels[3].get(6) != wheels[2].get(2)) {
				checkWheel(2, dir);
			}
			break;

		case 1: case 2:
			if(!visited[idx-1] && wheels[idx-1].get(2) != wheels[idx].get(6)) {
				checkWheel(idx-1, dir);
			}
			
			if(wheels[idx+1].get(6) != wheels[idx].get(2)) {
				checkWheel(idx+1, dir);
			}
			break;
		}
	}

	static void rotateWheel() {
		for(int i=0; i<4; i++) {
			if(visited[i]) {
				if(d[i]==1) {
					int tmp = wheels[i].get(wheels[i].size()-1);
					wheels[i].remove(wheels[i].size()-1);
					wheels[i].add(0, tmp);
				}
				else {
					int tmp = wheels[i].get(0);
					wheels[i].remove(0);
					wheels[i].add(tmp);
				}
			}
		}
	}

	static int setScore() {
		int score=0, idx=0;
		
		for(int i=1; i<=8; i*=2) {
			if(wheels[idx++].get(0)==1) score+=i;
		}

		return score;
	}
}