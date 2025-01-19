import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String memory = br.readLine();

            int count = 0;
            char currentState = '0';	// 초기 상태는 모두 0으로 설정

            for (int i = 0; i < memory.length(); i++) {
                if (memory.charAt(i) != currentState) {
                    count++;	// 주어진 메모리 문자열을 한 글자씩 순회하면서 현재 상태(currentState)와 다를 경우, 수정 횟수를 증가
                    currentState = memory.charAt(i);	// currentState를 갱신하여 현재 위치의 값을 기준으로 다음 상태를 판단
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}