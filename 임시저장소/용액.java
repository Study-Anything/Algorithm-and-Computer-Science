import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
      // two pointer
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

        int left = 0;
        int right = n - 1;

        int[] ans = new int[2];
        int mini = Integer.MAX_VALUE;
        while (left < right) {
            int mixed = Math.abs(arr[left] + arr[right]);

            if (mixed < mini) {
                mini = mixed;
                ans[0] = arr[left];
                ans[1] = arr[right];
            }
            if (arr[left] + arr[right] < 0) {
                left++;
            } else { // mixed >=0
                right--;
            }
        }
        bw.write(ans[0] +" "+ans[1]+"\n");
        bw.flush();
        bw.close();
    }
}
