import java.util.*;
import java.io.*;


public class Main
{
    static int n; // 노드 개수
    static int m; // 엣지 개수
    static boolean[] fromS;
    static boolean[] fromT;
    static boolean[] toS;
    static boolean[] toT;
    static int ans;
    static int S;
    static int T;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] graphR;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        fromS = new boolean[n+1];
        fromT = new boolean[n+1];
        toS = new boolean[n+1];
        toT = new boolean[n+1];

        // Costruct graph
        graph = new ArrayList[n+1];
        graphR = new ArrayList[n+1];
        for(int i = 0 ; i <=n ;i++){
            graph[i] = new ArrayList<>();
            graphR[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m; i++){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            graph[a].add(b); // direct graph (a2b)
            graphR[b].add(a); // direct graph (b2a)
        }
        stk = new StringTokenizer(br.readLine());
        S = Integer.parseInt(stk.nextToken());
        T = Integer.parseInt(stk.nextToken());


        fromS[T] = true;
        fromT[S] = true;
        
        dfs(S, fromS, graph);
        dfs(T, fromT, graph);
        dfs(S, toS, graphR);
        dfs(T, toT, graphR);
    
        // calc answer
        for(int i = 1; i<=n; i++){
            if(fromS[i]&&fromT[i]&&toS[i]&&toT[i]){
                ans++;
            }
        }
        System.out.println(ans-2);
    }
    public static void dfs(int v, boolean [] visited, ArrayList<Integer>[] graph){
        if(visited[v]){
            return;
        }
        //System.out.println(v);
        visited[v] = true;
        for(int ele : graph[v]){
            // 연결 성분에 대하여
            dfs(ele, visited, graph);
        }
    }

}
