import sys
input = sys.stdin.readline

N, M = map(int, input().split())
arr = [int(input()) for _ in range(N)]
arr.sort()

answer = float('inf')
start = 0
end = start + 1

while start < N and end < N:
    diff = arr[end] - arr[start]
    if diff < M:
        end += 1
    elif diff > M:
        answer = min(answer, diff)
        start += 1
    else:
        answer = M
        break

print(answer)