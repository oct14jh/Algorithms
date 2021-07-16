import java.util.*;
import java.io.*;

class Solution {
    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        int res = 0;
        Map<String, String> result = new HashMap<String,String>();
        result.put("ze", "0");
        result.put("on", "1");
        result.put("tw", "2");
        result.put("th", "3");
        result.put("fo", "4");
        result.put("fi", "5");
        result.put("si", "6");
        result.put("se", "7");
        result.put("ei", "8");
        result.put("ni", "9");
        
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if(i < s.length() - 1) answer.append(s.substring(i,i+1));
                else answer.append(s.substring(i));
            } else {
                String tempWord = s.substring(i, i+2);
                if(result.containsKey(tempWord)) {
                    String check = result.get(tempWord);
                    answer.append(check);
                    if(check.equals("1") || check.equals("2") || check.equals("6"))
                       i += 2;
                    else if(check.equals("0") || check.equals("4") || check.equals("5") || check.equals("9"))
                        i += 3;
                    else
                        i += 4;
                }
            }
                
        
        }
        res = Integer.parseInt(answer.toString());
        return res;
    }
}