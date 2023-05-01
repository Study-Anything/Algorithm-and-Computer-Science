import java.util.*;

class Solution {
    static TreeSet<Integer>[] dp;
    
    public int solution(int N, int number) {
        int answer = -1;
        dp = new TreeSet[9];
        for(int i = 0 ; i <=8; i++){
            dp[i] = new TreeSet<>();
        }
        dp[0].add(0);
        // dp[i] : 숫자 N 을 i 번 사용해서 만들 수 있는 수들의 집합
        // 숫자 N 은 최대 8 번 사용이 가능하다.
        for(int i = 1; i <=8; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < i ; j++){
                sb.append(String.valueOf(N));
            }
            dp[i].add(Integer.parseInt(sb.toString()));
        }
        
        for(int i = 2 ; i<=8; i++){
            for(int j = 1; j<i; j++){
                TreeSet<Integer> s1 = dp[j];
                TreeSet<Integer> s2 = dp[i-j];
                
                for(int x : s1){
                    for(int y : s2){
                        dp[i].add(x+y);
                        dp[i].add(x-y);
                        dp[i].add(x*y);
                        if(y!=0)
                            dp[i].add(x/y);
                    }
                }
                
            }
        }
        
        for(int i = 1 ; i<=8; i++){
            if(dp[i].contains(number)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }

}
