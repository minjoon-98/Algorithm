import sys

# 입력 받기
N, K = map(int, input().split())
stuff = [tuple(map(int, input().split())) for _ in range(N)]

# 1차원 배열로 초기화
knapsack = [0] * (K + 1)

# 배낭 문제 해결
for weight, value in stuff:
    for j in range(K, weight - 1, -1):
        knapsack[j] = max(knapsack[j], knapsack[j - weight] + value)

# 결과 출력
print(knapsack[K])