import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        int[] index = new int[n + 1];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            dp[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    index[i] = j;
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        Stack<Integer> st = new Stack<>();

        int t = ans;
        for (int i = n - 1; i >= 0; i--) {
            if(dp[i] == t){
                st.add(arr[i]);
                t--;
            }
        }

        bw.write(ans + "\n");
        while(!st.isEmpty()){
            bw.write(st.pop()+" ");
        }

        bw.flush();
        bw.close();
    }
}
