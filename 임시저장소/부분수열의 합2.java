import java.io.*;
import java.util.*;

// 모든 부분수열을 다 탐색하려면 2^40 => 시간초과
// 절반을 나눠서 왼쪽, 오른쪽을 따로 따로 생각하면
// 2^21 = 2백만
// 왼쪽 절반에서 얻을 수 있는 모든 sum 을 leftSum 에 저장
// 오른쪽 절반에서 얻을 수 있는 모든 sum 을 rightSum 에 저장
// leftSum 과 rightSum 의 최대 길이는 각각 2^20 ~= 백만
// leftSum 과 rightSum 의 각각의 원소의 합이 S 가 되는 경우를 카운팅
// 이때 n^2 방법을 사용하면 백만^2 => 시간초과
// 이를 방지하기 위해 이분 탐색 혹은 투 포인터를 사용하면 nlogn = 백만 * log 백만 = 7백만 (OK!)

public class Main {
    static ArrayList<Long> leftSum = new ArrayList<>();
    static ArrayList<Long> rightSum = new ArrayList<>();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken()); // target
        arr = new int[n];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        doLeft(0, n / 2, 0);
        doRight(n / 2, n, 0);

        Collections.sort(leftSum);
        Collections.sort(rightSum);

        long ans = 0;
        // two pointer
        int left = 0;
        int right = rightSum.size() - 1;
        while (left < leftSum.size() && right >= 0) {
            long sum = leftSum.get(left) + rightSum.get(right);
            if (sum == S) {
                // count
                long lSum = leftSum.get(left);
                long lcnt = 0;
                while (left < leftSum.size() && leftSum.get(left) == lSum) {
                    left++;
                    lcnt++;
                }
                long rSum = rightSum.get(right);
                long rcnt = 0;
                while (right >= 0 && rightSum.get(right) == rSum) {
                    right--;
                    rcnt++;
                }
                ans += lcnt * rcnt;
            } else if (sum < S) {
                left++;
            } else { // sum > S
                right--;
            }

        }
        if(S==0) // 아무것도 선택하지 않는 경우를 제외해준다.
            ans--;
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
    }

    static void doLeft(int st, int end, long sum) {
        if (st == end) {
            leftSum.add(sum);
            return;
        }
        doLeft(st + 1, end, sum + arr[st]);
        doLeft(st + 1, end, sum);
    }

    static void doRight(int st, int end, long sum) {
        if (st == end) {
            rightSum.add(sum);
            return;
        }
        doRight(st + 1, end, sum + arr[st]);
        doRight(st + 1, end, sum);
    }
}
