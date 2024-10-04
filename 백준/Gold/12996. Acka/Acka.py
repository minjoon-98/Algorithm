import sys
input = sys.stdin.readline

s, a, b, c = map(int, input().split())

# DP 배열 초기화: dp[n][a][b][c]는 n개의 곡 중 a, b, c 각각의 가수가 불러야 할 곡 수를 나타냄
dp = [[[[ -1 for _ in range(51)] for _ in range(51)] for _ in range(51)] for _ in range(51)]

def count_ways(n, a, b, c):
    # 곡 수가 0일 때
    if n == 0:
        # 각 가수가 부를 곡 수가 0이라면 1을 반환 (유효한 경우)
        if a == 0 and b == 0 and c == 0:
            return 1
        else:
            return 0  # 그렇지 않으면 0 반환 (유효하지 않은 경우)
    
    # 가수의 곡 수가 음수일 경우 0 반환 (유효하지 않은 경우)
    if a < 0 or b < 0 or c < 0:
        return 0
    
    # 이미 계산된 경우라면 그 값을 반환
    if dp[n][a][b][c] != -1:
        return dp[n][a][b][c]
    
    dp[n][a][b][c] = 0  # 현재 경우의 수 초기화
    
    # 각 가수의 곡 참여 여부를 결정 (0 또는 1)
    for i in range(2):  # dotorya
        for j in range(2):  # kesakiyo
            for k in range(2):  # hongjun7
                # 최소 한 명의 가수가 노래를 불러야 함
                if i + j + k > 0:  
                    # 현재 곡을 부르기 위해 재귀적으로 호출
                    dp[n][a][b][c] += count_ways(n - 1, a - i, b - j, c - k)

    dp[n][a][b][c] %= 1000000007  # 큰 수의 경우를 처리하기 위해 모듈로 연산
    return dp[n][a][b][c]  # 계산된 경우의 수 반환

# 최종 결과 출력
print(count_ways(s, a, b, c))