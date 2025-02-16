//package no2_10726;

import java.util.*;
import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            String binary = Integer.toBinaryString(M);  // M을 이진수로 변환
            sb.append("#").append(tc).append(" ");

            int bDigits = binary.length();  // 이진수 길이
            boolean allOn = true;

            // M이 0이라면 마지막 N 비트가 모두 1일 수 없음
            if (bDigits < N) {
                allOn = false;
            } else {
                // 마지막 N 비트가 모두 1인지 확인
                for (int i = 1; i <= N; i++) {
                    if (binary.charAt(bDigits - i) != '1') {
                        allOn = false;
                        break;
                    }
                }
            }

            if (allOn) {
                sb.append("ON").append("\n");
            } else {
                sb.append("OFF").append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
