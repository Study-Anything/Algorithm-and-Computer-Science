import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] graph;
    static int v;
    static int e;
    static int s;
    static int [] dist;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        // graph init
        stk = new StringTokenizer(br.readLine());
        v = Integer.parseInt(stk.nextToken());
        e = Integer.parseInt(stk.nextToken());

        s = Integer.parseInt(br.readLine());

        graph = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            graph[a].add(new Node(b, w));
        }
        dijkstra(s);

        for(int i = 1; i <=v; i++){
            if(dist[i] != Integer.MAX_VALUE){
                bw.write(dist[i]+"\n");
            } else bw.write("INF\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node> {
        int to;
        int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int [v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.add(new Node(start, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            for(Node iter : graph[now.to]){
                if(dist[iter.to] > dist[now.to] + iter.w){
                    dist[iter.to] = dist[now.to] + iter.w; // update
                    pq.add(new Node(iter.to, dist[iter.to]));
                }
            }
        }
    }
}
