//package a0408.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_4013_특이한자석_서울_14반_김민준 {
//	
//	
//	static List<Integer>[] magnetGear;
//	
//	static void rotate(int idx, int dir) {
//		
//		if (dir == 1) {
//			Collections.rotate(magnetGear[idx], 1);			
//		} else { // dir == -1
//			Collections.rotate(magnetGear[idx], 7);
//		}
//		
//	}
//	
//	static void command(int idx, int dir) {
//		
//		int[] rot = new int[4];
//		rot[idx] = dir;
//		
//		for (int i = idx - 1; i >= 0; i--) {
//		    if (magnetGear[i].get(2) != magnetGear[i + 1].get(6)) {
//		        rot[i] = -rot[i + 1];
//		    } else break;
//		}
//
//		for (int i = idx + 1; i < 4; i++) {
//		    if (magnetGear[i - 1].get(2) != magnetGear[i].get(6)) {
//		        rot[i] = -rot[i - 1];
//		    } else break;
//		}
//
//		for (int i = 0; i < 4; i++) {
//			if (rot[i] == 0) continue;
//			rotate(i, rot[i]);
//		}
//		
//	}
//
//	public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//        	
//        	int K = Integer.parseInt(br.readLine());
//        	
//        	magnetGear = new List[4];
//        	for (int i = 0; i < 4; i++) {
//        		magnetGear[i] = new ArrayList<>();
//        	}
//        	
//        	for (int i = 0; i < 4; i++) {
//        		st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < 8; j++) {
//					magnetGear[i].add(Integer.parseInt(st.nextToken()));
//				}
//			}
//
//        	for (int i = 0; i < K; i++) {
//        		st = new StringTokenizer(br.readLine());
//        		int idx = Integer.parseInt(st.nextToken()) - 1;	// 0-base
//        		int dir = Integer.parseInt(st.nextToken());
//        		command(idx, dir);
//			}
//            
//        	int answer = 0B0000;
//        	for (int i = 0; i < 4; i++) {
//        		if (magnetGear[i].get(0) == 1) {
////        			answer |= 1 << i;
//        			answer += 1 << i;
//        		}
//			}
//        	
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//	
//}



//package a0408.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_4013_특이한자석_서울_14반_김민준 {
//	
//	
//	static List<Integer>[] magnetGear;
//	
//	static int checkGear(int idx) {
//		int check = 1 << idx;
//		
//		int curr = idx - 1;
//		while (curr >= 0) {
//			if (magnetGear[curr].get(2) != magnetGear[curr + 1].get(6)) {
//				check |= 1 << curr;
//			} else {
//				break;
//			}
//			--curr;
//		}
//		curr = idx + 1;
//		while (curr <= 3) {
//			if (magnetGear[curr].get(6) != magnetGear[curr - 1].get(2)) {
//				check |= 1 << curr;
//			} else {
//				break;
//			}
//			++curr;
//		}
//		
//		return check;
//	}
//	
//	static void rotate(int idx, int dir) {
//		
//		if (dir == 1) {
//			Collections.rotate(magnetGear[idx], 1);			
//		} else {
//			Collections.rotate(magnetGear[idx], 7);
//		}
//		
//	}
//	
//	static void command(int idx, int dir) {
//		int check = checkGear(idx);
//		
//		for (int i = 0; i < 4; i++) {
//			if ((check & (1 << i)) != 0) continue;
//			if (idx % 2 == i % 2) {
//				rotate(i, dir);
//			} else {
//				rotate(i, dir*(-1));
//			}
//		}
//		
//	}
//
//	public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//        	
//        	int K = Integer.parseInt(br.readLine());
//        	
//        	magnetGear = new ArrayList[4];
//        	for (int i = 0; i < 4; i++) {
//        		magnetGear[i] = new ArrayList<>();
//        	}
//        	
//        	for (int i = 0; i < 4; i++) {
//        		st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < 8; j++) {
//					magnetGear[i].add(Integer.parseInt(st.nextToken()));
//				}
//			}
//        	
//        	for (int i = 0; i < 4; i++) {
//        		System.out.println(magnetGear[i].toString());
//        	}
//        	System.out.println();
//
//        	for (int i = 0; i < K; i++) {
//        		st = new StringTokenizer(br.readLine());
//        		int idx = Integer.parseInt(st.nextToken()) - 1;	// 0-base
//        		int dir = Integer.parseInt(st.nextToken());
//        		command(idx, dir);
//			}
//        	
//        	for (int i = 0; i < 4; i++) {
//        		System.out.println(magnetGear[i].toString());
//        	}
//            
//        	int answer = 0B0000;
//        	for (int i = 0; i < 4; i++) {
//        		if (magnetGear[i].get(0) == 1) {		
//        			answer += 1 << i;
//        		}
//			}
//        	
//        	System.out.println();
//        	System.out.println();
//        	System.out.println();
//        	
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}



