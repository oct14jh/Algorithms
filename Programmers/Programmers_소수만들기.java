import java.util.*;

class Solution {
    private static int answer = 0;
    private static boolean[] visited;
    public int solution(int[] nums) {
        visited = new boolean[nums.length];
        Combination(nums, 0, 0, 0);
        
        return answer;
    }
    
    // 조합
    private void Combination(int[] nums, int index, int count, int sum) {
        if(count == 3) {
            if(isPrime(sum))
                answer++;
            return;
        }
        
        for(int i = index; i < nums.length; i++) {
            if(visited[i])
                continue;
            visited[i] = true;
            Combination(nums, i+1, count+1, sum+nums[i]);
            visited[i] = false;
        }
    }
    
    // 소수 판별
    private boolean isPrime(int value) {
        if(value < 2)
            return false;
        else if(value == 2) {
            return true;
        } 
        else {
            for(int i = 2; i < value; i++) {
                if(value % i == 0)
                    return false;
            }
            return true;
        }
    }
}