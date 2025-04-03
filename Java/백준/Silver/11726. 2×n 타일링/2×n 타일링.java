/// int 오버 플로우 방지! 모듈러 연산을 계속 해주자!
//package a0403.homework;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if (n < 3) {
			System.out.println(n);
			return;
		}
		
		int[] dp = new int [n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%10007;
		}
		
		System.out.println(dp[n]);
	}
}


///// 오답!!! (int 오버플로우 발생 가능!)
//package a0403.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_bj_11726_2xn타일링_서울_14반_김민준 {
//	
//	public static void main(String[] args) throws Exception {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int n = Integer.parseInt(br.readLine());
//		
//		if (n < 3) {
//			System.out.println(n);
//			return;
//		}
//		
//		int[] dp = new int [n+1];
//		dp[0] = 0;
//		dp[1] = 1;
//		dp[2] = 2;
//		
//		for (int i = 3; i <= n; i++) {
//			dp[i] = dp[i-1] + dp[i-2]; // ← 여기서 int 오버플로우 발생 가능
//		}
//		
//		System.out.println(dp[n]%10007);
//	}
//}
