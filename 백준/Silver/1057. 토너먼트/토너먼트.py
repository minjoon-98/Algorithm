import math
import sys
input = sys.stdin.readline

N, a, b = map(int, input().split())

while N > 1:
    round = math.ceil(math.log2(N))
    N = pow(2, round)
    n = N // 2

    if a <= n and b <= n:
        N = n
    elif a > n and b > n:
        N -= n
        a -= n
        b -= n
    else:
        break
print(round)