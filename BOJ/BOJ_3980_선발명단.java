import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980_선발명단 {
	private static int[][] ManU;
	private static boolean[] isSelected;
	private static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <= T; testCase++) {
			// 테스트케이스별 초기화
			ManU = new int[11][11];
			isSelected = new boolean[11];
			result = Integer.MIN_VALUE;
			
			// 입력 [선수][포지션별능력]
			for(int i = 0; i < 11; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < 11; j++) {
					ManU[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//
			process(0, 0);
			System.out.println(result);
		}
	}
	
	private static void process(int playerIndex, int totalAbility) {
		if(playerIndex == 11) {
			result = Math.max(result, totalAbility);
			return;
		}
		
		for(int position = 0; position < 11; position++) {
			if(ManU[playerIndex][position] == 0 || isSelected[position])
				continue;
			
			isSelected[position] = true;
			process(playerIndex + 1, totalAbility + ManU[playerIndex][position]);
			isSelected[position] = false;
		}
	}
	

}
