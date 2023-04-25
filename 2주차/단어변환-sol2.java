import java.util.*;

class Solution {
    static boolean[] visited;
    static int n;
    static int answer;
    public int solution(String begin, String target, String[] words) {
        n = words.length;
        visited = new boolean[n+1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(begin, 0));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            if(now.str.equals(target)){
                // done
                answer = now.cnt;
                break;
            } else {
                // search
                for(int i = 0 ; i < words.length; i++){
                    if(visited[i]==false && now.isitGood(words[i])){
                        visited[i] = true;
                        q.offer(new Node(words[i], now.cnt+1));
                    }
                }
            }
        }
        
        return answer;
    }

    
    static class Node{
        String str;
        int cnt;
        public Node(String str, int cnt){
            this.str = str;
            this.cnt = cnt;
        }
        
        public boolean isitGood(String next){
            int cnt = 0;
            int n = this.str.length();
            for(int i = 0; i < n; i++){
                if(this.str.charAt(i)!=next.charAt(i)){
                    cnt++;
                }
            }
            return cnt==1 ? true : false;
        }
    }
}
