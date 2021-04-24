import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_Basic {
	private static int N, E;
	private static boolean adjMatrix[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점수
		E = sc.nextInt(); // 간선수
		
		adjMatrix = new boolean[N][N];
		
		for(int i = 0; i < E; i++) {
			System.out.print((i+1) + "번째 간선 연결 숫자 2개 입력 : ");
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = true;
		}
		System.out.println("============");
		System.out.println("BFS : 큐 나올 때 방문 처리한 경우 => 중복되는 것을 확인 (즉, 잘못됨)");
		BFS1();
		System.out.println("BFS : 큐 들어갈 때 방문 처리한 경우 => 정상처리 !!!");
		BFS2();
	}

	// 큐에 값이 삭제될 때, 방문 처리한 bfs메소드
	private static void BFS1() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean visited[] = new boolean[N];
		int current = 0;
		
		queue.offer(current);
		while(!queue.isEmpty()) {
			current = queue.poll();
//			if(visited[current])	방문한적이 있는 값이면 Skip
//				continue;			이 내용을 사용해주어야 큐에 중복값 안들어가고 정상처리됨.
			visited[current] = true;
			System.out.println((char)(current + 65));
			
			for(int i = 0; i < N; i++) {
				if(adjMatrix[current][i] && !visited[i]) {
					queue.offer(i);
				}
			}
			
		}
	}

	// 큐에 값이 삽입 될 때, 방문 처리한 bfs메소드
	private static void BFS2() {
		Queue<Integer> queue = new LinkedList<>();
		boolean visited[] = new boolean[N];
		int current = 0;

		queue.offer(current);
		while(!queue.isEmpty()) {
			current = queue.poll();
			System.out.println((char)(current + 65));
			
			for(int i = 0; i < N; i++) {
				if(adjMatrix[current][i] && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}
}