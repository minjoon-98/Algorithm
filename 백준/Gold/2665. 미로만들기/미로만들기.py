from collections import deque
import sys

n = int(sys.stdin.readline())
inf = float('inf')

graph = []
for _ in range(n):
    graph.append(list(map(int, sys.stdin.readline().rstrip())))
count = [[inf] * n for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

queue = deque([(0,0)])
count[0][0] = 0

while queue:
    (x, y) = queue.popleft()
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < n and 0 <= ny < n:
            if graph[nx][ny] == 0:  # 이동 가능한 공간인 경우
                if count[nx][ny] > count[x][y] + 1:  # 현재 위치를 통해 이동하는 것이 더 효율적인 경우
                    count[nx][ny] = count[x][y] + 1
                    queue.append((nx, ny))
            else:  # 벽인 경우
                if count[nx][ny] > count[x][y]:  # 벽은 통과할 수 없으므로, 현재 위치까지의 이동 횟수가 더 작을 경우에만 갱신
                    count[nx][ny] = count[x][y]
                    queue.append((nx, ny))

print(count[n-1][n-1])