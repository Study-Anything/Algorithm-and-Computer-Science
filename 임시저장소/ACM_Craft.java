import java.io.*;
import java.util.*;

// boj 1005
// Top-sort with DP

public class Main {
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int tc = Integer.parseInt(br.readLine()); // testcase
        while (tc-- > 0) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken()); // 건물 개수(node)
            int k = Integer.parseInt(stk.nextToken()); // 규칙 개수(edge)

            // graph init
            graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            int[] indegree = new int[n + 1];
            int[] times = new int[n + 1];

            stk = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                times[i] = Integer.parseInt(stk.nextToken());
            }
            // connect
            for (int i = 0; i < k; i++) {
                stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                graph[a].add(b); // direction graph
                indegree[b]++;
            }
            int must = Integer.parseInt(br.readLine());
            int [] dp = new int[n+1];
            // top_sort with dp
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                    dp[i] = times[i];
                }
            }
            while(!q.isEmpty()){
                int now = q.poll();
                for(int ele : graph[now]){
                    dp[ele] = Math.max(dp[ele], dp[now] + times[ele]);
                    indegree[ele]--;
                    if(indegree[ele]==0){
                        q.add(ele);
                    }
                }
            }
            bw.write(dp[must]+"\n");
        }

        bw.flush();
        bw.close();
    }
}
