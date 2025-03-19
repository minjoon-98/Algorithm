// package bj_2877;

import java.io.*;
import java.util.*;

public class Main {

    static int a, b;
    static String A, B;
    
    static String ascZero(int curr, int pow2) {
        if (pow2 == 2) {
            if (curr == 1) {
                return A; // A는 "0"
            } else if (curr == 2) {
                return B; // B는 0이 아닌 숫자
            }
        }
        if (curr > pow2 / 2) {
            return B + ascZero(curr - (pow2 / 2), pow2 / 2);
        } else {
            return A + ascZero(curr, pow2 / 2);
        }
    }
    
    static String ascNonZero(int curr, int pow2) {
        if (pow2 == 2) {
            if (curr == 1) {
                return A;
            } else if (curr == 2) {
                return B;
            }
        }
        if (curr > pow2 / 2) {
            return B + ascNonZero(curr - (pow2 / 2), pow2 / 2);
        } else {
            return A + ascNonZero(curr, pow2 / 2);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        // a = Integer.parseInt(st.nextToken());
        // b = Integer.parseInt(st.nextToken());
        a = 4;
        b = 7;
        A = "" + a;
        B = "" + b;
        
        int K = Integer.parseInt(br.readLine());
        String answer = "";
        
        // 한 숫자가 0이면, 0이 맨 앞에 오면 안됨.
        boolean isZero = (a == 0 || b == 0);

        int curr = K;
        int pow2 = 2; // !isZero이면 1자리 그룹의 경우의 수는 2 (즉, 2개의 숫자)
        while (curr - pow2 > 0) {
            curr -= pow2;
            pow2 *= 2;
        }
        
        if (isZero) {
            // 1자리에서는 비0인 숫자(B)만 가능
            if (K == 1) {
                answer = B;
            } else {
                // 결과: 맨 앞자리는 항상 B, 나머지 자릿수는 ascZero로 결정
                answer = B + ascZero(curr - 1, pow2); // isZero이면 1자리 그룹의 경우의 수는 1(0을 제외한 수) 따라서 1을 빼줘야함
            }
        } else {
            answer = ascNonZero(curr, pow2);
        }
        
        System.out.println(answer);
    }
}


// /// GPT가 알려준 코드...

// import java.io.*;
// import java.util.*;

// public class Main {
//     public static void main(String[] args) throws Exception {

//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
//         // 첫 번째 줄에서 두 정수 a와 b를 입력받음 (공백으로 구분)
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int a = Integer.parseInt(st.nextToken());
//         int b = Integer.parseInt(st.nextToken());
        
//         // 두 번째 줄에서 K번째 숫자를 구하기 위한 K를 입력받음
//         int K = Integer.parseInt(br.readLine());
        
//         // 최종 결과를 저장할 문자열 변수
//         String answer = "";
        
//         // a와 b 중 하나라도 0인지 확인 (0이 맨 앞에 올 수 없으므로 별도 처리 필요)
//         boolean hasZero = (a == 0 || b == 0);
        
//         // ====================================================
//         // [경우 1] 두 숫자 모두 0이 아닌 경우
//         // ====================================================
//         if (!hasZero) {
//             // a와 b가 모두 0이 아니므로, 두 숫자 중 작은 값을 small, 큰 값을 large로 설정합니다.
//             // 숫자를 문자로 표현하기 위해 '0'을 더해줍니다.
//             char small = (a < b) ? (char)(a + '0') : (char)(b + '0');
//             char large = (a < b) ? (char)(b + '0') : (char)(a + '0');
            
//             // 만들 수 있는 숫자들은 자릿수(길이)별로 그룹이 나뉘게 됩니다.
//             // 예를 들어, 1자리 숫자는 2개, 2자리 숫자는 2^2=4개, 3자리 숫자는 2^3=8개 등입니다.
//             // 각 그룹의 숫자 개수는 "2의 자릿수승"으로 나타낼 수 있습니다.
//             int length = 1;                         // 현재 고려하는 자릿수 길이 (초기값: 1자리)
//             int countForLength = 1 << length;         // 1 << length 는 2^length를 의미 (예: 1<<1 == 2)
//             int cumulative = countForLength;          // 1자리 그룹부터 시작하여 누적된 숫자 개수
            
//             // K번째 숫자가 몇 자리 숫자 그룹에 속하는지 결정하기 위해 누적 개수를 계산합니다.
//             while (K > cumulative) {
//                 length++;                           // 자릿수를 하나 늘림
//                 countForLength = 1 << length;         // 해당 자릿수의 경우의 수 (2^length)
//                 cumulative += countForLength;         // 누적 개수를 갱신 (1자리부터 현재까지의 총 개수)
//             }
//             // 누적 개수에서 현재 그룹(길이 = length)의 개수를 빼면 이전 그룹의 총 개수를 얻습니다.
//             int prevCumulative = cumulative - countForLength;
//             // 현재 그룹 내에서 K번째 숫자가 몇 번째(0-indexed)인지를 계산합니다.
//             int index = K - prevCumulative - 1;
            
//             // index 값을 이진수 문자열로 변환합니다.
//             // 이진수의 각 자릿수는 해당 위치에 들어갈 숫자를 결정합니다.
//             // 여기서 '0'은 small, '1'은 large를 의미합니다.
//             String binaryStr = Integer.toBinaryString(index);
//             // 이진수 문자열의 길이가 현재 자릿수(length)와 맞지 않으면 앞에 '0'을 채워서 길이를 맞춥니다.
//             while (binaryStr.length() < length) {
//                 binaryStr = "0" + binaryStr;
//             }
            
//             // 이진수 문자열의 각 자리마다 매핑하여 최종 숫자 문자열을 구성합니다.
//             StringBuilder sb = new StringBuilder();
//             for (char bit : binaryStr.toCharArray()) {
//                 // '0'이면 작은 숫자(small), '1'이면 큰 숫자(large)를 추가합니다.
//                 sb.append(bit == '0' ? small : large);
//             }
//             answer = sb.toString();
//         }
//         // ====================================================
//         // [경우 2] a와 b 중 하나가 0인 경우
//         // ====================================================
//         else {
//             // 0은 맨 앞에 올 수 없으므로, 유효한 숫자는 0이 아닌 숫자 하나만 사용됩니다.
//             // 예를 들어, a=0, b=3이면 유효한 숫자는 '3'만 가능.
//             int nonZero = (a == 0) ? b : a;
//             char X = (char)(nonZero + '0'); // 0이 아닌 숫자를 문자로 변환하여 X로 저장
            
//             // 1자리의 경우, 유효한 숫자는 X 하나뿐입니다.
//             if (K == 1) {
//                 answer = String.valueOf(X);
//             } else {
//                 // 2자리 이상의 숫자에서는 맨 앞자리는 반드시 X여야 합니다.
//                 // 나머지 자릿수는 0 또는 X가 올 수 있으므로, 경우의 수는 2^(자릿수-1)개입니다.
//                 int length = 2;  // 2자리부터 시작
//                 int countForLength = 1 << (length - 1);  // 2^(length-1): 맨 앞자리는 고정되어 있으므로 나머지 자리의 경우의 수
//                 int cumulative = countForLength;         // 1자리 숫자(1개)를 제외한 누적 개수
//                 // K에서 1자리 숫자를 제외한 후, K번째 숫자가 몇 자리 그룹에 속하는지 결정합니다.
//                 while (K - 1 > cumulative) {
//                     length++;  // 자릿수를 하나 늘림
//                     countForLength = 1 << (length - 1); // 현재 자릿수(맨 앞 제외)의 경우의 수
//                     cumulative += countForLength;       // 누적 개수를 갱신
//                 }
//                 // 현재 그룹 이전의 누적 개수를 계산합니다.
//                 int prevCumulative = cumulative - countForLength;
//                 // 현재 그룹 내에서 (맨 앞이 고정되어 있으므로) 0-indexed 순서를 구합니다.
//                 int index = (K - 1) - prevCumulative - 1;
                
//                 // 맨 앞은 이미 X로 고정되었으므로, 나머지 (length-1)자리의 조합을 이진수로 결정합니다.
//                 String binaryStr = Integer.toBinaryString(index);
//                 // 이진수 문자열의 길이가 (length-1)과 맞도록 앞에 '0'을 채웁니다.
//                 while (binaryStr.length() < length - 1) {
//                     binaryStr = "0" + binaryStr;
//                 }
                
//                 // 이진수 문자열의 각 자리수를 매핑하여, '0'이면 문자 '0', '1'이면 X로 매핑합니다.
//                 StringBuilder sb = new StringBuilder();
//                 for (char bit : binaryStr.toCharArray()) {
//                     sb.append(bit == '0' ? '0' : X);
//                 }
//                 // 최종 결과는 맨 앞자리 X와 나머지 자리 조합의 연결입니다.
//                 answer = X + sb.toString();
//             }
//         }
        
//         // 결과 출력: K번째에 해당하는 숫자를 출력
//         System.out.println(answer);
//     }
// }

