import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static ArrayList<fireBall> graph[][];
    static ArrayList<fireBall> list;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken()); // nxn 크기의 격자
        m = Integer.parseInt(stk.nextToken()); // 초기 파이어볼의 개수
        k = Integer.parseInt(stk.nextToken()); // 이동이 일어나는 개수

        graph = new ArrayList[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        list = new ArrayList<>();  // init

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int r, c, m, s, d;
            r = Integer.parseInt(stk.nextToken());
            c = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken()); // 질량
            s = Integer.parseInt(stk.nextToken()); // 속력
            d = Integer.parseInt(stk.nextToken()); // 방향
            graph[r][c].add(new fireBall(r, c, m, s, d));
            list.add(new fireBall(r, c, m, s, d));
        }

        for (int i = 0; i < k; i++) {
            run();
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j].size() > 0) {
                    for (fireBall ele : graph[i][j]) {
                        ans += ele.mass;
                    }
                }
            }
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    public static void run() {
        move(); // 일단 이동
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j].size() > 1) {
                    int total_mass = 0;
                    int total_speed = 0;
                    boolean allEven = true;
                    boolean allOdd = true;
                    int cnt = 0;
                    for (fireBall ele : graph[i][j]) {
                        if (ele.direction % 2 == 0) {
                            allOdd = false;
                        }
                        if (ele.direction % 2 == 1) {
                            allEven = false;
                        }
                        total_mass += ele.mass;
                        total_speed += ele.speed;
                        cnt++;
                    }
                    graph[i][j] = new ArrayList<>();
                    int nmass = total_mass / 5;
                    if (nmass <= 0) {
                        continue;
                    }

                    int nspeed = total_speed / cnt;
                    if (allEven || allOdd) {
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 0));
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 2));
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 4));
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 6));
                    } else {
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 1));
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 3));
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 5));
                        graph[i][j].add(new fireBall(i, j, nmass, nspeed, 7));
                    }
                }
            }
        }
        list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j].size() > 0) {
                    list.addAll(graph[i][j]);
                }
            }
        }
    }

    public static void move() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                graph[i][j] = new ArrayList<>();
            }
        } // 일단 초기화하고
        for (fireBall now : list) {
            // 현재 파이어볼듣의 새로운 위치를 계산하자.
            int x = now.c;
            int y = now.r;
            int v = now.speed;

            int nx = x + dx[now.direction]*v%n;
            int ny = y + dy[now.direction]*v%n;

            if (nx > n) nx %= n;
            if (ny > n) ny %= n;
            if (nx <= 0) nx = n - Math.abs(nx);
            if (ny <= 0) ny = n - Math.abs(ny);

            now.update(ny, nx, now.mass, now.speed, now.direction);
            graph[ny][nx].add(now);
        }
    }

    public static class fireBall {
        int r;
        int c;
        int mass; // 질량
        int speed; // 속도
        int direction; // 방향

        public fireBall(int r, int c, int mass, int speed, int direction) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

        public void update(int r, int c, int mass, int speed, int direction) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }
}
