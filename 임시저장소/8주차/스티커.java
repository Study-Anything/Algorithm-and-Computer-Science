import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] score = new int[2][n];
            for (int i = 0; i < 2; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    score[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            int[][] dp = new int[2][n+2];
            for(int i = 0 ; i < 2 ; i++){
                for(int j = 0 ; j < n ; j++){
                    dp[i][j+2] = score[i][j];
                }
            }

            for(int i = 2; i < n+2 ; i++){
                dp[0][i] += Math.max(dp[1][i-1], dp[1][i-2]);
                dp[1][i] += Math.max(dp[0][i-1], dp[0][i-2]);
            }

            int ans = Math.max(dp[0][n+1], dp[1][n+1]);
            bw.write(ans+"\n");
        }

        bw.flush();
        bw.close();
    }
}
