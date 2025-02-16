//package no3_1230;

import java.util.*;
import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 10;  // 10개의 테스트 케이스

        for (int tc = 1; tc <= T; tc++) {

            // 원본 암호문 뭉치 속 암호문의 개수
            int N = Integer.parseInt(br.readLine());

            // 원본 암호문 뭉치
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            ArrayList<Integer> ciphertext = new ArrayList<>();
            while (st.hasMoreTokens()) {
                ciphertext.add(Integer.parseInt(st.nextToken()));
            }

            // 명령어의 개수
            int M = Integer.parseInt(br.readLine());

            // 명령어 처리
            st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreTokens()) {
                String command = st.nextToken();
                switch (command) {
                    case "I":
                        int xI = Integer.parseInt(st.nextToken());
                        int yI = Integer.parseInt(st.nextToken());
                        ArrayList<Integer> insertList = new ArrayList<>();
                        for (int i = 0; i < yI; i++) {
                            insertList.add(Integer.parseInt(st.nextToken()));
                        }
                        // xI 번째 이후에 yI 개의 암호문 삽입
                        ciphertext.addAll(xI, insertList);
                        break;

                    case "D":
                        int xD = Integer.parseInt(st.nextToken());
                        int yD = Integer.parseInt(st.nextToken());
                        // xD 이후부터 yD 개의 암호문 삭제
                        for (int i = 0; i < yD; i++) {
                            ciphertext.remove(xD);
                        }
                        break;

                    case "A":
                        int yA = Integer.parseInt(st.nextToken());
                        ArrayList<Integer> addList = new ArrayList<>();
                        for (int i = 0; i < yA; i++) {
                            addList.add(Integer.parseInt(st.nextToken()));
                        }
                        // 맨 뒤에 yA 개의 암호문 추가
                        ciphertext.addAll(addList);
                        break;

                    default:
                        break;
                }
            }

            // 첫 10개 출력
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(ciphertext.get(i)).append(" ");
            }
            sb.append("\n");
        }

        // 최종 출력
        System.out.println(sb.toString());
    }
}