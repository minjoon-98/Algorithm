// package bj_17404;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        int[][] RGBs = new int[N][3];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                RGBs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dpR = new int[N][3];
        int[][] dpG = new int[N][3];
        int[][] dpB = new int[N][3];

        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                dpR[0][i] = RGBs[0][i];
                dpG[0][i] = 10000007;
                dpB[0][i] = 10000007;
            } else if (i == 1) {
                dpR[0][i] = 10000007;
                dpG[0][i] = RGBs[0][i];
                dpB[0][i] = 10000007;
            } else { // i == 2
                dpR[0][i] = 10000007;
                dpG[0][i] = 10000007;
                dpB[0][i] = RGBs[0][i];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dpR[i][j] = Math.min(dpR[i-1][(j+1)%3] + RGBs[i][j], dpR[i-1][(j+2)%3] + RGBs[i][j]);
                dpG[i][j] = Math.min(dpG[i-1][(j+1)%3] + RGBs[i][j], dpG[i-1][(j+2)%3] + RGBs[i][j]);
                dpB[i][j] = Math.min(dpB[i-1][(j+1)%3] + RGBs[i][j], dpB[i-1][(j+2)%3] + RGBs[i][j]);
            }
        }

        int minR = Math.min(dpR[N-1][1], dpR[N-1][2]);
        int minG = Math.min(dpG[N-1][0], dpG[N-1][2]);
        int minB = Math.min(dpB[N-1][0], dpB[N-1][1]);

        int answer = Math.min(Math.min(minR, minG), minB);

        System.out.println(answer);
    }
}
