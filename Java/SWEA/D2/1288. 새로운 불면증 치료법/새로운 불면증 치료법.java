//package no1_1288;

import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[10];  // 0부터 9까지 숫자가 등장했는지 체크
            int count = 0;
            int i = 1;

            // 모든 숫자(0~9)가 등장할 때까지 반복
            while (true) {
                int temp = N * i;

                // 현재 숫자의 각 자릿수들을 확인
                while (temp > 0) {
                    int digit = temp % 10;
                    visited[digit] = true;
                    temp /= 10;
                }

                count++;  // 양을 세는 횟수 증가

                // 모든 숫자가 등장했는지 확인
                boolean allVisited = true;
                for (boolean v : visited) {
                    if (!v) {
                        allVisited = false;
                        break;
                    }
                }

                if (allVisited) {
                    break;  // 모든 숫자를 본 경우 종료
                }

                i++;  // 다음 배수로 넘어감
            }

            sb.append("#").append(tc).append(" ").append(N * count).append("\n");
        }

        System.out.println(sb.toString());
    }
}
