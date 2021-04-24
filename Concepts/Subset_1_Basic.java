import java.util.*;

public class Subset_1_Basic {
	static int N, totalCnt;	// 원소개수입력, 총부분집합의 숫자
	static int[] input;
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++)
			input[i] = sc.nextInt();
	
		generateSubset(0);
		System.out.println("총 경우의 수 : " + totalCnt);
	}
	
	private static void generateSubset(int cnt) {
		
		if(cnt == N) {	// 기저조건
			++totalCnt;
			for(int i=0; i<N;i++) {
				System.out.print(isSelected[i]? input[i] : "X" );	// 포함안되어있으면(False면) X로 출력
				System.out.print("\t");
			}
			System.out.println();
			return;
		}
		
		//부분집합 구성에 포함 
		isSelected[cnt] = true;		// cnt는 처리한 개수이면서도 인덱스라 배열 접근하면서 값 체크
		generateSubset(cnt+1);		// 포함된 원소의 개수가 아님.. 고려해보고있는 원소의 개수..(포함할지미포함할지모른다는 말)
									//몇개인지 알고싶다면, isSelected의 true개수 체크하면됨.
									
		//부분집합 구성에 미포함
		isSelected[cnt] = false;
		generateSubset(cnt+1);	// 다음원소로 넘어가는 메소도임.
	}
}