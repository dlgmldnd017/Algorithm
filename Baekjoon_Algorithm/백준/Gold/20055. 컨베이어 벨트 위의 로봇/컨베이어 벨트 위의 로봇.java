import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K, ans;

    static List<Integer> listA = new ArrayList<>();
    static List<Integer> map = new ArrayList<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(sc.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        listA.add(0);
        map.add(0);
        
        for(int i=0; i<N; i++) {
        	map.add(0);
        }

        st = new StringTokenizer(sc.readLine());
        for(int i=1; i<=N*2; i++) {
            listA.add(Integer.parseInt(st.nextToken()));
        }

        solve();
        System.out.println(ans);
    }

    static void solve() {
        int step=1;

        while(true) {
            // 회전한다.
            rotate();
            
            // 로봇 이동
            moveRobot();
            
            // 1번 위치에 상자를 올린다.
            raise();

            if(check()) {
            	ans=step;
            	return;
            }
            step++;
        }
    }
    
    static void rotate() {
        // A배열 돌리기
        int tmp1 = listA.get(listA.size()-1);
        listA.remove(listA.size()-1);
        listA.add(1, tmp1);

        // 로봇 돌리기
        map.remove(map.size()-1);
        map.add(1, 0);
        
        // 로봇이 N위치라면 out
        if(map.get(N)==1) map.set(N, 0);
    }
    
    static void moveRobot() {
        for(int i=N-1; i>0; i--) {
        	int idx = map.get(i);
        	
        	// 현재 위치에 옮길 로봇이 없거나 옮길 위치에 로봇이 있는 경우 또는
        	// 내구도가 0일 경우
        	if(idx==0 || map.get(i+1)==1 || listA.get(i+1)==0) continue;
        	
        	map.set(i, 0);
        	map.set(i+1, 1);
        	listA.set(i+1, listA.get(i+1)-1);
        }
    }

    static void raise() {
        if(listA.get(1)==0) return;

        map.set(1, 1);
        listA.set(1, listA.get(1)-1);
    }
    
    static boolean check() {
    	int cnt=0;
    	
    	for(int i=1; i<=2*N; i++) {
    		if(listA.get(i)==0) cnt++;
    	}
    	
    	if(cnt>=K) return true;
    	return false;
    }
}