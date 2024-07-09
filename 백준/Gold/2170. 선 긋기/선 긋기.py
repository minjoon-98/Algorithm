import sys
input = sys.stdin.readline

N = int(input())
line = list(tuple(sorted(map(int, input().split()))) for _ in range(N))
line.sort()

length = line[0][1]-line[0][0]
maxNum = line[0][1]

for i in range(1, N):
    if line[i][0] <= maxNum:
        if maxNum < line[i][1]:
            length += line[i][1]-maxNum
            maxNum = line[i][1]
    else:
        length += line[i][1]-line[i][0]
        maxNum = line[i][1]
print(length)
