class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        // 순서돌면서 해당 판매자와 판매금액을 고려해서 처리
        for(int order = 0; order < seller.length; order++) {
            String person = seller[order];
            int originProfit = amount[order] * 100;
            
            process(person, originProfit, enroll, referral, answer);
        }
        return answer;
    }
    
    // 처리 과정
    private void process(String person, int profit, String[] enroll, String[] referral, int[] answer) {
        int myIndex = findMyIndex(person, enroll);
        
        if(referral[myIndex].equals("-")) {  // 부모가 더이상 없을 때
            if(profit < 10) {
                answer[myIndex] += profit;
                
            } else {
                answer[myIndex] += profit - (int)(profit*0.1);
            }
            return;
        } else {    // 부모가 존재할 때
            if(profit < 10) {   
                answer[myIndex] += profit;
            } else {
                int tempParentProfit = (int)(profit * 0.1);
                if(tempParentProfit < 1) {
                    answer[myIndex] += profit;
                    return;
                } else {
                    answer[myIndex] += profit - (int)(profit*0.1);
                    process(referral[myIndex], tempParentProfit, enroll, referral, answer);
                }
            }
        }
    }

    // 본인의 인덱스 찾기
    private int findMyIndex(String name, String[] enroll) {
        for(int i = 0; i < enroll.length; i++) {
            if(name.equals(enroll[i])){
                return i;
            }
        }
        return 0;
    }
}