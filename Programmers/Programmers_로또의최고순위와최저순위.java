import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int zeroCount = 0;
        int rightCount = 0;
        
        for(int i = 0; i < 6; i++) {
            if(lottos[i] == 0)
                zeroCount++;
            for(int j = 0; j < 6; j++) {
                if(lottos[i] == win_nums[j])
                    rightCount++;
            }
        }
        answer[0] = rank(rightCount + zeroCount);
        answer[1] = rank(rightCount);
        
        return answer; // 최고등수, 최저등수
    }
    
    private int rank(int rightNumCount) {
        int result = 7 - rightNumCount;
        if(result >= 6)
            return 6;
        else 
            return result; 
    }

    
}