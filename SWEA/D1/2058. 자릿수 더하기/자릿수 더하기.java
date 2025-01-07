import java.util.Scanner;
import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        
        // 자연수 N 입력 받기
        int N = sc.nextInt();
        
        // 각 자릿수의 합을 구하기 위한 변수
        int sum = 0;
        
        // 자릿수를 하나씩 분리하여 더하기
        while (N > 0) {
            sum += N % 10;  // N의 마지막 자릿수를 sum에 더함
            N /= 10;         // N을 10으로 나누어 마지막 자리를 제거
        }
        
        // 결과 출력
        System.out.println(sum);
    }
}
