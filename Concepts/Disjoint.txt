import java.util.Scanner;

public class Disjoint {
	private static int N, M;
	private static int[] parents;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int state;
		int x, y;
		
		StringBuilder sb = null;
		
		for(int testCase = 1; testCase <= T; testCase++) {
			sb = new StringBuilder();
			N = sc.nextInt();
			M = sc.nextInt();
			parents = new int[N+1];
			
			for(int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
			
			for(int i = 0 ; i < M; i++) {
				state = sc.nextInt();
				x = sc.nextInt();
				y = sc.nextInt();
				switch(state) {
				case 0:
					unionSet(x, y);
					break;
				case 1:
					if(findSet(x) == findSet(y)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
					break;
				}
			}
			System.out.println("#" + testCase + " " + sb.toString());
		}
	}
	
	private static int findSet(int x) {
		if(parents[x] == x) {
			return x;
		} else {
			return parents[x] = findSet(parents[x]);
		}
	}
	
	private static void unionSet(int x, int y) {
		int xroot = findSet(x);
		int yroot = findSet(y);
		if(xroot != yroot)
			parents[yroot] = xroot;
	}
}
