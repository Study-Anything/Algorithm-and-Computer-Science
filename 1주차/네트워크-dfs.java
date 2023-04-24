import java.util.*;

class Solution {
    static boolean [] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n+1];
        for(int i = 0 ; i < n ; i ++){
            if(visited[i]==false){
                answer++;
                dfs(i, n, computers);
            }
        }
        
        return answer;
    }
    public void dfs(int st, int n, int[][] computers){
        visited[st] = true;
        for(int i = 0; i < n ; i++){
            // 연결 성분 탐색
            if(computers[st][i] == 1 && visited[i] == false){
                dfs(i, n, computers);
            }
        }
    }
}
