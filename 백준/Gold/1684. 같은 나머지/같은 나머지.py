import math
import sys
input = sys.stdin.readline

N = int(input())

sequence = list(map(int, input().split()))
min = min(sequence)
sub = []

for i in range(N):
    if sequence[i]!=min:
        sub.append(sequence[i]-min)
gcd = sub[0]
for i in range(1, len(sub)):
    gcd = math.gcd(gcd, sub[i])

print(gcd)