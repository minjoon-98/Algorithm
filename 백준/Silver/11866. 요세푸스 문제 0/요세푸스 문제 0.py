import sys

N, K = map(int, sys.stdin.readline().split())

pm = [i for i in range(1, N+1)]
Josephus = []

index = 0 

for _ in range(N):
    index = (index + K - 1) % len(pm)
    Josephus.append(pm.pop(index))

list_str = ', '.join(map(str, Josephus))    
print(f'<{list_str}>')
