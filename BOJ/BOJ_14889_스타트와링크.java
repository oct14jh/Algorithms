package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author khyun
 * N명의 인원에서 스타트와 링크라는 2팀으로 갈리는데, 능력치 최소합을 구하고자 하는 문제다.
 * 2팀으로 갈리기에, 한팀을 count 수를 N/2 만큼 뽑았으면, 남은 N/2는 자동으로 팀이 된다. (true/false로 팀구분)
 * 고로 count==4일 때, true 인 팀원들의 능력치합들을 구하고, false인 팀원들의 능력치합을 구해서 최소합을 최종적으로 도출하는 것이다.
 * 추가적으로 알아야하는 개념은, 조합을 생각하면 됨. (순서관계없이 팀원 구성원들의 능력치만 고려하면 됨)
 */

public class BOJ_14889_스타트와링크_S3 {
	static int N, result=Integer.MAX_VALUE;		// N은 인원 수, result는 최소값을 출력하고싶기때문에 초기선언값으로는 정수형최댓값 설정
	static int[] startTeam, linkTeam;			// 스타트팀, 링크팀 배열로 나누는데 N/2크기만큼 설정해줄것이다.
	static int[][] map;							// 2차원 배열(능력치)
	static boolean[] visited;					// boolean형 배열을 써줘서 true/false로 팀구분을 하기 위함
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		visited = new boolean[N];
		startTeam = linkTeam = new int[N/2];
		
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0,0);				// N명(0~N-1)에서 '0'이라고 지칭되는 사람부터 접근을 하되, 초기count값도 '0' [count수가 N/2 되면 팀 나눠진것]
		
		System.out.println(result);
		br.close();
	}
	
	private static void combination(int index, int count) {
		if(count == N/2) {	// true로 체크된 사람을 스타트팀이라 가정하였을 때, N/2만큼 팀이 차면 팀이 다 나눠진 것이기에 기저조건으로 씀.
			int tempStartSum = 0, tempLinkSum = 0, tempGap = 0;	// 팀이 짜여졌다는 가정하 이제 계산을 하겠다.
			
			for(int i = 0; i < N; i++) {				// 전체 탐색하면서
				for(int j = 0; j < N; j++) {
					if(visited[i] && visited[j])		// 같은 팀원일때만 능력치가 반영되어야 하므로 visited[i] 와 visited[j]라는 배열 따로 조건걸었음(i,j가 한팀일때 능력치 반영)
						tempStartSum += map[i][j];		// 해당 S(i,j)의 점수를 스타트팀 점수에 반영.. map[j][i]점수는? => (중첩반복이므로 결국 S(j,i)의 값도 들어가게될것임)
					if(!visited[i] && !visited[j])		// true가 스타트팀이라면, false는 링크팀이기에 위와 같은 개념으로 계산
						tempLinkSum += map[i][j];
				}
			}
			
			tempGap = Math.abs(tempStartSum - tempLinkSum);		// 팀 종합능력치 점수 차를 구한다. (절대값필수)
			if(result > tempGap)								// result에 더 작은 점수 차 반영
				result = tempGap;
			return;					
		}
		
		for(int i = index; i < N; i++) {				// 조합개념이기에, 이러한 개념 사용
			if(!visited[i]) {					// 스타트 팀인 친구가 아니라면
				visited[i] = true;				// 스타트팀으로 소속시켜주고
				combination(i+1, count+1);		// 해당 인원을 제외한 다른 친구들 중 또 스타트팀을 뽑기위한 재귀
				visited[i] = false;				// 소속시킨 해당인원을 링크팀이라고 하였을 때를 가정하기 위해 false
			}
		}
	}
}
