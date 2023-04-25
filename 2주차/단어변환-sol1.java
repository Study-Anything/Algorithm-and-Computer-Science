import java.util.*;

class Solution {
    static boolean[] visited;
    static int n;
    static int answer=99;
    static boolean done;
    public int solution(String begin, String target, String[] words) {
        n = words.length;
        visited = new boolean[n+1];
        dfs(begin, target, words, 0);
        
        return done==true ? answer : 0;
    }
    public void dfs(String begin, String target, String[] words, int cnt){
        if(begin.equals(target)){
            answer = Math.min(answer, cnt);
            done = true;
            return;
        } else {
            for(int i = 0 ; i < n ; i++){
                if(isitGood(begin, words[i]) && visited[i] == false){
                    // begin 을 words[i] 로 바꾸기에 적합하면?
                    visited[i] = true;
                    dfs(words[i], target, words, cnt+1);
                    visited[i] = false;
                }
            }
        }
    }
    public boolean isitGood(String src, String dest){
        // src 를 dest 로 바꾸는 것이 가능한가?
        int cnt = 0;
        for(int i = 0 ; i < src.length(); i++){
            if(src.charAt(i)!=dest.charAt(i)){
                cnt++;
            }
        }
        return cnt == 1 ? true : false;
    }
}
