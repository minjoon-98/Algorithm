import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, count;
	static HashMap<Integer, ArrayList<Integer>> map;
	static void subSet(int idx, ArrayList<Integer> selected) {
		if (idx == N) {
			count++;
			return;
		}
		
		boolean isConst = false;
		
		if (!map.get(idx).isEmpty()) {			
			for (int i : map.get(idx)) {
				for (int j : selected) {
					if (i == j) {
						isConst = true;
						break;
					}
				}
			}
		}
		
//		if (isConst) {
//			subSet(idx+1, selected);
//		} else {
//			selected.add(idx);
//			subSet(idx+1, selected);
//			selected.remove(selected.size() - 1);
//			subSet(idx+1, selected);
//		}
		
		if (!isConst) {
			selected.add(idx);
			subSet(idx+1, selected);
			selected.remove(selected.size() - 1);
		}
		subSet(idx+1, selected);
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
				
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			map = new HashMap<>();
			
			for (int i = 0; i < N; i++) {
				map.put(i, new ArrayList<>());
			}
           
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				map.get(a-1).add(b-1);
				map.get(b-1).add(a-1);
			}
			
			count = 0;
			subSet(0, new ArrayList<>());
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}

		System.out.println(sb.toString());
	}

}