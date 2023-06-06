import java.io.*;
import java.util.*;

// int overflow 체크 안하면 39% 에서 틀립니당

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        // 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
        Arrays.sort(arr);

        int[] ans = new int[3];
        long mini = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int t = arr[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long mixed = Math.abs((long)(arr[left] + arr[right]) + t);
                if(mixed < mini){
                    mini = mixed;
                    ans[0] = t;
                    ans[1] = arr[left];
                    ans[2] = arr[right];
                }
                if((long)(arr[left] + arr[right]) + t < 0){
                   left++;
                } else {
                    right--;
                }
            }
        }
        for(int ele : ans)
            bw.write(ele+" ");
        bw.flush();
        bw.close();
    }
}
