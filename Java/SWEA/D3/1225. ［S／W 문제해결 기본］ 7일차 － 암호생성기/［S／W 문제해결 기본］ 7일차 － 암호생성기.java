import java.io.*;
import java.util.*;

public class Solution {
	
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 10; tc++) {
			int test_case = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			queue = new ArrayDeque<>(8);
			
//			while (st.hasMoreTokens()) {
			for (int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			boolean shutdown = false;
			int count = 0;
			while (!shutdown) {
				int before = queue.poll();
				int after = Math.max(0, before - ((count%5)+1));
				queue.offer(after);
				count++;
				if (after == 0) {
					shutdown = true;
				}
			}
			
			sb.append("#").append(test_case).append(" ");
			
			for (Integer i : queue) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
	}

}
