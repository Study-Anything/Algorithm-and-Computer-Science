import java.util.*;

class Solution {    
    static int answer=-1;
    public int solution(int N, int number) {
        dfs(0, 0, N, number);
        return answer;
    }
    public void dfs(int cnt, int prev, int N, int target){
        if(cnt > 8){
            return;
        }
        if(prev==target){
            if(answer>cnt || answer==-1)
                answer = cnt;
            return;
        }
        int t = 0;
        for(int i =0 ; i< 8; i++){
            t = t*10+N;
            dfs(cnt+1+i, prev+t, N, target);
            dfs(cnt+1+i, prev-t, N, target);
            dfs(cnt+1+i, prev/t, N, target);
            dfs(cnt+1+i, prev*t, N, target);
        }
    }
}
