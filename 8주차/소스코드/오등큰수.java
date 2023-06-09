import java.io.*;
import java.util.*;

/*
    boj 17299
    monotonic stack
*/

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] ngf = new int[n];
        int[] fq = new int[1000001];

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            fq[arr[i]]++;
        }

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && fq[st.peek()] <= fq[arr[i]]) {
                st.pop();
            }
            if (st.isEmpty()) {
                ngf[i] = -1;
            } else {
                ngf[i] = st.peek();
            }
            st.add(arr[i]);
        }

        for(int i = 0 ; i<n; i++){
            bw.write(ngf[i]+" ");
        }

        bw.flush();
        bw.close();
    }
}
