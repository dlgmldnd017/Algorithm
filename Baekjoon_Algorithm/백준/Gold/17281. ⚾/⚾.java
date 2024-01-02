import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M=9, cnt, ans;
	
	static boolean selected[];
	
	static ArrayList<Integer> arr, curBase;
	static ArrayList<Integer> map[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(sc.readLine());
		
		map = new ArrayList[N];
		arr = new ArrayList<>();
		curBase = new ArrayList<>();
		selected = new boolean[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(sc.readLine());
			
			map[i] = new ArrayList<>();
			for(int j=0; j<M; j++) {
				map[i].add(Integer.parseInt(st.nextToken()));
			}
			
			int tmp = map[i].get(0);
			map[i].remove(0);
			map[i].add(3,tmp);
		}
		
		makePerm(0);
		System.out.println(ans);
	}

	static void solve() {
		int curIdx=0;
		
		for(int i=0; i<N; i++) {
			int outCnt=0;
			curBase.clear();
			
			while(outCnt!=3) {
				curIdx %= 9;
				
				int curBatter = map[i].get(arr.get(curIdx));
				
				if(curBatter==0) {
					outCnt++;
					curIdx++;
					continue;
				}
				
				cnt += checkScore(curBatter);
				
				curIdx++;
			}
		}
	}
	
	static int checkScore(int hit) {
		int sum=0;
		
		if(hit==4) {
			sum = curBase.size()+1;
			curBase.clear();
		}else {
			for(int i=0; i<curBase.size(); i++) {
				curBase.set(i, curBase.get(i)+hit);
				
				if(curBase.get(i)>3) {
					curBase.remove(i);
					sum++;
					i--;
				}
			}
			curBase.add(hit);
		}
		
		return sum;
	}
	
	static void makePerm(int depth) {
		if(depth>3 && arr.get(3)!=3) return;
		
		if(depth==M) {
			cnt=0;
			
			solve();
			
			curBase.clear();
			ans = Math.max(ans, cnt);
			return;
		}
		
		for(int i=0; i<M; i++) {
			if(!selected[i]) {
				arr.add(i);
				selected[i]=true;
				
				makePerm(depth+1);
				
				arr.remove(arr.size()-1);
				selected[i]=false;
			}
		}
	}
}
