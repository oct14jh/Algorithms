class Solution {
    public String solution(int a, int b) {
        int[] date = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] day = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        String answer = "";
        int calDate = 0;
        int restDate = 0;
        
        for(int month = 0; month < a - 1; month++) {
            calDate += date[month];
        }
        restDate = (calDate+b-1) % 7;
        
        answer = day[restDate];

        return answer;
    }
}