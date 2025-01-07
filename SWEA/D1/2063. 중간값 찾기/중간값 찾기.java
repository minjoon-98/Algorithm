import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        // 스캐너로 입력 받기
        Scanner sc = new Scanner(System.in);

        // 첫 번째 줄에서 N을 입력받음
        int N = sc.nextInt();

        // 두 번째 줄에서 N개의 점수를 입력받음
        int[] scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = sc.nextInt();
        }

        // 점수 배열을 오름차순으로 정렬
        Arrays.sort(scores);

        // 중간값을 출력 (정렬된 배열의 중앙값)
        System.out.println(scores[N / 2]);
    }
}
