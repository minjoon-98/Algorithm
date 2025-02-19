import java.util.*;
import java.io.*;

public class Solution {

	static ArrayList<Integer> setlist;	// 내가 내는 카드 셋 리스트
	static byte[] isSelected;	// 0: 선택 안된 카드, 1: 상대가 고른 카드, 2: 내가 고른 카드, -1: 0-base
	
	static int winCount;
	static int loseCount;
	
	static void game(int depth, int myScore, int opScore) {
		
		if (depth == 9) {
			if (opScore < myScore) {
				winCount++;
			} else if (opScore > myScore) {
				loseCount++;
			}
			return;
		}
		
		for (int c = 1; c <= 18; c++) {
			if (!(isSelected[c] == 0)) {
				continue;
			}
			
			int score= c + setlist.get(depth);
			
			if (setlist.get(depth) > c) {
				isSelected[c] = 2;
				game(depth+1, myScore + score, opScore);
				isSelected[c] = 0;
			} else if (setlist.get(depth) < c) {				
				isSelected[c] = 2;
				game(depth+1, myScore, opScore + score);
				isSelected[c] = 0;
			} else {
				isSelected[c] = 2;
				game(depth+1, myScore, opScore);
				isSelected[c] = 0;
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			setlist = new ArrayList<>(9);
			isSelected = new byte[19];
			isSelected[0] = -1;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < 9; i++) {
				int c = Integer.parseInt(st.nextToken());
				setlist.add(c);
				isSelected[c] = 1;
			}
			
			winCount = 0;
			loseCount = 0;
						
			game(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(winCount).append(" ").append(loseCount).append("\n");
		}

		System.out.println(sb.toString());
	}

}
