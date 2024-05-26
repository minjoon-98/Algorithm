import sys
input = sys.stdin.readline

N, K = map(int, input().split())
sequence = list(map(int, input().split()))
count = [0] * (max(sequence) + 1)

start, end = 0, 0
answer = 0
while end < N:
    if count[sequence[end]] < K:
        count[sequence[end]] += 1
        end += 1
    else:
        count[sequence[start]] -= 1
        start += 1
    answer = max(answer, end - start)
print(answer)    