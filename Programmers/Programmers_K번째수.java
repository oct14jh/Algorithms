import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int n = 0; n < commands.length; n++) {
            int i = commands[n][0];
            int j = commands[n][1];
            int k = commands[n][2];
            
            int[] temp = new int[j-i+1];
            int count = 0;
            for(int index = i-1; index < j; index++) {
                temp[count++] = array[index];
            }
            Arrays.sort(temp);
            answer[n] = temp[k-1];
        }
        
        return answer;
    }
}