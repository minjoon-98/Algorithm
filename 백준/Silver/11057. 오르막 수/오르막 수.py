import sys
input = sys.stdin.readline

N = int(input())

answer = 1

for i in range(10, 10+N):
    answer *= i
for j in range(1, N+1):
    answer //= j
print(answer%10007)