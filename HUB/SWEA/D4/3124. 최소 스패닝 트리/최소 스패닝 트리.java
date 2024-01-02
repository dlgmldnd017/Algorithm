import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
class Node implements Comparable<Node>{
    int start, d;
 
    public Node(int start, int d) {
        this.start = start;
        this.d = d;
    }
 
    @Override
    public int compareTo(Node o) {
        return d-o.d;
    }
}
 
public class Solution {
    static long ans;
    static int V, E;
    static int dist[];
 
    static ArrayList<Node> map[];
 
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int test_case = Integer.parseInt(sc.readLine());
        for(int T=1; T<=test_case; T++) {
            st = new StringTokenizer(sc.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
 
            map = new ArrayList[V];
            dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
 
            for(int i=0; i<V; i++) {
                map[i] = new ArrayList<>();
            }
 
            for(int i=0; i<E; i++) {
                st = new StringTokenizer(sc.readLine());
                int y = Integer.parseInt(st.nextToken())-1;
                int x = Integer.parseInt(st.nextToken())-1;
                int d = Integer.parseInt(st.nextToken());
 
                map[y].add(new Node(x, d));
                map[x].add(new Node(y, d));
            }
 
            ans=0;
            solve(0);
            sb.append("#"+T+" "+ans+"\n");
        }
        System.out.println(sb);
    }
 
    static void solve(int start) {
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        boolean visited[] = new boolean[V];
 
        dist[start]=0;
        q.add(new Node(start, 0));
 
        int cnt=0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            start = cur.start;
 
            if(visited[start]) continue;
            visited[start] = true;
             
            ans += cur.d;
            if(++cnt == V) break;
             
            for(Node n : map[start]) {
                if(!visited[n.start] && dist[n.start] > n.d) {
                    dist[n.start] = n.d;
                	q.add(n);
                }
            }
        }
    }
}