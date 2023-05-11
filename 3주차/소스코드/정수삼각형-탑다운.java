class Solution {
    int [][] dp;
    int n;
    public int solution(int[][] triangle) {
        int answer = 0;
        dp = new int[501][501];
        n = triangle.length;
        for(int i = 0 ; i < n; i++){
            dp[n-1][i]=triangle[n-1][i];
        }
        return answer = find(0, 0, triangle);
    }
    public int find(int row, int col, int [][] tri){
        if(row==n-1){
            return dp[row][col];
        }
        if(dp[row][col]==0){
            dp[row][col] = tri[row][col] + Math.max(
            find(row+1, col, tri), find(row+1, col+1, tri)
                );
        }
        return dp[row][col];
    }
}
