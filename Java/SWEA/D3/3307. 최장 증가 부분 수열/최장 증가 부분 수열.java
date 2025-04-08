//package a0408.homework;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
        	
    		int N = Integer.parseInt(br.readLine());
    		
    		int[] arr = new int[N];
    		int[] C = new int[N]; // 각 길이를 LIS로 만족하는 가장 끝에 오는 최소값
    		
    		st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < N; i++) {
    			arr[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		int size = 0; // LIS의 길이
    		for (int i = 0; i < N; i++) {
    			// i 원소가 최소값으로 끝에 설 수 있는 위치 찾기
    			int pos = Arrays.binarySearch(C, 0, size, arr[i]); // 못찾으면 -insertion point -1
    			if (pos >= 0) return;	// 찾는 값이 있다는 얘기는 값의 중복이 있다는 의미! : 문제에 따라 다르게 처리
    			
    			int temp = Math.abs(pos) - 1;
//    			if(pos < 0) pos = -(pos + 1);
    			
    			C[temp] = arr[i];
    			
    			if (temp == size) ++size; // 맨뒤에 추가된 상황이므로 사이즈 증가
    			
    		}
            
            sb.append("#").append(tc).append(" ").append(size).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}


//package a0408.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d3_3307_최장증가부분수열_서울_14반_김민준 {
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//        	
//    		int N = Integer.parseInt(br.readLine());
//    		
//    		int[] arr = new int[N];
//    		int[] LIS = new int[N]; // 자신을 끝으로 하는 증가부분수열의 최장 길이
//    		
//    		st = new StringTokenizer(br.readLine());
//    		for (int i = 0; i < N; i++) {
//    			arr[i] = Integer.parseInt(st.nextToken());
//    		}
//    		
//    		int max = 0; // 최장증가부분수열의 길이
//    		for (int i = 0; i < N; i++) {
//    			LIS[i] = 1; // 자신만 끝에 세웠을 경우의 최장길이 1로 초기화
//    			for (int j = 0; j < i; j++) { // i보다 앞에 있는 모든 대상에 대해 탐색
//    				if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) { // 나보다 앞에 있는 j가 값이 나보다 작고, j 뒤에 i를 세우는 것이 더 최장을 만족한다면
//    					LIS[i] = LIS[j] + 1;
//    				}
//    			}
//    			max = Math.max(max, LIS[i]);
//    		}
//            
//            sb.append("#").append(tc).append(" ").append(max).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}
