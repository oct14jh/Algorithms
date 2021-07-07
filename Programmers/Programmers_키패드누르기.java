import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        int currentL = -1;   // 좌손가락의 키패드 현 위치 (처음엔 *인데, 바로처리될거라 -1로해도 무방)
        int currentR = -2;   // 우손가락의 키패드 현 위치 (처음엔 #인데, 바로처리될거라 -2로해도 무방)
        
        // 숫자들 처리
        for(int i = 0; i < numbers.length; i++) {
            // 누를 숫자 키패드 putKey
            int putKey = numbers[i];
            
            // 맨 처음 시작 시, 2,5,8,0 숫자 체킹이라면 어떤손잡이인지로 판단(거리는 좌우간 동일하기때문)
            if ((i == 0) && (putKey == 2 || putKey == 5 || putKey == 8 || putKey == 0)) {
                if (hand.equals("left")) { currentL = putKey; sb.append("L"); continue; }
                if (hand.equals("right")) { currentR = putKey; sb.append("R"); continue; }
            }
            
            // 1,4,7은 무조건 왼손 처리
            if(putKey == 1 || putKey == 4 || putKey == 7) {
                currentL = putKey; sb.append("L");
                continue;
            }
            
            //3,6,9는 무조건 오른손 처리
            if(putKey == 3 || putKey == 6 || putKey == 9) {
                currentR = putKey; sb.append("R");
                continue;
            }
            
            //2,5,8,0은 거리로 누를 손가락 처리
            if(putKey == 2 || putKey == 5 || putKey == 8 || putKey == 0) {
                // 거리는 배열을 활용해 체킹 (어느손으로 눌러서, 그손이 현재어딨는지)
                String selectHand = checkDistance(putKey, currentL, currentR, hand);
                if(selectHand.equals("left")) { currentL = putKey; sb.append("L"); }
                else { currentR = putKey; sb.append("R"); }
            }
     
        }
        // 출력
        // answer = sb.toString();
        return sb.toString();   
    }

    // 거리계산을 통한 어느손가락으로 눌렀고, 그손가락이 현재 어딨는지 출력
    private String checkDistance(int putKey, int left, int right, String hand) {
        int[] keyIndex = new int[2];
        int[][] phone = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2} };
        int[][] fingerIndexInPhone = new int[2][2]; // 좌(0),우(1) 기준으로 r(0),c(1)
        
        // 손가락 및 키패드 위치 파악 (인덱스)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3;j++) {
                if(phone[i][j] == left) {
                    fingerIndexInPhone[0][0] = i;
                    fingerIndexInPhone[0][1] = j;
                }
                if(phone[i][j] == right) {
                    fingerIndexInPhone[1][0] = i;
                    fingerIndexInPhone[1][1] = j;
                }
                if(phone[i][j] == putKey) {
                    keyIndex[0] = i;
                    keyIndex[1] = j;
                }
            }
        }
        
        // 직접 거리 계산 (상하좌우, 1칸씩이므로 바로 이렇게 계산)
        int leftDistance = Math.abs(Math.abs(fingerIndexInPhone[0][0] - keyIndex[0]) + Math.abs(fingerIndexInPhone[0][1] - keyIndex[1]));
        int rightDistance = Math.abs(Math.abs(fingerIndexInPhone[1][0] - keyIndex[0]) + Math.abs(fingerIndexInPhone[1][1] - keyIndex[1]));
        // System.out.println(putKey + " => " + leftDistance + " : " + rightDistance + ", " + hand);
        if (leftDistance < rightDistance) return "left";
        if (leftDistance > rightDistance) return "right";
        return hand;
    }
}