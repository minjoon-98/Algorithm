import sys

N, M = map(int, sys.stdin.readline().split())
Trees = list(map(int, sys.stdin.readline().split()))

start = 0
end = max(Trees)
result = 0

while start <= end:
    mid = (start + end) // 2
    total = 0
    for tree in Trees:
        if tree > mid:
            total += (tree - mid)

    if total >= M:
        result = mid
        start = mid + 1
    else:
        end = mid - 1

print(result)