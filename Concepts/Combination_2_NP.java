
import java.util.Arrays;
import java.util.Scanner;

public class Combination_2_NP {
	static int N, R;
	static int[] input, p;		// 추가배열 p !!!
	static int totalCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		p = new int[N];
		
		for(int i = 0; i < N; i++) 
			input[i] = sc.nextInt();
		
		
		//전처리 R개만큼만 추가배열 p의 '1' 채워주기
		int cnt = 0;
		while(++cnt<=R) {
			p[N-cnt] = 1;			// 이거 그림그려서 생각 해 볼것.. 인덱스 잘 생각해야함. 
												// 이거 헷갈리면 그냥 맨앞인덱스부터 R개만큼 1로 채워주면됨..
												// (어차피 arrays.sort하고 시작하니깐)
		}
		
		Arrays.sort(p);     // 최초 정렬 한번 해준다.
		do {
			for(int i = 0; i < N; i++) {
				if(p[i] == 1) 	// 배열 p에서 1인부분을 체킹해서 숫자배열에 적용시켜 출력하는 것이다		
					System.out.println(input[i]);
			}
			System.out.println();
		} while(nextPermutation(p));
	}
	

	private static boolean nextPermutation(int[] numbers) {				
		//step1. 꼭대기 찾기
		int i = N-1;
		while(i>0 && numbers[i-1] >= numbers[i]) --i;	// 인덱스 생각해보자.. 0보다 큰값이되~ ㄱ꼭대기의 가장 큰위치 파악해야하니 --i
		if(i==0) return false;		// 마지막 순열의 상태이면 다음 순열 없음
		
		//step2. i-1 위치와 교환할 다음단계 큰 수 뒤쪽에서 찾기
		int j = N-1;
		while(numbers[i-1] >= numbers[j]) --j;		// i-1보다 큰 j는 항상 존재한다. => 꼭대기 i가 있었기 때문에 i-1이 존재하는 것이기때문에
		
		//step3. i-1위치값과 j 위치값 교환
		swap(numbers, i-1, j);
		
		//step4. i위치부터 맨 뒤까지 오름차순 정렬 (내림차순 되어있는 것을 오름차순으로 정렬)
		int k = N-1;	// 맨뒤를 가리키는 k
		while(i<k) {	// 둘이 만나지 않았다는 조건
			swap(numbers, i++, k--);	// 증감은.. 스왑할 앞과 뒤의 인덱스를 이제 좁혀야한다했으니
		}

		return true;
	}
	
	public static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}