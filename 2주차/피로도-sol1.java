class Solution {
    static int maxcnt = 0;
    static boolean[] visited;
    static int[] order;
    static int n;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        n = dungeons.length;
        visited = new boolean[n+1];
        order = new int[n+1];
        
        dfs(n, dungeons, 0, k);
        answer = maxcnt;
        return answer;
    }
    public void dfs(int n, int[][] dungeons, int depth, int k){
        if(depth==n){
            // update
            int cnt = 0;
            for(int i = 0 ; i < n ; i++){
                int now = order[i];
                int need = dungeons[now-1][0];
                int minus = dungeons[now-1][1];
                if(k>=need){
                    k-=minus;
                    cnt++;
                }
            }
            maxcnt = Math.max(cnt, maxcnt);
            return;
        } else {
            for(int i = 1 ;i<=n; i++){
                if(visited[i]==false){
                    visited[i]=true;
                    order[depth]=i;
                    dfs(n, dungeons, depth+1, k);
                    visited[i]=false;
                }
            }
        }
    }
}
