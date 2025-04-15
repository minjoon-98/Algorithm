//package a0415.homework;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {
	
	// 페르마의 소정리
	static BigInteger nCrMod(int n, int r, int p) {
		long[] fact = new long[n+1];
		fact[0] = 1;
		for(int i=1; i<=n; i++) fact[i] = fact[i-1]*i % p;
		BigInteger P = BigInteger.valueOf(p);
		BigInteger A = BigInteger.valueOf(fact[n]);
		BigInteger B = BigInteger.valueOf(fact[r]).modInverse(P).remainder(P);
		BigInteger C = BigInteger.valueOf(fact[n-r]).modInverse(P).remainder(P);
		return A.multiply(B).multiply(C).remainder(P);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			BigInteger answer = nCrMod(N,R,1234567891);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}
