package Divide_Conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FanMeeting1 {
	private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder builder = new StringBuilder();
	
	private static String M, N;
	
	public static void main(String[] args) throws Exception{
		int c = Integer.parseInt(sc.readLine());

		for(int i=0; i<c; i++) {
			M = sc.readLine();
			N = sc.readLine();
			builder.append(bruteForce()+"\n");
		}
		System.out.println("\n"+builder);
	}
	
	private static int bruteForce() {
		int ret = 0;

		for(int i=0; i<N.length() - M.length()+1; i++) {
			
			// 만약 남성과 남성끼리 만남이 있으면 바로 break와
			// 남성과 남성끼리의 만남이 없으면 바로 ret++를 한다.
			boolean isOk = false;
			
			for(int j=0; j<M.length(); j++) {
				if((M.charAt(j) == 'M' && N.charAt(j+i) == 'M')) {
					isOk = false;
					break;
				}
				isOk = true;
			}
			if(isOk) {
				ret++;
			}
		}
		return ret;
	}
}