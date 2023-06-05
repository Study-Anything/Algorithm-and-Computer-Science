import java.io.*;
import java.util.*;

public class Main {
  // greedy
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            nodes[i] = new Node(m, v);
        }
        Arrays.sort(nodes);

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int idx = 0;
        long total = 0;
        for (int i = 0; i < k; i++) {
            int nowWeight = bags[i];
            while (idx < n && nowWeight >= nodes[idx].m) {
                pq.add(nodes[idx++].v);
            }
            if (!pq.isEmpty())
                total += pq.poll();
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int m;
        int v;

        public Node(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            if (o.m == this.m) {
                return o.v - this.v;
            } else return this.m - o.m;
        }
    }
}
