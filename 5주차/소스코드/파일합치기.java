import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] files;
    static int[] sums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            n = Integer.parseInt(br.readLine());
            files = new int[n + 1];
            sums = new int[n + 1];
            dp = new int[n + 1][n + 1];
            stk = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                files[i] = Integer.parseInt(stk.nextToken());
                sums[i] = sums[i - 1] + files[i];
            }
            for (int i = 1; i <= n; i++) {
                for (int from = 1; i + from <= n; from++) {
                    int to = i + from;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int k = from; k < to; k++) {
                        dp[from][to] = Math.min(dp[from][to],
                                dp[from][k] + dp[k + 1][to] + sums[to] - sums[from-1]);
                    }
                }
            }
            bw.write(dp[1][n]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
