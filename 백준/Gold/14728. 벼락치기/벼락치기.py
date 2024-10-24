import sys
input = sys.stdin.readline

N, T = map(int, input().split())

time_score = [[]]
for i in range(N):
    time_score.append(tuple(map(int, input().split())))

# time_score.sort(key=lambda x : (-x[1], x[0]))
# time_score.sort(key=lambda x : (-(x[1]/x[0])))
knapsack = [[0] * (T+1) for _ in range(N+1)]

for i in range(1, N+1):
    for j in range(1, T+1):
        time, score = time_score[i]
        if j < time:
            knapsack[i][j] = knapsack[i-1][j]
        else:
            knapsack[i][j] = max(knapsack[i-1][j], knapsack[i-1][j-time] + score)
        
print(knapsack[N][T])