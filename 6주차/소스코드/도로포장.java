import java.io.*;
import java.util.*;

public class Main {
    static int n; // 노드
    static int m; // 엣지
    static int k; // 포장할 도로 개수
    static long[][] dist;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());
            graph[a].add(new Node(b, w, 0));
            graph[b].add(new Node(a, w, 0));
        }

        dist = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        dist[1][0] = 0;

        // do dijkstra
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.w > dist[now.to][now.cnt])
                continue;
            for (Node iter : graph[now.to]) {
                if (now.cnt < k && dist[iter.to][now.cnt + 1] > dist[now.to][now.cnt]) {
                    dist[iter.to][now.cnt + 1] = dist[now.to][now.cnt];
                    pq.add(new Node(iter.to, dist[iter.to][now.cnt + 1], now.cnt + 1));
                }
                if (dist[iter.to][now.cnt] > dist[now.to][now.cnt] + iter.w) {
                    dist[iter.to][now.cnt] = dist[now.to][now.cnt] + iter.w;
                    pq.add(new Node(iter.to, dist[iter.to][now.cnt], now.cnt));
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            ans = Math.min(dist[n][i], ans);
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int to;
        long w;
        int cnt;

        public Node(int to, long w, int cnt) {
            this.to = to;
            this.w = w;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.w - o.w);
        }
    }
}
