import sys
input = sys.stdin.readline

N, M = map(int, input().split())
r, c, d = map(int, input().split())
room = [list(map(int, input().split())) for _ in range(N)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
count = 0

def robot_cleaner(r, c, d):
    global count
    if room[r][c] == 0:
        room[r][c] = 2
        count += 1
    for i in range(1, 5):
        nx, ny = r + dx[(d-i)%4], c + dy[(d-i)%4]
        if 0 < nx < N-1 and 0 < ny < M-1 and room[nx][ny] == 0:
            robot_cleaner(nx, ny, (d-i)%4)
    bx, by = r - dx[d], c - dy[d]
    if 0 < bx < N-1 and 0 < by < M-1 and room[bx][by] != 1:
        robot_cleaner(bx, by, d)
    else:
        print(count)
        exit()

robot_cleaner(r, c, d)