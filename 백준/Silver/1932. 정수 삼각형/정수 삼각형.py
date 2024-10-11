import sys
input = sys.stdin.readline

# 입력 받기
N = int(input())
triangle = [list(map(int, input().split())) for _ in range(N)]

# DP 테이블 초기화
dp = [[0] * (i + 1) for i in range(N)]
dp[0][0] = triangle[0][0]  # 맨 위의 값

# DP 배열 업데이트
for i in range(1, N):
    for j in range(i + 1):
        if j == 0:
            dp[i][j] = dp[i - 1][j] + triangle[i][j]  # 첫 번째 요소
        elif j == i:
            dp[i][j] = dp[i - 1][j - 1] + triangle[i][j]  # 마지막 요소
        else:
            dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j]  # 중간 요소

# 마지막 행에서 최대값 찾기
result = max(dp[N - 1])
print(result)