import java.io.*;
import java.util.*;
/*
  트리에서의 다이나믹 프로그래밍
  [문제 조건] 모든 사람이 이 아이디어를 받아들여야 함.
  dp[v][O]: 루트가 v 인 서브트리에서
            v 가 얼리어답터일 경우에 문제 조건을
            만족시키는 얼리어답터 노드의 최소 개수
  dp[v][X]: 루트가 v 인 서브트리에서
            v 가 얼리어답터가 아닐 때, 문제 조건을
            만족시키는 얼리어답터 노드의 최소 개수
            
  [핵심 아이디어]
  dp[parent][O] += child 가 X 이든 O 이든 최소값을 선택
  dp[parent][X] = child 는 반드시 O 여야 함.
*/
public class Main {
    static int n;
    static ArrayList<Integer>[] tree;
    static int [][] dp;
    static int O = 0;
    static int X = 1;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        // init
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < n-1; i++){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        dp = new int[n+1][2];
        find(1);

        int ans = Math.min(dp[1][O], dp[1][X]);

        bw.write(ans+"\n");
        bw.flush();
        bw.close();
    }
    static public void find(int v){
        dp[v][O] = 1;
        dp[v][X] = 0;

        visited[v] = true;
        for(int ele : tree[v]){
            if(!visited[ele]){
                visited[ele] = true;
                find(ele);
                dp[v][O] += Math.min(dp[ele][X], dp[ele][O]);
                dp[v][X] += dp[ele][O];
            }
        }
    }
}
