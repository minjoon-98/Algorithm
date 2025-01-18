import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// new는 memory allocation 해주는 역할. BufferdReader는 constructor(생성자)
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			int ans = 0;
			for (int i = 0; i < 10; i++) {
				int n = Integer.parseInt(st.nextToken());
				if (n % 2 == 1) {
					ans += n;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");	// append 사용 이유 : String이 원시형 타입이 아니라 객체라 +를 사용하면 객체가 매번 생성되어 성능이 떨어지게 됨
		}
		
		System.out.print(sb.toString());
		
		br.close();
	}
}