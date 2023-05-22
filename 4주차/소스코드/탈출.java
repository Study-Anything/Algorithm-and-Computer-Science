import java.io.*;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static int[][] graph;
    static int[][][] dist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        int ans = -1;

        graph = new int[r][c];
        dist = new int[r][c][2];

        Queue<Node> waterQueue = new LinkedList<>();
        Queue<Node> dochiQueue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                graph[i][j] = str.charAt(j);
                if (graph[i][j] == 'S'/*고슴도치의 위치*/) {
                    dochiQueue.add(new Node(j, i, 0));
                }
                if (graph[i][j] == '*'/*물이 차있는 곳의 위치*/) {
                    waterQueue.add(new Node(j, i, 0));
                }
            }
        } // input

        // bfs once w/ two queues
        boolean flag = false;
        while (!dochiQueue.isEmpty()) {
            if (flag) break;
            while (!waterQueue.isEmpty()) {
                Node nowWater = waterQueue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = nowWater.x + dx[dir];
                    int ny = nowWater.y + dy[dir];
                    if (nx < 0 || nx >= c || ny < 0 || ny >= r) continue; // 범위초과
                    if (graph[ny][nx] == 'X' || graph[ny][nx] == 'D' || dist[ny][nx][0] > 0) continue;
                    waterQueue.offer(new Node(nx, ny, nowWater.times + 1));
                    dist[ny][nx][0] = nowWater.times + 1;
                }
            }
            Node nowDochi = dochiQueue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = nowDochi.x + dx[dir];
                int ny = nowDochi.y + dy[dir];
                if (nx < 0 || nx >= c || ny < 0 || ny >= r) continue; // 범위초과
                if (graph[ny][nx] == 'D') {
                    ans = nowDochi.times + 1;
                    flag = true;
                    break;
                }
                if (graph[ny][nx] == '*' || graph[ny][nx] == 'X' || dist[ny][nx][1] > 0) continue;
                if (dist[ny][nx][0] <= nowDochi.times + 1 && dist[ny][nx][0] != 0) continue;

                dochiQueue.offer(new Node(nx, ny, nowDochi.times + 1));
                dist[ny][nx][1] = nowDochi.times + 1;
            }
        }
        if (flag)
            bw.write(ans + "\n");
        else
            bw.write("KAKTUS\n");

        bw.flush();
        bw.close();
    }

    static class Node {
        int x;
        int y;
        int times;

        public Node(int x, int y, int times) {
            this.x = x;
            this.y = y;
            this.times = times;
        }
    }
}
