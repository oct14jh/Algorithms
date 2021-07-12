import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        String answer = "";

        int size = s.length();
        
        if(size % 2 == 0) {
            answer = s.substring(size/2 - 1, size/2 + 1);
        } else {
            answer = s.substring(size/2, size/2 + 1);
        }
        return answer;
    }
}