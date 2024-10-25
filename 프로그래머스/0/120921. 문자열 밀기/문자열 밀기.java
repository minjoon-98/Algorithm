class Solution {
    public int solution(String A, String B) {
        // A와 B의 길이가 다르면 -1 반환
        if (A.length() != B.length()) {
            return -1;
        }

        // A와 B가 같다면 밀지 않아도 되므로 0 반환
        if (A.equals(B)) {
            return 0;
        }

        int n = A.length();

        // 최대 n-1번 밀 수 있음
        for (int i = 1; i < n; i++) {
            // 오른쪽으로 밀기 (슬라이스 사용)
            A = A.charAt(n - 1) + A.substring(0, n - 1);

            // 밀린 문자열과 B 비교
            if (A.equals(B)) {
                return i; // 몇 번 밀었는지 반환
            }
        }

        return -1; // 밀어서 B가 될 수 없는 경우
    }
}

// // StringBuilder 클래스 사용
// class Solution {
//     public int solution(String A, String B) {
//         // A와 B의 길이가 같지 않으면 -1 반환
//         if (A.length() != B.length()) {
//             return -1;
//         }

//         // A와 B가 같다면 밀지 않아도 되므로 0 반환
//         if (A.equals(B)) {
//             return 0;
//         }

//         int n = A.length();
//         StringBuilder rotatedA = new StringBuilder(A);

//         // 최대 n-1번 밀 수 있음
//         for (int i = 1; i < n; i++) {
//             // 마지막 문자를 앞으로 이동
//             char lastChar = rotatedA.charAt(n - 1);
//             rotatedA.deleteCharAt(n - 1);
//             rotatedA.insert(0, lastChar);

//             // 밀린 문자열과 B 비교
//             if (rotatedA.toString().equals(B)) {
//                 return i; // 몇 번 밀었는지 반환
//             }
//         }

//         return -1; // 밀어서 B가 될 수 없는 경우
//     }
// }