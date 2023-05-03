import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] sudoku;
    static int n; // 점의 개수
    static int m; // turn 의 개수
    static int[] parent;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int answer = 0;

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            if (find(a) == find(b)) {
                answer = i;
                break;
            } else {
                union(a, b);
            }
        }
        System.out.println(answer);
    }
    public static int find(int a){
        if(a==parent[a])
            return a;
        else return parent[a] = find(parent[a]);
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b){
            parent[b] = a;
        }
    }
}
