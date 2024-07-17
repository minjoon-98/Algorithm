import sys
input = sys.stdin.readline

N, L = map(int, input().split())
leak = sorted(list(map(int, input().split())))

put, count = 0, 0
for i in range(N):
    if put == 0:
        put = leak[i]
        count += 1
    else:
        if leak[i]-put+1 > L:
            put = leak[i]
            count += 1

print(count)