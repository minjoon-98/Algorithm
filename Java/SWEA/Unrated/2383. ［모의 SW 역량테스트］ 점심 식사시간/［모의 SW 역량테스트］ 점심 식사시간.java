//package a0327.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
    // 좌표 정보를 저장할 클래스
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }
    
    // 계단 정보를 저장할 클래스 (r, c 좌표와 계단 길이)
    static class Stair {
        int r, c, len;
        Stair(int r, int c, int len) { 
            this.r = r; 
            this.c = c; 
            this.len = len; 
        }
    }
    
    static int N, answer;
    static int[][] map;
    static ArrayList<Point> people;
    static Stair[] stairs;
    static int[] assign;  // 각 사람에게 할당된 계단 번호 (0 또는 1)
    

    // idx번째 사람의 계단 할당을 결정하는 subset (dfs)
    static void subset(int idx) {
        if (idx == people.size()) {
            // 할당이 완료되면 각 계단별로 소요시간 계산
            int time0 = simulate(0);
            int time1 = simulate(1);
            // 전체 시간은 두 계단 중 더 늦게 끝난 시간
            int totalTime = Math.max(time0, time1);
            answer = Math.min(answer, totalTime);
            return;
        }
        
        // 사람 idx를 0번 계단으로 할당
        assign[idx] = 0;
        subset(idx + 1);
        
        // 사람 idx를 1번 계단으로 할당
        assign[idx] = 1;
        subset(idx + 1);
    }
    
    // 특정 계단(stairNum)을 이용하는 사람들에 대해 시뮬레이션 후 완료 시간을 반환
    static int simulate(int stairNum) {
        ArrayList<Integer> arrivalTimes = new ArrayList<>();
        Stair stair = stairs[stairNum];
        
        // 해당 계단을 선택한 사람들에 대해 계단 입구 도착 시간(맨해튼 거리)을 구함
        for (int i = 0; i < people.size(); i++) {
            if (assign[i] == stairNum) {
                Point p = people.get(i);
                int dist = Math.abs(p.r - stair.r) + Math.abs(p.c - stair.c);
                arrivalTimes.add(dist);
            }
        }
        
        // 해당 계단을 사용하지 않는 경우
        if (arrivalTimes.size() == 0) return 0;
        
        Collections.sort(arrivalTimes);
        
        int time = 0;
        int finished = 0;
        int idx = 0;
        int total = arrivalTimes.size();
        
        // 내려가고 있는 사람들의 완료 시간을 저장하는 큐 (최소 시간 우선)
        Queue<Integer> descending = new LinkedList<>();
        
        // 시뮬레이션: 모든 사람이 내려갈 때까지 1분씩 진행
        while (finished < total) {
            // 현재 시간에 완료되는 사람들 처리
            while (!descending.isEmpty() && descending.peek() == time) {
                descending.poll();
                finished++;
            }
            
            // 도착한 사람이 계단 입구에서 대기하는 경우 (도착 후 1분 기다림)
            // 동시에 3명까지만 계단에 들어갈 수 있음.
            while (idx < total && arrivalTimes.get(idx) + 1 <= time && descending.size() < 3) {
                // 현재 시간에 내려가기 시작 -> 완전히 내려가는 시간 = 현재 시간 + 계단 길이
                descending.offer(time + stair.len);
                idx++;
            }
            time++;
        }
        return time - 1;	// 시뮬 종료 시점에 더해지는 1 빼주기
    }
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            people = new ArrayList<>();
            stairs = new Stair[2];
            int stairIdx = 0;
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        people.add(new Point(i, j));
                    } else if (map[i][j] > 1) {
                        stairs[stairIdx++] = new Stair(i, j, map[i][j]);
                    }
                }
            }
            
            // 사람 수에 따른 계단 선택 저장 배열
            assign = new int[people.size()];
            answer = Integer.MAX_VALUE;
            
            // DFS로 각 사람의 계단 할당 경우의 수를 탐색
            subset(0);
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
    
}