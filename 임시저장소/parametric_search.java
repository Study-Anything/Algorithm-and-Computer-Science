import java.io.*;
import java.util.*;

// BOJ 2805

public class Main {
    static int n;
    static int target;
    static long[] namu;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        target = Integer.parseInt(stk.nextToken());
        namu = new long[n];

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            namu[i] = Long.parseLong(stk.nextToken());

        long low = 0; // check(low) is always true
        long high = (long) 1e9; // check(high) is always false

        while (low + 1 < high) {
            long mid = (low+high)/2;
            if(check(mid))
                low = mid;
            else high = mid;
        }
        bw.write(low+"\n");
        bw.flush();
        bw.close();
    }
    public static boolean check(long t){
        long sum = 0;
        for(long ele : namu){
            if(ele >= t){
                sum += (ele-t);
            }
        }
        return sum >= target;
    }
}
