import sys

def mul(A, B, C):
    if B == 1:
        return A % C
    if B % 2 == 0:
        return (pow(mul(A, B//2, C), 2)) % C
    else:
        return (pow(mul(A, B//2, C), 2) * A) % C

A, B, C = map(int, sys.stdin.readline().split())
print(mul(A, B, C))