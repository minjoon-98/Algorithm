import sys

N, K = map(int, sys.stdin.readline().split())

A = [None]
for _ in range(N):
    A.append(int(sys.stdin.readline()))
count = 0
for i in range(N, 0, -1):
    while K:
        if K >= A[i]:
            count += K // A[i]
            K = K % A[i]
        else:
            break
print(count)