class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        
        for(int i = 0; i < a.length; i++) {
            answer += cal(a[i], b[i]);
        }
        
        return answer;
    }
    
    private int cal(int a, int b) {
        return a*b;
    }
}