class Solution {
    static int answer=0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0, true);
        dfs(numbers, target, 0, 0, false);
        return answer;
    }
    public void dfs(int[] numbers, int target, int psum, int depth, boolean flag){
        if(flag==true){
            psum+=numbers[depth];
        }
        if(flag==false){
            psum-=numbers[depth];
        }
        if(numbers.length-1!=depth){
            dfs(numbers, target, psum, depth+1, true);
            dfs(numbers, target, psum, depth+1, false);
        } else if (psum==target) {
            // numbers.length == depth && psum==target
            answer++;
        }
    }
}
