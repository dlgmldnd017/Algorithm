import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	
	static Data in, out;
	static Data data[], q[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        data = new Data[N];
        q = new Data[200000+1];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            data[i] = new Data(Integer.parseInt(st.nextToken()), 
                    Integer.parseInt(st.nextToken()));
        }
        
        Arrays.sort(data);
        
        int i=0, time=1;
        
        while (out!=null || data.length!=i) {
            while (i<data.length && data[i].at==time) {
            	
                if (in!=null) {
                    in.last = data[i];
                    data[i].next = in;
                } 
                
                else {
                    out = data[i];
                }
                
                in = data[i];
                q[data[i].rt] = data[i];
                i++;
            }
            
            if (time<200001 && q[time]!=null) {
                Data p = q[time];
                
                if (p.last!=null) p.last.next = p.next;
                else in = p.next;
                
                if (p.next != null) p.next.last = p.last;
                else out = p.last;
                
                q[p.rt] = null;
                
                ans = Math.max(ans, time-p.at);
            } 
            
            else if (out != null) {
                Data p = out;
                out = p.last;
                
                if (out!=null) out.next = null;
                    
                else in = null;
                
                q[p.rt] = null;
                
                ans = Math.max(ans, time-p.at);
            }

            time++;
        }
        
        System.out.println(ans);
    }

}

class Data implements Comparable<Data> {
    Data next, last;
    int rt, at;
    
    public Data(int rt, int at) {
		this.rt = rt;
		this.at = at;
	}

    @Override
    public int compareTo(Data o) {
        if(at==o.at) return rt-o.rt;
        return at-o.at;
    }
}