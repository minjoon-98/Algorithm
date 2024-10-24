import sys
input = sys.stdin.readline

N, K = map(int, input().split())
levels = [int(input()) for _ in range(N)]

left = min(levels)
right = max(levels) + K

answer = 0
while left <= right:
    mid = (left + right) // 2
    total_needs = 0
    for level in levels:
        if level < mid:
            total_needs += mid - level
    if total_needs > K:
        right = mid - 1
    else:
        left = mid + 1
        answer = mid

print(answer)