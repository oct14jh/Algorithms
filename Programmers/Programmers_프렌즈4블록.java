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
            deleteBlockCount = Deleting(m, n, map); // 1. 현재 맵상태에서 삭제할 블록 체크
            Arranging(m, n, map); // 2. 삭제 블록 고려해서 재정리(남은윗블록 아래로 떨어트리기)
            answer += deleteBlockCount;  // 3. 삭제한 블록 개수 계산
        } while(deleteBlockCount != 0);   // 4. 삭제할 블록이 존재하는 한, 계속 수행
        
        return answer;
    }
    
    // 1-1 :: 삭제할 블록 체크 메소드
    private int Deleting(int m, int n, char[][] map) {
        boolean[][] visited = new boolean[m][n];
        
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                if(map[i][j] != 'X')    // 프렌즈가 존재하면
                    Friends4Block(i, j, map, visited);  // 2*2블록형태 확인하러가기
            }
        }
        // 방문처리된 내용들 기반으로 이제 삭제 처리(블록삭제 표시 및 개수 처리)
        return process(m, n, visited, map);
    }
    
    // 1-2 :: 2*2 형태의 블록 확인
    private void Friends4Block(int checkI, int checkJ, char[][] map, boolean[][] visited) {
        char checkFriend = map[checkI][checkJ]; // 현재 체크할 프렌즈
        
        for(int i = checkI; i <= checkI + 1; i++) { // 기준점이 좌상단이라는 가정하, 2*2블록 여부
            for(int j = checkJ; j <= checkJ + 1; j++) {
                if(map[i][j] != checkFriend)    // 만에하나, 체크프렌즈와 다른 것이 존재하면 바로 종료
                    return;
               
            }
        }
        
        // 이 부분을 수행한다는 것은 기준점이 좌상단이라는 가정하, 2*2 블록이라는 것이므로
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
        // 스택사용이유 : 맨아래(row의 가장큰값) 부터 채워야하니 row 인덱스 0부터 접근하는 상황에선, FILO가 적절함
        Stack<Character> restFriends = new Stack<>();   
        
        for(int col = 0; col < n; col++) {  // 1열(수직으로1줄)마다 체크
            int fillIndex = m - 1;      // 배열에 바로 적용시킬 것이기에, m-1으로 초기화
            for(int row = 0; row < m; row++) {  // 수직 1열의 맨위부터 체크
                if(map[row][col] != 'X')
                    restFriends.push(map[row][col]);    // 남은 프렌즈들은 스택에 푸쉬
            }
            
            while(!restFriends.isEmpty()) { // 스택에 쌓인 애들을 아래에서부터 채우기
                map[fillIndex--][col] = restFriends.pop();
            }
            
            for(int row = 0; row <= fillIndex; row++) { // 맨위부터 채우고 남은 인덱스까지 'X' 처리
                map[row][col] = 'X';
            } 
        }
    }
}