import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= 10; tc++) {
			
			int length = Integer.parseInt(br.readLine());
//			StringTokenizer st = new StringTokenizer(br.readLine(), "");
			String brackets = br.readLine();
			
			ArrayList<Character> stack = new ArrayList<>();
			
			// 1: 유효함	0: 유효하지 않음
			byte isValid = -1;
			
			for (int i = 0; i < length; i++) {
//				char input = st.nextToken().charAt(0);
				char input = brackets.charAt(i);
				if (stack.isEmpty()) {					
					stack.add(input);
				} else {
					int lastIdx = stack.size() - 1;
					char peek = stack.get(lastIdx);
//					char peek = stack.getLast();
					switch (input) {
					case ')':
						if (peek == '(') {
							stack.remove(lastIdx);
//							stack.removeLast();
						} else {
							isValid = 0;
						}
						break;
					case ']':
						if (peek == '[') {
							stack.remove(lastIdx);
//							stack.removeLast();
						} else {
							isValid = 0;
						}
						break;
					case '}':
						if (peek == '{') {
							stack.remove(lastIdx);
//							stack.removeLast();
						} else {
							isValid = 0;
						}
						break;
					case '>':
						if (peek == '<') {
							stack.remove(lastIdx);
//							stack.removeLast();
						} else {
							isValid = 0;
						}
						break;
					default:
						stack.add(input);
						break;
					}
				}
			}
			
			if (isValid == -1) {
				if (stack.isEmpty()) {
					isValid = 1;
				}				
			}
			
			sb.append("#").append(tc).append(" ").append(isValid).append("\n");
		}
		System.out.println(sb.toString());
	}

}
