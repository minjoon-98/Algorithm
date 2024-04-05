import sys

N = int(sys.stdin.readline())
T = [None, 1, 2]
def tile(n):
    for i in range(3, n+1):
        T.append((T[i-1] + T[i-2])%15746)
    return T[n]
print(tile(N))