/// int 배열 버전
//package a0408.homework;

import java.io.*;
import java.util.*;

public class Solution {
	
	
	static int[][] magnetGear;
	
	static void rotate(int idx, int dir) {
		
		if (dir == 1) {
			int temp = magnetGear[idx][7];
			for (int i = 7; i > 0; i--) {
				magnetGear[idx][i] = magnetGear[idx][i-1];
			}
			magnetGear[idx][0] = temp;
		} else { // dir == -1
			int temp = magnetGear[idx][0];
			for (int i = 0; i < 7; i++) {
				magnetGear[idx][i] = magnetGear[idx][i+1];
			}
			magnetGear[idx][7] = temp;
		}
		
	}
	
	static void command(int idx, int d) {
		
		int[] dir = new int[4];
		dir[idx] = d;
		
		for (int i = idx - 1; i >= 0; i--) {
			if (magnetGear[i][2] != magnetGear[i + 1][6]) {
				dir[i] = -dir[i + 1];
			} else break;
		}
		
		for (int i = idx + 1; i < 4; i++) {
			if (magnetGear[i - 1][2] != magnetGear[i][6]) {
				dir[i] = -dir[i - 1];
			} else break;
		}
		
		for (int i = 0; i < 4; i++) {
			if (dir[i] == 0) continue;
			rotate(i, dir[i]);
		}
		
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
        	
        	int K = Integer.parseInt(br.readLine());
        	
        	magnetGear = new int[4][8];
        	
        	for (int i = 0; i < 4; i++) {
        		st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnetGear[i][j] = Integer.parseInt(st.nextToken());
				}
			}
        	
        	for (int i = 0; i < K; i++) {
        		st = new StringTokenizer(br.readLine());
        		int idx = Integer.parseInt(st.nextToken()) - 1;	// 0-base
        		int d = Integer.parseInt(st.nextToken());
        		command(idx, d);
			}
            
        	int answer = 0;
        	for (int i = 0; i < 4; i++) {
        		if (magnetGear[i][0] == 1) {
        	        answer += (1 << i);
        	    }
			}
        	
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}



///// int 배열로 시도
//package a0408.homework;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution_ur_4013_특이한자석_서울_14반_김민준 {
//	
//	
//	static int[][] magnetGear;
//	
//	static int checkGear(int idx) {
//		int check = 1 << idx;
//		
//		int curr = idx - 1;
//		while (curr > 0) {
//			if (magnetGear[curr][2] != magnetGear[curr + 1][6]) {
//				check &= 1 << curr;
//			} else {
//				break;
//			}
//			--curr;
//		}
//		curr = idx + 1;
//		while (curr < 3) {
//			if (magnetGear[curr][6] != magnetGear[curr - 1][2]) {
//				check &= 1 << curr;
//			} else {
//				break;
//			}
//			++curr;
//		}
//		
//		return check;
//	}
//	
//	static void rotate(int idx, int dir) {
//		
//		if (dir == 1) {
//			Collections.rotate(magnetGear[idx], 1);			
//		} else { // dir == -1
//			Collections.rotate(magnetGear[idx], 7);
//		}
//		
//	}
//	
//	static void command(int idx, int dir) {
//		int check = checkGear(idx);
//		
//		for (int i = 0; i < 4; i++) {
//			if ((check & (1 << i)) != 1) continue;
//			if (idx % 2 == i % 2) {
//				rotate(i, dir);
//			} else {
//				rotate(i, dir*(-1));
//			}
//		}
//		
//	}
//
//	public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = null;
//        int T = Integer.parseInt(br.readLine());
//        
//        for (int tc = 1; tc <= T; tc++) {
//        	
//        	int K = Integer.parseInt(br.readLine());
//        	
//        	magnetGear = new int[4][8];
//        	
//        	for (int i = 0; i < 4; i++) {
//        		st = new StringTokenizer(br.readLine());
//				for (int j = 0; j < 8; j++) {
//					magnetGear[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//        	
//        	for (int i = 0; i < K; i++) {
//        		st = new StringTokenizer(br.readLine());
//        		int idx = Integer.parseInt(st.nextToken()) - 1;	// 0-base
//        		int dir = Integer.parseInt(st.nextToken());
//        		command(idx, dir);
//			}
//            
//        	int answer = 0B0000;
//        	for (int i = 0; i < 4; i++) {
//				answer &= 1 << magnetGear[i][0];
//			}
//        	
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//        
//        System.out.println(sb.toString());
//    }
//}
//
