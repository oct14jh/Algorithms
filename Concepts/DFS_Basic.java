import java.util.Scanner;

public class DFS_Basic {
	private static int N, E;
	private static boolean adjMatrix[][];
	private static boolean visited[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		E = sc.nextInt();
		
		adjMatrix = new boolean[N][N];
		N = sc.nextInt();	// 정점수
		E = sc.nextInt();	// 간선수
		
		adjMatrix = new boolean[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = true;
		}
		
		System.out.println("========================");
		DFS(0);
	}
	
	private static void DFS(int current) {
		visited[current] = true;
		System.out.println((char)(current + 65));
		
		for(int i = 0; i < N; i++) {
			if(adjMatrix[current][i] && !visited[i]) {
				DFS(i);
			}
		}
	}
}