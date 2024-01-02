import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Shark{
    int r, c, s, d, z, idx;
    boolean isDeath;
    public Shark(int r, int c, int s, int d, int z, int idx) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
        this.idx = idx;
    }
}

public class Main {
    static int R, C, M, ans;
    static String map[][];

    static List<Shark> list = new ArrayList<>();

    // 상 하 좌 우
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, 1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(sc.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[R+1][C+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(sc.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            map[r][c] = "";
            map[r][c] += i;
            list.add(new Shark(r, c, s, d, z, i));
        }

        solve();
        System.out.println(ans);
    }

    static void solve() {
        // 낚시왕이 C열로 가면 이동이 끝난다.
        for(int x=1; x<=C; x++) {

            // 낚시왕이 있는 곳에서 상어를 잡는다.
            catchShark(x);
            
            // 상어를 움직인다.
            moveShark();
            
            // 겹치는 상어를 찾는다.
            checkDoubleShark();
        }
    }

    static void print() {
        for(String str1[] : map) {
            for(String str2 : str1) {
                if(str2==null) System.out.print(".");
                else System.out.print(str2);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void catchShark(int x) {
        for(int y=1; y<=R; y++) {
            if(map[y][x]!=null) {
                String str[] = map[y][x].split(",");
                int idx = Integer.parseInt(str[0]);
                
                if(list.get(idx).isDeath) continue;
                
                ans+=list.get(idx).z;
                list.get(idx).isDeath=true;
                return;
            }
        }
    }

    static void moveShark() {
        String copy[][] = new String[R+1][C+1];

        for(Shark cur : list) {
            if(cur.isDeath) continue;
            
            switch(cur.d) {
            case 0: case 1:
                cur.s %= (R*2-2);
                for(int i=0; i<cur.s; i++) {
                    if(cur.r==1) cur.d=1;
                    else if(cur.r==R) cur.d=0;
                    
                    cur.r += dy[cur.d];
                }
                
                break;
            
            case 2: case 3:
                cur.s %= (C*2-2);
                for(int i=0; i<cur.s; i++) {
                    if(cur.c==1) cur.d=2;
                    else if(cur.c==C) cur.d=3;
                    
                    cur.c += dx[cur.d];
                }
                break;
            }
            
            if(copy[cur.r][cur.c]!=null) {
            	copy[cur.r][cur.c] += cur.idx+",";
            }else {
            	copy[cur.r][cur.c] = "";
            	copy[cur.r][cur.c] = cur.idx+",";
            }
        }
        map = copyArr(copy);
    }

    static void checkDoubleShark() {
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                if(map[i][j]!=null && map[i][j].length()>2) {
                    String str[] = map[i][j].split(",");

                    int max=-1, maxIdx=-1;

                    for(int k=0; k<str.length; k++) {
                        int idx = Integer.parseInt(str[k]);

                        if(max<list.get(idx).z) {
                            max = list.get(idx).z;
                            maxIdx = idx;
                        }
                    }

                    for(int k=0; k<str.length; k++) {
                        int idx = Integer.parseInt(str[k]);

                        if(maxIdx==idx) map[list.get(idx).r][list.get(idx).c] = idx+"";
                        else list.get(idx).isDeath=true;
                    }
                }
            }
        }
    }

    static String[][] copyArr(String copy[][]){
        String tmp[][] = new String[R+1][C+1];
        for(int i=1; i<=R; i++) {
            tmp[i] = copy[i].clone();
        }

        return tmp;
    }
}