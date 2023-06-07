import java.io.*;
import java.util.*;

public class Main {

    /*
    BOJ 15681 : 트리와 쿼리 -> 트리에서 DP 
    
    트리의 정점의 수 N과 루트의 번호 R, 쿼리의 수 Q가 주어진다. (2 ≤ N ≤ 105, 1 ≤ R ≤ N, 1 ≤ Q ≤ 105)
    이어 N-1줄에 걸쳐, U V의 형태로 트리에 속한 간선의 정보가 주어진다. (1 ≤ U, V ≤ N, U ≠ V)
    이는 U와 V를 양 끝점으로 하는 간선이 트리에 속함을 의미한다.
    이어 Q줄에 걸쳐, 문제에 설명한 U가 하나씩 주어진다. (1 ≤ U ≤ N)
    입력으로 주어지는 트리는 항상 올바른 트리임이 보장된다.
     */
    static int n;
    static int r;
    static int q;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int r = Integer.parseInt(stk.nextToken());
        int q = Integer.parseInt(stk.nextToken());

        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        dp = new int[n + 1];
        visited = new boolean[n + 1];

        find(r);

        while (q-- > 0) {
            int t = Integer.parseInt(br.readLine());
            bw.write(dp[t] + "\n");
        }
        bw.flush();
        bw.close();
    }

    static int find(int v) {
        visited[v] = true;
        int ret = 1;
        if (dp[v] == 0) {
            for (int iter : tree[v]) {
                if (!visited[iter]) {
                    ret += find(iter);
                    visited[iter] = true;
                }
            }
            dp[v] = ret;
        }
        return dp[v];
    }
}
