class Solution {
    static int maxcnt = 0;
    static boolean[] visited;
    static int n;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        n = dungeons.length;
        visited = new boolean[n+1];
        
        dfs(k, dungeons, n, 0);
        
        answer = maxcnt;
        return answer;
    }
    public void dfs(int piro, int [][] dungeons, int n, int cnt){
        for(int i = 0; i<n; i++){
            if(visited[i]==false && piro >= dungeons[i][0]){
                visited[i]=true;
                dfs(piro-dungeons[i][1], dungeons, n, cnt+1);
                visited[i]=false;
            }
        }
        maxcnt = Math.max(cnt, maxcnt);
    }
}
