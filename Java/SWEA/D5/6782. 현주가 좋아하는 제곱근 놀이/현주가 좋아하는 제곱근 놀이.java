//package a0304.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	/// java.lang.OutOfMemoryError: Java heap space
	/// O(2^N) (지수 시간)
//	static int bfsReversed(int N) {
//		
//		Queue<int[]> q = new ArrayDeque<>();
//		q.offer(new int[] {2, 0});
//		
//		while (!q.isEmpty()) {
//			int[] NC = q.poll();
//			int num = NC[0], cnt = NC[1];
//			
//			if (num == N) {
//				return cnt;
//			}
//			
//			if (num > 2) {
//				q.offer(new int[]{num-1, cnt+1});				
//			}
//			q.offer(new int[]{num*num, cnt+1});
//			
//		}
//		
//		return -1;
//	}
	
	/// 제곱근을 계속 취하는 방식
	/// O(log log N)
    static long minOperations(long N) {
        long count = 0;
        
        while (N > 2) {
            long sqrt = (long) Math.sqrt(N);
            
            if (sqrt * sqrt == N) { // 현재 N이 완전제곱수이면
                N = sqrt;
                count++;
            } else {
                // 가장 가까운 제곱수 찾기
                long nextSquare = (sqrt + 1) * (sqrt + 1);
                count += (nextSquare - N); // N을 제곱수로 만들기 위해 +1 연산 수행
                N = nextSquare;
            }
        }
        return count;
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
//			int N = Integer.parseInt(br.readLine());
			long N = Long.parseLong(br.readLine());  // 10^12까지 가능하므로 long 사용
			
//			sb.append("#").append(tc).append(" ").append(bfsReversed(N)).append("\n");
			sb.append("#").append(tc).append(" ").append(minOperations(N)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}