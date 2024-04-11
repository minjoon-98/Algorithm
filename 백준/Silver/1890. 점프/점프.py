import sys

N = int(sys.stdin.readline())
map = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[0]*N for _ in range(N)]
dp[0][0] = 1

for i in range(N):
    for j in range(N):
        for k in range(1, i+1):
            if k == map[i-k][j] and dp[i-k][j] != 0: # 위로 k만큼 떨어져있는 곳의 map의 값이 K와 같다면 올 수 있다는 뜻이니 +1
                dp[i][j] += dp[i-k][j]
        for k in range(1, j+1):
            if k == map[i][j-k] and dp[i][j-k] != 0:  # 왼쪽으로 k만큼 떨어져있는 곳의 map의 값이 K와 같다면 올 수 있다는 뜻이니 +1
                dp[i][j] += dp[i][j-k]
print(dp[N-1][N-1])