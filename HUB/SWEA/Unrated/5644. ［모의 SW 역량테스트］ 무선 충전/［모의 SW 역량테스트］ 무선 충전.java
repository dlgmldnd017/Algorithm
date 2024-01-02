import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Charger{
	int y, x, c, p;

	public Charger(int y, int x, int c, int p) {
		this.y = y;
		this.x = x;
		this.c = c;
		this.p = p;
	}
}

public class Solution {
	static int N=10, M, A, ans;
	static int Y[], X[];
	
	static Queue<Charger> charger;
	
	static int dy[] = {0, -1, 0, 1, 0};
	static int dx[] = {0, 0, 1, 0, -1};

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(sc.readLine());
		for (int t = 1; t <= test_case; t++) {
			st = new StringTokenizer(sc.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			Y = new int[M];
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<M; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			X = new int[M];
			st = new StringTokenizer(sc.readLine());
			for(int i=0; i<M; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			
			charger = new LinkedList<Charger>();
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(sc.readLine());
				int y = Integer.parseInt(st.nextToken())-1;
				int x = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				charger.add(new Charger(x, y, c, p));
			}
			
			ans=0;
			solve(0, 0, 9, 9);
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb);
	}

	static void solve(int Ay, int Ax, int By, int Bx) {
		ans = getInCharger(Ay, Ax, By, Bx);
		
		for(int i=0; i<M; i++) {
			 Ay += dy[Y[i]];
			 Ax += dx[Y[i]];
			 By += dy[X[i]];
			 Bx += dx[X[i]];
			 
			 ans += getInCharger(Ay, Ax, By, Bx);
		}
	}

	static int getInCharger(int Ay, int Ax, int By, int Bx) {
		List<Charger> listA = new ArrayList<>();
		List<Charger> listB = new ArrayList<>();
		
		for(Charger c : charger) {
			int d=0;
			
			d = Math.abs(c.y-Ay) + Math.abs(c.x-Ax);
			if(d<=c.c) listA.add(c);
			
			d = Math.abs(c.y-By) + Math.abs(c.x-Bx);
			if(d<=c.c) listB.add(c);
		}
		
		int tmp=0, max=0;
		if(listA.size()>0 && listB.size()>0) {
			for(Charger c1 : listA) {
				for(Charger c2 : listB) {
					tmp=0;
					
					if(c1==c2) {
						tmp = c1.p;
					}
					
					else {
						tmp += c1.p;
						tmp += c2.p;
					}
					
					max = Math.max(max, tmp);
				}
			}
		}
		
		else if(listA.size()>0) {
			for(Charger c : listA) {
				max = Math.max(max, c.p);
			}
		}
		
		else if(listB.size()>0) {
			for(Charger c : listB) {
				max = Math.max(max, c.p);
			}
		}
		
		return max;
	}
}
