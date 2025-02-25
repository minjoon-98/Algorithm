import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[] operation;
	static ArrayList<Integer> results;
	
	static void swap(int i, int j) {
		int temp = operation[i];
		operation[i] = operation[j];
		operation[j] = temp;
	}
	
	static boolean nextPerm() {
		
		int i = N-2;
		while (i>0 && operation[i-1] >= operation[i]) --i;
		if (i == 0) return false;
		
		int j = N-2;
		while(operation[i-1] >= operation[j]) --j;
		swap(i-1, j);
		
		int k = N-2;
		while (i < k) swap(i++, k--);
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
				
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			int[] operationCnt = new int[4];
			operation = new int[N-1];
			int idxCnt = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				operationCnt[i] = Integer.parseInt(st.nextToken());
				int cnt = idxCnt;
				for (int j = cnt; j < cnt + operationCnt[i]; j++) {
					operation[j] = i;
					++idxCnt;
				}
			}
//			System.out.println(Arrays.toString(operation));
			
			int[] operand = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				operand[i] = Integer.parseInt(st.nextToken());
			}
			
			results = new ArrayList<>();
			do {
				int result = operand[0];
				for (int i = 0; i < N-1; i++) {
					int op = operation[i];
					if (op == 0) {
						result += operand[i+1];						
					} else if (op == 1) {
						result -= operand[i+1];
					} else if (op == 2) {
						result *= operand[i+1];
					} else if (op == 3) {
						result /= operand[i+1];
					}
				}
				results.add(result);
			} while (nextPerm());
			results.sort((a, b) -> a - b);	// 오름차순 정렬
			int answer = results.get(results.size()-1) - results.get(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}