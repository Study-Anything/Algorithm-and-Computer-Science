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
            stk = new StringTokenizer(br.readLine());
            boolean[] visited = new boolean[20000];

            int A = Integer.parseInt(stk.nextToken()); // 레지스터의 초깃값
            int B = Integer.parseInt(stk.nextToken()); // 레지스터의 최종값

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(A, ""));
            boolean flag = false;
            while (!q.isEmpty()) {
                if (flag) break;
                Node now = q.poll();
                int t = now.num;
                String str = now.instruction;
                for (Node nn : new Node[]{new Node(doD(t), str + "D"), new Node(doS(t), str + "S"),
                        new Node(doL(t), str + "L"), new Node(doR(t), str + "R")}) {
                    if (visited[nn.num]) continue;
                    if (nn.num == B) {
                        bw.write(nn.instruction + "\n");
                        flag = true;
                        break;
                    }
                    q.add(nn);
                    visited[nn.num] = true;
                }
            }
        }

        bw.flush();
        bw.close();
    }

    public static class Node {
        int num;
        String instruction;

        public Node(int num, String instruction) {
            this.num = num;
            this.instruction = instruction;
        }
    }

    public static int doD(int n) {
        return (2 * n) % 10000;
    }

    public static int doS(int n) {
        return n == 0 ? 9999 : n - 1;
    }

    public static int doL(int n) { // 1234 -> 2341
        return (n % 1000) * 10 + (n / 1000);
    }

    public static int doR(int n) { // 1234 -> 4123
        return n / 10 + (n % 10) * 1000;
    }
}
