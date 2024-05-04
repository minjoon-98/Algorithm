import heapq
import sys
input = sys.stdin.readline

N = int(input())
arr = []
for _ in range(N):
    heapq.heappush(arr, int(input()))

summ = []

while len(arr) > 1:
    temp = heapq.heappop(arr) + heapq.heappop(arr)
    heapq.heappush(arr, temp)
    summ.append(temp)

print(sum(summ))