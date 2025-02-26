/// BufferedReader 사용하면 RunTime Error 발생!!!
/// StreamTokenizer 사용한 버전... Scanner가 더 쉬운거 같다...

//package a0226.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 10240);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
		StreamTokenizer st = new StreamTokenizer(br);
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(br.readLine());
		st.nextToken();
		int T = (int) st.nval;
				
		for (int tc = 1; tc <= T; tc++) {
//			int N = Integer.parseInt(br.readLine());
			st.nextToken();
			int N = (int) st.nval;
			int[] heights = new int[N];

//			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
//				heights[i] = Integer.parseInt(st.nextToken());
				st.nextToken();
				heights[i] = (int) st.nval;
			}
			
            int answer = 0;
            int increaseCount = 0, decreaseCount = 0;
            for (int i = 1; i < N; i++) {
                if (heights[i - 1] < heights[i]) {	// 증가 구간
                	if (decreaseCount > 0) {	// 만약 우뚝 선 산이 존재했다면
                		increaseCount = 0;	// 카운팅을 초기화
                	}
                    increaseCount++;
                    decreaseCount = 0;
                } else {	// 감소 구간
                    decreaseCount++;
                    if (increaseCount > 0) {
                        answer += increaseCount;
                    }
                }
            }

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}
}


///// BufferedReader 사용하면 RunTime Error 발생!!!
///// Scanner 사용해야함..!
//
//package a0226.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d4_4796_의석이의우뚝선산_서울_14반_김민준 {
//	
//	public static void main(String[] args) throws Exception {
////		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
//		StringBuilder sb = new StringBuilder();
//		
////		int T = Integer.parseInt(br.readLine());
//		int T = sc.nextInt();
//				
//		for (int tc = 1; tc <= T; tc++) {
////			int N = Integer.parseInt(br.readLine());
//			int N = sc.nextInt();
//			int[] heights = new int[N];
//
////			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//			for (int i = 0; i < N; i++) {
////				heights[i] = Integer.parseInt(st.nextToken());
//				heights[i] = sc.nextInt();
//			}
//			
//            int answer = 0;
//            int increaseCount = 0, decreaseCount = 0;
//            for (int i = 1; i < N; i++) {
//                if (heights[i - 1] < heights[i]) {	// 증가 구간
//                	if (decreaseCount > 0) {	// 만약 우뚝 선 산이 존재했다면
//                		increaseCount = 0;	// 카운팅을 초기화
//                	}
//                    increaseCount++;
//                    decreaseCount = 0;
//                } else {	// 감소 구간
//                    decreaseCount++;
//                    if (increaseCount > 0) {
//                        answer += increaseCount;
//                    }
//                }
//            }
//
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//
//		System.out.println(sb.toString());
//	}
//
//}

//package a0226.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d4_4796_의석이의우뚝선산_서울_14반_김민준 {
//	
//	public static void main(String[] args) throws Exception {
////		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
//		StringBuilder sb = new StringBuilder();
//		
////		int T = Integer.parseInt(br.readLine());
//		int T = sc.nextInt();
//				
//		for (int tc = 1; tc <= T; tc++) {
////			int N = Integer.parseInt(br.readLine());
//			int N = sc.nextInt();
//			int[] heights = new int[N];
//
////			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//			for (int i = 0; i < N; i++) {
////				heights[i] = Integer.parseInt(st.nextToken());
//				heights[i] = sc.nextInt();
//			}
//			
//			
//			int answer = 0;
//			int pi = 0, pj = 1;
//			int increaseCount = 0, decreaseCount = 0;
//			
//			while (pj < N) {
//				// 1. 증가 구간 탐색
//				while (pj < N && heights[pj - 1] < heights[pj]) {
//					increaseCount++;
//					pj++;
//				}
//				
//				// 증가 구간이 없으면 넘어감
//				if (increaseCount == 0) {
//					pi = pj;
//					pj++;
//					continue;
//				}
//				
//				// 2. 감소 구간 탐색
//				decreaseCount = 0;
//				while (pj < N && heights[pj - 1] > heights[pj]) {
//					decreaseCount++;
//					answer += increaseCount; // 증가 구간 수만큼 감소 구간을 선택할 수 있음.
//					pj++;
//				}
//				
//				// 3. 감소 구간이 없으면 초기화 후 다시 탐색
//				if (decreaseCount == 0) {
//					increaseCount = 0;
//					pi = pj;
//					pj++;
//					continue;
//				}
//				
//				// 4. 새로운 구간 탐색을 위해 초기화
//				pi = pj - 1; // 감소 구간 끝에서 다시 탐색 시작
//				increaseCount = 0;
//			}
//			
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//	
//}

/// 오답 10개중 5개 틀림
//package a0226.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_d4_4796_의석이의우뚝선산_서울_14반_김민준 {
//	
//	public static void main(String[] args) throws Exception {
////		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
//		StringBuilder sb = new StringBuilder();
//		
////		int T = Integer.parseInt(br.readLine());
//		int T = sc.nextInt();
//				
//		for (int tc = 1; tc <= T; tc++) {
////			int N = Integer.parseInt(br.readLine());
//			int N = sc.nextInt();
//			int[] heights = new int[N];
//
////			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//			for (int i = 0; i < N; i++) {
////				heights[i] = Integer.parseInt(st.nextToken());
//				heights[i] = sc.nextInt();
//			}
//			int answer = 0;
//			int pi = 0, pj = 2;
//			int increaseP = -1, decreaseP = 0;
//			boolean continuous = false;
//			while (pj < N && pi + 1 < N) {
//				if (heights[pi] < heights[pi+1]) {
//					if (heights[pj-1] > heights[pj]) {
//						if (!continuous) {
//							continuous = true;							
//							increaseP = pi + 1;
////							decreaseP = (increaseP + 2 < N - 1) ? increaseP + 2 : N - 1;
//							decreaseP = (increaseP + 1 < pj) ? pj : Math.min(N - 1, increaseP + 2);							
//						}
//						++answer;
//						++pj;
//						continue;
//					} else {
//						if (continuous) {
//							continuous = false;
//							pi = increaseP;
//							pj = decreaseP;
//						} else {
//							++pj;
//						}
//					}
//				} else {
//					++pi;
//					pj = Math.min(N - 1, pi + 2);
//				}
//			}
//			
//			sb.append("#").append(tc).append(" ").append(answer).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//	
//}