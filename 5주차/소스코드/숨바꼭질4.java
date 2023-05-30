import java.io.*;
import java.util.*;

public class Main {
    static int n; // 수빈
    static int k; // 동생
    static int [] visited;
    static int [] dist;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        visited = new int[100002];
        dist = new int[100002];
        // bfs + 경로 역추적
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        Arrays.fill(visited, -1);
        while(!q.isEmpty()){
            int x = q.poll();
            if(x==k){ // 도착
                bw.write(dist[k]+"\n");
                Stack<Integer> s = new Stack<>();
                s.add(k);
                for(int i = x; i!=n; i = visited[i]){
                    s.add(visited[i]);
                }
                while(!s.isEmpty()){
                    bw.write(s.pop()+" ");
                }
                break;
            }
            for(int nx : new int[]{x-1, x+1, 2*x}){
                if(nx < 0 || nx > 100000) continue; // 맵 바깥
                if(visited[nx] != -1) continue;

                dist[nx] = dist[x] + 1;
                visited[nx] = x;
                q.add(nx);
            }
        }

        bw.flush();
        bw.close();
    }
}
