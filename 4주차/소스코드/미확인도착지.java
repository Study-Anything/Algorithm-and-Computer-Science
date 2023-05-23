import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int n, m, t; // 교차로, 도로, 목적지 후보의 개수
    static int s, g, h; // 출발지, 교차로
    static int[] dist;
    static ArrayList<Node>[] graph;
    static ArrayList<Integer> ansList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        T = Integer.parseInt(br.readLine()); //testcase 개수
        while (T-- > 0) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken()); // 교차로 개수
            m = Integer.parseInt(stk.nextToken()); // 도로 개수
            t = Integer.parseInt(stk.nextToken()); // 목적지 후보의 개수
            stk = new StringTokenizer(br.readLine());
            s = Integer.parseInt(stk.nextToken()); // 출발지
            g = Integer.parseInt(stk.nextToken()); // 교차로
            h = Integer.parseInt(stk.nextToken()); // 교차로

            // graph init
            graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                int d = Integer.parseInt(stk.nextToken()); // weight
                graph[a].add(new Node(b, d)); // 양방향
                graph[b].add(new Node(a, d));
            }

            for (int i = 0; i < t; i++) {
                int x = Integer.parseInt(br.readLine()); // 목적지 후보
                // 이제 s-g-h-x, s-h-g-x 까지의 최단경로와, s-x 의 최단경로를 구하자
                dist = new int[n + 1];
                dijkstra(s);
                int s2g = dist[g];
                int s2h = dist[h];
                int s2x = dist[x];
                dijkstra(g);
                int g2h = dist[h];
                int g2x = dist[x];
                dijkstra(h);
                int h2g = dist[g];
                int h2x = dist[x];

                if (s2g == Integer.MAX_VALUE || s2h == Integer.MAX_VALUE || s2x == Integer.MAX_VALUE || g2x == Integer.MAX_VALUE || h2x == Integer.MAX_VALUE) {
                    continue;
                }

                int s2g2h2x = s2g + g2h + h2x;
                int s2h2g2x = s2h + h2g + g2x;
                int minPath = Math.min(s2g2h2x, s2h2g2x);

                if (minPath <= s2x) {
                    ansList.add(x);
                }
            }
            Collections.sort(ansList);
            for (int iter : ansList) {
                bw.write(iter + " ");
            } bw.newLine();
            ansList.clear();
        }

        bw.flush();
        bw.close();
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

    static public void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (Node iter : graph[now.to]) {
                // 연결관계 탐색
                if (dist[iter.to] > dist[now.to] + iter.w) {
                    dist[iter.to] = dist[now.to] + iter.w;
                    pq.offer(new Node(iter.to, dist[iter.to]));
                }
            }
        }

    }
}
