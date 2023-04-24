import java.util.*;

class Solution {
    static boolean [] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n+1];
        for(int i = 0 ; i < n ; i ++){
            if(visited[i]==false){
                // 방문하지 않은 노드이면
                visited[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                
                answer++;
                while(!q.isEmpty()){
                    int st = q.poll();
                    for(int j = 0; j <n; j++){
                        if(computers[st][j]==1&&visited[j]==false){
                            visited[j] = true;
                            q.offer(j);
                        }
                    }
                }
            }
        }   
        return answer;
    }
}
