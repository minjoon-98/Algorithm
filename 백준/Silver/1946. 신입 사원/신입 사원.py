import sys

T = int(sys.stdin.readline())
for _ in range(T):
    N = int(sys.stdin.readline())
    test = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    test.sort()
    hired = 1
    top = 0
    for i in range(1, N):
        if test[top][1] > test[i][1]:
            hired += 1
            top = i
    print(hired)