package Divide_Conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FanMeeting2 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder builder = new StringBuilder();
	private static String M,N;
	
	public static void main(String[] args) throws Exception {
		int c = Integer.parseInt(sc.readLine());
		
		for(int i=0; i<c; i++) {
			M = sc.readLine();
			N = sc.readLine();
			builder.append(hugs(M,N)+"\n");
		}
		System.out.println("\n"+builder);
	}
	
	private static int hugs(String M, String N) {
		String strM = M.replace('M', '1').replace('F','0');
		String strN = N.replace('M', '1').replace('F','0');
		
		// 기저 사례: 맴버가 팬보다 맞으면 0 리턴
		if(strM.length() > strN.length()) return 0;

		long A = Long.parseLong(strM,2);
		long B = Long.parseLong(strN,2);
		
        int result = 0;

        for(int i=0 ; i < strN.length() - strM.length() + 1 ; i++) {
            if ((A&B) == 0) {
                result += 1;
            }
            
            // 팬을 오른쪽으로 한 칸씩 옮긴다.
            B = (B>>1);
        }

        return result;
	}
}