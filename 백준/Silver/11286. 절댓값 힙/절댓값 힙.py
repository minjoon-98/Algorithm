import heapq
import sys
input = sys.stdin.readline

N = int(input())

heap = []
for _ in range(N):
    x = int(input())

    if x == 0:
        if heap:
            min_abs, sign = heapq.heappop(heap)
            print(min_abs*sign)
        else:
            print(0)
    else:
        heapq.heappush(heap, (abs(x), 1 if x >= 0 else -1))