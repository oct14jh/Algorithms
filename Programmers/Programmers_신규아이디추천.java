import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        // 1. 대->소문자 변환
        sb.append(new_id.toLowerCase());
        
        // 2. 조건 글자 외 모든 문자 삭제
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) >= 'a' && sb.charAt(i) <= 'z') continue;
            if(sb.charAt(i) >= '0' && sb.charAt(i) <= '9') continue;
            switch(sb.charAt(i)) {
                case '-':
                case '_':
                case '.':
                    continue;
                default :
                    sb.deleteCharAt(i);
                    i--;
            }
        }
        
        // 3. .. 된건 .으로 변환
        for(int i = 0; i < sb.length() - 1; i++) {
            if(sb.charAt(i) == '.' && sb.charAt(i+1) == '.') {
                sb = sb.deleteCharAt(i+1);
                i--;
            }
        }
        
        // 4. .이 맨앞/맨뒤면 삭제
        if(sb.charAt(0) == '.') sb = sb.deleteCharAt(0);
        int lastIndex = sb.length();
        if(lastIndex != 0) {
            if(sb.charAt(sb.length()-1) == '.') sb = sb.deleteCharAt(sb.length()-1);
        }
        
        // 5. 빈 문자열이면, 'a' 추가
        if(sb.length() == 0) sb.append('a');
        
        // 6. 16글자 이상이면, 앞에서 15개만 인식
        if(sb.length() >= 16) sb = sb.delete(15, sb.length()+1);
        
        //여기서 이제 임의로 내가 하나 추가 (15개만인식되어 결국 끝에 .이 생길경우)
        if(sb.charAt(sb.length()-1) == '.')
            sb = sb.deleteCharAt(sb.length()-1);
        
        // 7. 2글자 이하면, 끝글자 무한반복으로 3글자 이상만들기
        while(sb.length() <= 2) {
            sb.append(sb.charAt(sb.length()-1));
        }
        
        
        answer = sb.toString();
        
        return answer;
    }
}