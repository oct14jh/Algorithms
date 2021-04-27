import java.util.*;
/* Programmers_크레인인형뽑기게임 */
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int time = 0; time < moves.length; time++) {
            for(int floor = 0; floor < board[0].length; floor++){
                if(board[floor][moves[time]-1] > 0) {
                    if(stack.isEmpty()) {
                        stack.push(board[floor][moves[time] - 1]);
                        board[floor][moves[time]-1] = 0;
                        break;
                    } else {
                      if(board[floor][moves[time]-1] == stack.peek()) {
                          stack.pop();
                          board[floor][moves[time]-1] = 0;
                          answer += 2;
                          break;
                      } else {
                          stack.push(board[floor][moves[time]-1]);
                          board[floor][moves[time]-1] = 0;
                          break;
                      }  
                    }
                }
            }
        }
        
        return answer;
    }
}