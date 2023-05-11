class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int [][] dp = new int[501][501];
        dp[0][0] = triangle[0][0];
        
        for(int y = 1; y < triangle.length; y++){
            for(int x = 0; x < triangle[y].length; x++){
                // 왼쪽 끝지점이면
                if(x==0){
                    dp[y][x] = triangle[y][x] + dp[y-1][x];
                }
                // 오른쪽 끝지점이면
                else if(x==triangle[y].length-1){
                    dp[y][x] = triangle[y][x] + dp[y-1][x-1];
                }
                // 중간 지점이면
                else{
                    dp[y][x] = triangle[y][x] + Math.max(dp[y-1][x-1], dp[y-1][x]);
                }
                answer = Math.max(answer, dp[y][x]);
            }
        }
        return answer;
    }
}
