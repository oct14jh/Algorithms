import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_5653_줄기세포배양_모의역테 {
	// 상태를 문자열로 일일이 쓰기 귀찮기 때문에 상태를 상수로 설정해둔다.
	private static final int inActive = 0;
	private static final int Active = 1;
	private static final int Dead = 2;
	
	private static class Cell implements Comparable<Cell> {
		int r;
		int c;
		int x; // 생명력
		int time; // 시간
		int status; // 상태값

		 public Cell(int r, int c, int x) {
			super();
			this.r = r;
			this.c = c;
			this.x = x;
			this.time = -x;	// -x~-1 까진 비활성화 시간.. 0인 순간 활성화시작... +x가되면 죽음처리되어야함.
//			this.status = 0... 상태는 기본값이 0으로 비활성이기 때문에 건드릴 필요가 없다.
		}
		
		// 이거쓰면 양수와 음수 섞여서 계산하게 되는경우를 고려해서 체킹해줌 (즉, 양수 - 음수 이러면 양수 + 양수 되는걸 즉, 오버/언더플로우 방지)
		@Override
		public int compareTo(Cell o) {
			return Integer.compare(o.x, this.x);	
		}
		
		public void flowTime() {
			// 모든 세포에 흐른 시간 처리
			time++;
			if(time == 0 || time == x) // 활성상태가 되었다거나, 활성시간을 다해서 죽거나
				status++;
			// 흐른 시간이 0 => 활성상태가 되어야함. 흐른 시간이 1 => 죽음상태가 되어야함.
		}
	}
	
	private static int N, M, K;
	private static int[][] map;
	
	private static boolean[][] visited;
	private static PriorityQueue<Cell> pQueue;
	
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N+K+2][M+K+2];	// +2를 왜했는지 생각하기.
			visited = new boolean[N+K+2][M+K+2];
			pQueue = new PriorityQueue<Cell>();
			
			//행,열 중간 위치
			int a = (N+K+2)/2;
			int b = (M+K+2)/2;
			
			// 배양 가능한 크기의 배열을 이용하여 중앙에 N*M 초기 배양 상태를 저장.
			// 와 이게 진짜핵심이다 시발... 왜 a-(N/2) , b-(M/2)하는지 잘생각하기!
			for(int i = (a-(N/2)), iCnt = 0; iCnt < N; ++i, ++iCnt) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = (b-(M/2)), jCnt = 0; jCnt < M; ++j, ++jCnt) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {	// 생명력이 있다면, 줄기세포다.
						visited[i][j] = true;	// 이미 번식된 처리
						pQueue.offer(new Cell(i, j, map[i][j]));	// pQueue에 넣기
					}
				} 
			}
			System.out.println("#" + testCase + " " + Answer());
		}
	}
	
	private static int Answer() {
		int t = K;
		int nr, nc;
		Cell cell;
		
		ArrayList<Cell> list = new ArrayList<>(); // 새로 번식된 새 줄기세포 저장할 임시 리스트
													// 다음 반복 시 처리해야하는 줄기세포 저장할 임시 리스트
		
		// K시간만큼 진행
		while(t-- > 0) {
			// 매 시간마다 줄기세포에 대해 시간의 흐름을 따른 처리
			while(!pQueue.isEmpty()) {
				cell = pQueue.poll();
				
				// 줄기세포의 상태가 비활성화일 때 => 그냥 while의 t만 처리만 되면 되기때문에 아무런 작업 안취한다...ㅎ
				// 줄기세포의 상태가 활성상태이며, 활성화된 첫번째 시간에만 번식!!!
				if(cell.status==Active && cell.time == 0) {
					for(int move = 0; move < 4; move++) { // 4방으로 자신과 같은 생명력을 갖는 줄기세포 번식
						nr = cell.r + dr[move];
						nc = cell.c + dc[move];
						if(visited[nr][nc]) continue;	// 다른 세포가 존재하면 번식 불가
						
						visited[nr][nc] = true; // 무조건 번식시킴 (뒤에 오는 줄기세포는 생명력이 같거나 작다)
						list.add(new Cell(nr, nc, cell.x)); // 새로운 줄기세포 자신을 번식시키는 모줄기세포와 같은 생명력으로 생성
					}
				}

				cell.flowTime();
				// 이 주석부분은 Cell 내부 메소드로 처리.. 그래서 바로 윗라인 한줄로 끝난거임
//				// 모든 세포에 흐른 시간 처리
//				cell.time++;
//				if(cell.time == 0 || cell.time == cell.x) // 활성상태가 되었다거나, 활성시간을 다해서 죽거나
//					cell.status++;
//				// 흐른 시간이 0 => 활성상태가 되어야함. 흐른 시간이 1 => 죽음상태가 되어야함.
				
				if(cell.status == Dead)
					continue;
				list.add(cell);
			}// 매 시간 처리
			
			pQueue.addAll(list); // 죽지 않고 살아있는 줄기세포들을 pQueue에 넣어 다음 처리르 할 수 있도록 한다.
			list.clear(); // 다음 처리를 위한 리스트 초기화
		}
		
		return pQueue.size();
	}

}