import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> list = new HashMap<>();
        
        for (String name : participant) 
            list.put(name, list.getOrDefault(name, 0) + 1);
        for (String name : completion) 
            list.put(name, list.get(name) - 1);

        for (String key : list.keySet()) {
            if (list.get(key) != 0){
                answer = key;
            }
        }        
        return answer;
    }
}