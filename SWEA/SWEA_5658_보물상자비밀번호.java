import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class SWEA_5658_보물상자비밀번호 {
    private static int N, K, oneLineSize;
    private static String expr;
    private static ArrayList<Integer> allValueList;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = null;
        for(int testCase = 1; testCase <= T; testCase++) {
            allValueList = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
             
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            expr = br.readLine();
            oneLineSize = N / 4;
             
            for(int i = 0; i < oneLineSize; i++) {
                if(i >= 1) {
                    newExpr();
                }
                process();
            }
             
            System.out.println("#" + testCase + " " + answer());
        }
    }
     
 
    /* 비밀번호 문자열 재조정 */
    private static void newExpr() {
        StringBuilder sb = new StringBuilder();
        sb.append(expr.charAt(expr.length()-1));
        sb.append(expr.substring(0, expr.length()-1));
        expr = sb.toString();
    }
 
    /* 문자열 4등분 및 10진수화 해서 값 집어넣기 */
    private static void process() {
        String[] temp = new String[4];
         
        for(int i = 0; i < 4; i++) {
            temp[i] = expr.substring(i*oneLineSize, (i+1)*oneLineSize);
            int check = Integer.parseInt(temp[i], 16);
            if(!allValueList.contains(check)) {
                allValueList.add(check);
            }
        }
    }
 
    /*내림차순 정리 후 k번째 값 출력(인덱스접근이기에, -1 추가처리)*/
    private static int answer() {
        Collections.sort(allValueList, Collections.reverseOrder());
        return allValueList.get(K-1);
    }
}