import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        for(int ele : getNGEs(arr)){
            bw.write(ele+" ");
        }
        bw.flush();
        bw.close();
    }

    public static int[] getNGEs(int[] arr) {
        int[] nges = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        for(int i = n-1 ; i >=0 ; i--){
            while(!st.isEmpty() && st.peek() <= arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                nges[i] = -1;
            } else {
                nges[i] = st.peek();
            }
            st.add(arr[i]);
        }
        return nges;
    }
}
