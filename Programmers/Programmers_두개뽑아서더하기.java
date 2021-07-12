import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        List<Integer> addList = new ArrayList<>();
        
        for(int i = 0; i < numbers.length - 1; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                int result = numbers[i] + numbers[j];
                
                if(!addList.contains(result))
                    addList.add(result);
            }
        }
        
        Collections.sort(addList);
        int[] answer = new int[addList.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = addList.get(i);
        }
        return answer;
    }
}