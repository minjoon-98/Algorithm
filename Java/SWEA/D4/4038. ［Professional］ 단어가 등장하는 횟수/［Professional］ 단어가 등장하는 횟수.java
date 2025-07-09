import java.io.*;
import java.util.*;

// KMP 알고리즘 (O(N + M))
public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String text = br.readLine();
            String pattern = br.readLine();

            int count = kmp(text, pattern);
            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb.toString());
    }

    // KMP 알고리즘 전체 흐름
    public static int kmp(String text, String pattern) {
        int[] lps = buildLPS(pattern);
        int count = 0;
        int i = 0, j = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    count++;
                    j = lps[j - 1]; // 중첩 허용
                }
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    // LPS 배열 생성
    public static int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}



/////  단순 슬라이딩 윈도우 방식 (O(NM))
/// B 길이 500,000 S 길이 100,000 -> 시간 복잡도 5,000,000,000 -> 시간 초과!
////package SWEA;
//
//import java.util.*;
//import java.io.*;
//
//public class Solution_Pro_4038_단어가등장하는횟수 {
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        int T = Integer.parseInt(br.readLine());
//
//        for (int tc = 1; tc <= T; tc++) {
//            String B = br.readLine(); // 책의 내용
//            String S = br.readLine(); // 찾을 단어
//
//            int count = countOccurrences(B, S);
//
//            sb.append("#").append(tc).append(" ").append(count).append("\n");
//        }
//
//        System.out.print(sb.toString());
//    }
//
//    // 중첩 포함 등장 횟수 세기 (슬라이딩 윈도우 방식)
//    public static int countOccurrences(String text, String pattern) {
//        int count = 0;
//        int tLen = text.length();
//        int pLen = pattern.length();
//
//        for (int i = 0; i <= tLen - pLen; i++) {
//            if (text.substring(i, i + pLen).equals(pattern)) {
//                count++;
//            }
//        }
//
//        return count;
//    }
//}
