from collections import deque

def bfs(start, passage, visited):
    queue = deque()
    queue.append(start)
    visited[start[0]][start[1]] = True
    size = 0
    
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    while queue:
        x, y = queue.popleft()
        size += 1
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < len(passage) and 0 <= ny < len(passage[0]) and not visited[nx][ny] and passage[nx][ny] == 1:
                visited[nx][ny] = True
                queue.append((nx, ny))
    
    return size

N, M, K = map(int, input().split())
food_positions = [tuple(map(int, input().split())) for _ in range(K)]


passage = [[0] * M  for _ in range(N)]
    
for x, y in food_positions:
    passage[x-1][y-1] = 1
 
visited = [[False] * M for _ in range(N)]
max_size = 0
  
for i in range(N):
    for j in range(M):
        if passage[i][j] == 1 and not visited[i][j]:
            area_size = bfs((i, j), passage, visited)
            max_size = max(max_size, area_size)

print(max_size)