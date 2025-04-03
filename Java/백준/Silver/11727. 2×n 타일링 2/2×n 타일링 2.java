import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if (n == 1) {
			System.out.println(1);
			return;
		} else if (n == 2) {
			System.out.println(3);
			return;
		}
		
		int[] dp = new int [n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 3;
		
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-2] * dp[2] + dp[i-1] - dp[i-2])%10007;
		}
		
		System.out.println(dp[n]);
	}
}


///// 오답!!! (왜 틀림??!)
//package a0403.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Main_bj_11727_2xn타일링2_서울_14반_김민준 {
//	
//	public static void main(String[] args) throws Exception {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int n = Integer.parseInt(br.readLine());
//		
//		if (n == 1) {
//			System.out.println(1);
//			return;
//		} else if (n == 2) {
//			System.out.println(3);
//			return;
//		}
//		
//		int[] dp = new int [n+1];
//		dp[0] = 0;
//		dp[1] = 1;
//		dp[2] = 3;
//		
//		for (int i = 3; i <= n; i++) {
//			dp[i] = dp[i-2] * dp[2] + dp[i-1] - dp[i-2];
//		}
//		
//		System.out.println(dp[n]%10007);
//	}
//}
