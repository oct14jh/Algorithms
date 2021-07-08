import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        // 스테이지 수 만큼 정답 answer 배열 선언
        int[] answer = new int[N];
        
        // 스테이지 그리고 해당스테이지의 실패율을 쌍으로 묶기 위한 해시맵
        Map<Integer, Double> map = new HashMap<>();
        
        // 실패율 처리
        failureRating(N, stages, map);
        
        // 내림차순 정렬
        sortFailureRate(map, answer, N);
        return answer;
    }
    
    // 실패율 처리 메소드 (스테이지별로 각 사람 체킹 해서 실패율 처리)
    private void failureRating(int N, int[] stages, Map<Integer, Double> map) {
        // 스테이지 1부터 N까지의 각 스테이지별로 사람 수 체크 (클리어못한 사람, 더 높게(현스테이지포함) 클리어한 사람)
        for(int stage = 1; stage <= N; stage++) {
            int boonja = 0; // 현재 스테이지에 머물러 있는 즉, 도달했지만 클리어못한 사람 수
            int boonmo = 0; // 스테이지에 도달한 사람 수 (체킹중인 스테이지 이상 단계에 머물러 있는 사람 수)
            
            // 사람별로 해당 스테이지를 클리어했는지 등 처리
            for(int person = 0; person < stages.length; person++) {
                // 현재 스테이지를 클리어 하지 못한 사람 
                if(stage == stages[person])
                   boonja++;
                // 현재 체킹중인 스테이지단계 이상의 단계에 머물러 있는 사람
                if(stage <= stages[person]) {
                    boonmo++;
                }
            }
            
            // 실패율 계산 (단, 스테이지 도달한 유저가없는 경우는 실패율 '0'으로 임의처리)
            if(boonmo == 0)
                map.put(stage, (double)0);
            else
                map.put(stage, (double)boonja / (double)boonmo);
        }
    }
    
    // 내림차순 (키는 스테이지, 밸류는 실패율)
    private void sortFailureRate(Map<Integer, Double> map, int[] answer, int N) {
        for (int i = 0; i < N; i++) {
            double max = -1;    // 내림차순에 필요한 변수 max
            int maxKey = 0;     // 인덱스
            for(Integer key : map.keySet()) {   // map을 전체출력하기 위한 keySet()->키만.. entrySet()은 쌍전체.. 
                if(max < map.get(key)) {    // 각 키에 존재하는 밸류(실패율) 뽑으면서 
                    max = map.get(key);     // 한바퀴 돌때마다 가장 큰 실패율을 뽑기
                    maxKey = key;           // 가장 큰 실패율을 가진 키값을 maxKey 변수에 저장
                }
            }
            answer[i] = maxKey;             // maxKey값이 곧, 실패율 가장 큰 값을 가진 스테이지
            map.remove(maxKey);             // 처리해주었기에, 해당 <스테이지,실패율>은 삭제하고 남은 스테이지들을 계속 처리
        }
    }
}