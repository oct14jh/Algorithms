import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        /* 1벌은 가져야한다. 0벌은 옷이없어서 빌려야한다는 것이다. */
        int[] students = new int[n];
        for(int i = 0; i < n; i++)
            students[i] = 1;    // 모든 사람들은 체육복 1개씩 가졌다.
        
        for(int studentNo : reserve)
            students[studentNo - 1]++; // 여분 1벌 씩 가진 사람은 추가
        
        for(int studentNo : lost)
            students[studentNo - 1]--; // 도난당한사람은 1벌씩 제거
        
        for(int i = 0; i < n; i++){
            if(students[i] == 0){ //빌려야하는 사람 기준으로
                // 왼쪽 사람이 2개 가지고 있다면 하나 받기
                if(i-1 >= 0 && students[i-1] > 1){
                    students[i]++;
                    students[i-1]--;
                    continue;
                }
                // 오른쪽 사람이 2개 가지고 있다면 하나 받기
                if(i+1 < n && students[i+1] > 1){
                    students[i]++;
                    students[i+1]--;
                }
            }
        }

        // 1개 이상 체육복 가지고 있는 사람 카운트
        for(int i = 0; i < students.length; i++){
            if(students[i] > 0)
                answer++;
        }
        
        return answer;
    }
}