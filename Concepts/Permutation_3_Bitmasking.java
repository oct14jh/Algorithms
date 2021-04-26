import java.util.Arrays;
import java.util.Scanner;

public class Permutation_3_Bitmasking {
	
	static int N, R;
	static int[] input, numbers;
//	static boolean[] selected;	
	static int totalCount;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
//		selected = new boolean[N];
		numbers = new int[R];
		
		for(int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		permutation(0,0);	// 아무것도 선택되지않은 상태이니 flag변수자리에도 0
		System.out.println("총 경우의 수 : " + totalCount);
	}
	
	
	// 어떤 작업한 것을 이제 되돌리지않고, 계쏙 원래의 값을 쓸 수 있도록 하기 위해 flag라는 변수 사용
	private static void permutation(int cnt, int flag) {				
		if(cnt == R) {
			totalCount++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 0; i < N; ++i) {
//			if(selected[i]) continue;
			
			// 내가 원하는 자리가 1인지만 체크,, 0이 아니면 내가 보고싶었던 자리가 1이라는 것이므로, 사용되어있다는 말이다.
			if((flag & (1<<i)) != 0) continue;	
			
			numbers[cnt] = input[i];	//  if문에서 걸러지지않았다면, 0이므로 사용가능하다는 말임. 그래서 집어넣어준다.
			
//			selected[i] = true;
			/* flag | (1<<i) 요작업이 바로 true해주는 작업이다. 밑의 false작업은 필요가없다.. 왜? flag는 그대로, 메소드 호출시 전달되는 값은 flag가 연산된 결과 */
			permutation(cnt + 1, flag | (1<<i)); 
//			selected[i] = false;
		}
	}
}

/*  number &= ~(1<<x); 이렇게 하면 지정비트 0으로 변경은 가능합니다. */