import sys

N = int(sys.stdin.readline())
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(N)] 

result = [0, 0]
def calc(x, y, N):
    color = paper[x][y]
    for i in range(x, x + N):
        for j in range(y, y + N):
            if color != paper[i][j]:
                calc(x, y, N // 2)
                calc(x + N // 2, y, N // 2)
                calc(x, y + N // 2, N // 2)
                calc(x + N // 2, y + N // 2, N // 2)
                return
    if color == 0:
        result[0] += 1
    else:
        result[1] += 1
calc(0, 0, N)
print(result[0])
print(result[1])