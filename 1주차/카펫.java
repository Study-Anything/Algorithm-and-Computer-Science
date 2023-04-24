import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        ArrayList<Integer> list = new ArrayList<>();
        
        int xy = brown+yellow;
        
        for(int i = 2; i<=xy; i++){
            if(xy%i==0){
                // i 가 xy 의 약수이면 add
                list.add(i);
            }
        }
        // list 에 대해서 투포인터
        int left=0;
        int right=list.size()-1;
        int leftval=0;
        int rightval=0;
        while(left<=right){
            leftval=list.get(left);
            rightval=list.get(right);
            int mul=(leftval-2)*(rightval-2);
            if(mul==yellow){
                answer[0] = rightval;
                answer[1] = leftval;
                break;
            } else if(mul<yellow){
                left++;
            } else {
                right--;
            }
        }
        
        return answer;
    }
}
