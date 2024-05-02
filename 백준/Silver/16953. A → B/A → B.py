import sys

A, B = map(int, sys.stdin.readline().split())


count = 0
while A < B:
    if B % 2 == 0:
        B = B // 2
        count += 1
    elif B % 10 == 1:
        B = B // 10
        count += 1
    else:
        print(-1)
        exit()
if A == B:
    count += 1
    print(count)
else:
    print(-1)