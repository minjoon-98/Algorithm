import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N, L;
    static int[] scores;
    static int[] calories;
    static int maxScore;
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
 
            scores = new int[N];
            calories = new int[N];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }
 
            maxScore = 0;
            calculateMaxScore(0, 0, 0);
 
            sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
        }
 
        System.out.print(sb.toString());
        br.close();
    }
     
    // 재귀 함수 (DFS)
    private static void calculateMaxScore(int index, int currentScore, int currentCalories) {
        if (currentCalories > L) {
            return;
        }
 
        if (index == N) {
            maxScore = Math.max(maxScore, currentScore);
            return;
        }
 
        //  현재 재료를 포함하는 경우
        calculateMaxScore(index + 1, currentScore + scores[index], currentCalories + calories[index]);
 
        // 현재 재료를 포함하지 않는 경우
        calculateMaxScore(index + 1, currentScore, currentCalories);
    }
}