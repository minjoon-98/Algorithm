import sys

K, N = map(int, sys.stdin.readline().split())
cables = []

for _ in range(K):
    cables.append(int(sys.stdin.readline()))

start = 1
end = (sum(cables) // N) 

while start <= end:
    count = 0

    mid = (start + end) // 2
    for i in range(K):
        count += cables[i] // mid
    if N <= count:
        start = mid + 1
    else:
        end = mid - 1

print(end)