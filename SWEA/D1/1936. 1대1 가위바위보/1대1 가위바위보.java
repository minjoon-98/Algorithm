import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        // 표준입력으로 스캐너를 만들어 데이터를 읽어옵니다.
        Scanner sc = new Scanner(System.in);
        
        // A와 B의 선택을 입력받습니다.
        int A = sc.nextInt();
        int B = sc.nextInt();
        
        // 승자를 판별하는 조건문
        if (A == 1 && B == 3 || A == 2 && B == 1 || A == 3 && B == 2) {
            System.out.println("A");
        } else {
            System.out.println("B");
        }
    }
}
