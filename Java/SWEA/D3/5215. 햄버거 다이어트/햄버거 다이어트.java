//package a0225.homework;

import java.io.*;
import java.util.*;

public class Solution {
    
   static int N, L;
   static byte[] isSelected;
   static int[][] ingredients;
   static int maxScore;
   
   static void swap(int i, int j) {
	   byte temp = isSelected[i];
	   isSelected[i] = isSelected[j];
	   isSelected[j] = temp;
   }
   
   static boolean nextComb() {
	   
	   int i = N-1;
	   while (i>0 && isSelected[i-1] >= isSelected[i]) i--;
	   if (i==0) return false;
	   
	   int j = N-1;
	   while (isSelected[i-1] >= isSelected[j]) j--;
	   swap(i-1, j);
	   
//	   int k = N-1;
//	   swap(i, k);
	   int left = i, right = N - 1;
	    while (left < right) {
	        swap(left, right);
	        left++;
	        right--;
	    }
	   return true;
   }
    
   public static void main(String[] args) throws Exception {

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
        
       int T = Integer.parseInt(br.readLine());
        
       for (int tc = 1; tc <= T; tc++) {
           StringTokenizer st = new StringTokenizer(br.readLine(), " ");
           
           N = Integer.parseInt(st.nextToken());
           L = Integer.parseInt(st.nextToken());
           
           ingredients = new int[N][2];
            
           for (int i = 0; i < N; i++) {
               st = new StringTokenizer(br.readLine(), " ");
               ingredients[i][0] = Integer.parseInt(st.nextToken());
               ingredients[i][1] = Integer.parseInt(st.nextToken());
           }
            
           maxScore = 0;

           isSelected = new byte[N];
           for (int i = 0; i < N; i++) {  	   
        	   isSelected[i] = 1;
        	   
        	   Arrays.sort(isSelected);

        	   do {
//        		   System.out.println(Arrays.toString(isSelected));
        		   int score = 0;
        		   int calories = 0;
        		   for (int j = 0; j < N; j++) {
        			   if (isSelected[j] == 1) {
        				   score += ingredients[j][0];
        				   calories += ingredients[j][1];
        			   }
        		   }
        		   if (calories <= L) maxScore = Math.max(maxScore, score);
        	   } while (nextComb());
           }
            
           sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
       }

       System.out.println(sb.toString());
   }
}