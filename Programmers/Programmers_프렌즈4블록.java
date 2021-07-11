import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n]; // 블록 맵 생성
        int deleteBlockCount = 0;
        
        // 입력 맵 처리
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        do {
            // 1. 현재 맵상태에서 삭제할 블록 체크
            deleteBlockCount = Deleting(m, n, map); 

            // 2. 삭제 블록 고려해서 재정리(남은윗블록 아래로 떨어트리기)
            Arranging(m, n, map); 

            // 3. 삭제한 블록 개수 계산
            answer += deleteBlockCount;  
        } while(deleteBlockCount != 0);  
        return answer;
    }
    
    // 1-1 :: 삭제할 블록 체크 메소드
    private int Deleting(int m, int n, char[][] map) {
        boolean[][] visited = new boolean[m][n];
        
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                if(map[i][j] != 'X')  
                    Friends4Block(i, j, map, visited);  
            }
        }
        return process(m, n, visited, map);
    }
    

    private void Friends4Block(int checkI, int checkJ, char[][] map, boolean[][] visited) {
        char checkFriend = map[checkI][checkJ]; // 현재 체크할 프렌즈
        
        for(int i = checkI; i <= checkI + 1; i++) { 
            for(int j = checkJ; j <= checkJ + 1; j++) {
                if(map[i][j] != checkFriend)    
                    return;
               
            }
        }
        

        for(int i = checkI; i <= checkI + 1; i++) {
            for(int j = checkJ; j <= checkJ + 1; j++) {
                visited[i][j] = true;   // 해당 공간들을 방문처리해줌.
            }
        }
    }
    
    // 1-3 :: 2*2 블록 체킹된것들 'X'로 처리하고 개수 처리
    private int process(int m, int n, boolean[][] visited, char[][] map) {
        int tempResult = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j]) { 
                    map[i][j] = 'X';
                    tempResult++;
                } 
            }
        }
        return tempResult;
    }
    
    // 2 :: 맵 정렬(아래로 떨어트리기)
    private void Arranging(int m, int n, char[][] map) {
        Stack<Character> restFriends = new Stack<>();   
        
        for(int col = 0; col < n; col++) {  
            int fillIndex = m - 1;    
            for(int row = 0; row < m; row++) {  
                if(map[row][col] != 'X')
                    restFriends.push(map[row][col]);    
            }
            
            while(!restFriends.isEmpty()) { 
                map[fillIndex--][col] = restFriends.pop();
            }
            
            for(int row = 0; row <= fillIndex; row++) { 
                map[row][col] = 'X';
            } 
        }
    }
}