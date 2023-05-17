import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long right = -1;
        for(int i = 0 ; i < times.length; i++)
            if(right <= times[i])
                right = times[i];
        right *= n;
        
        long left = 0;
        long mid = 0;
                
        while(left<=right){
            mid = (left + right)/2;
            // mid 만큼의 시간만 주어졌을때 몇 명이 입국심사를 통과할 수 있는가?
            long cnt = 0;
            
            for(int i = 0 ; i < times.length; i ++){
                cnt += mid/times[i];
            }
            
            if(cnt < n){ // mid 시간 만으로는 n 명을 검사할 수 없다.
                // 범위를 늘린다.
                left = mid + 1;
            } else { // mid 시간 만으로 n 명 이상을 검사할 수 있다.
                // 범위를 줄인다.
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
