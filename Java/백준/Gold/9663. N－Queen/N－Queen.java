//package algo_live.a0227;

import java.util.Iterator;
import java.util.Scanner;

/// 두 점의 대각선 여부 판별
/// |두 점의 행 차이| == |두 점의 열 차이|
/// 둘 다 절대값이면 X자
/// 하나만 절대값이면 \ or /
/// 절대값이 아니고 +- 부호로만 된다면 특정 점 기준으로 좌상, 우상, 좌하, 우하 이렇게 4가지

// N*N 판에 N개의 퀸을 양립하도록 놓기
// 같은 행 시도하지 않는 버전
public class Main {

	static int N, ans;
	static boolean[] col, slash, bSlash;	// 같은 열, 대각선(/), 대각선(\)
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new boolean[N+1];
		slash = new boolean[2*N+1];
		bSlash = new boolean[2*N];
		
		setQueens(1);
		System.out.println(ans);

	}
	
	static void setQueens(int rowNo) {
		
		if (rowNo > N) {
			++ans;
			return;
		}
		
		for (int c = 1; c <= N; c++) {
			
			// 가지치기
			if (!isAvailable(rowNo,c)) continue;
			
			col[c] = slash[rowNo+c] = bSlash[(rowNo-c)+N] = true;
			setQueens(rowNo+1);	// 다음 퀸 놓기
			col[c] = slash[rowNo+c] = bSlash[(rowNo-c)+N] = false;
		}
	}

	static boolean isAvailable(int rowNo, int c) {
		return !col[c] && !slash[rowNo+c] && !bSlash[(rowNo-c)+N];
	}

}
