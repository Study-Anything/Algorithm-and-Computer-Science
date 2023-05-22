import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int[][] num;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken()); // 표의 크기
        m = Integer.parseInt(stk.nextToken()); // 쿼리 개수

        dp = new int[n + 1][n + 1];
        num = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                num[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        init();
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(stk.nextToken());
            int y1 = Integer.parseInt(stk.nextToken());
            int x2 = Integer.parseInt(stk.nextToken());
            int y2 = Integer.parseInt(stk.nextToken());
            bw.write(query(x1, y1, x2, y2) + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void init() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + num[i][j];
            }
        }
    }

    static int query(int x1, int y1, int x2, int y2) {
        return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
    }
}
