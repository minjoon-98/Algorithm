from collections import deque 

def solution(maps):
    # 문자열을 리스트로 변환
    maps = [list(row) for row in maps]
    rows, cols = len(maps), len(maps[0])
    visited = [[False] * cols for _ in range(rows)]
    
    def bfs(x, y):
        queue = deque([(x, y)])
        total_food = 0
        
        while queue:
            cx, cy = queue.popleft()
            if visited[cx][cy]:
                continue
            
            visited[cx][cy] = True
            total_food += int(maps[cx][cy])
            
            # 상하좌우 탐색
            for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                nx, ny = cx + dx, cy + dy
                if 0 <= nx < rows and 0 <= ny < cols and not visited[nx][ny] and maps[nx][ny] != 'X':
                    queue.append((nx, ny))
        
        return total_food

    answer = []
    
    # 모든 격자를 탐색
    for i in range(rows):
        for j in range(cols):
            if maps[i][j] != 'X' and not visited[i][j]:  # 섬을 찾았을 때
                island_food = bfs(i, j)  # 섬의 식량 합산
                answer.append(island_food)
                
#     def dfs(x, y):
#         # 현재 위치가 범위를 벗어나거나 바다(X)인 경우
#         if x < 0 or x >= len(maps) or y < 0 or y >= len(maps[0]) or maps[x][y] == 'X':
#             return 0
        
#         # 현재 위치의 식량을 얻고, 'X'로 표시하여 방문 처리
#         food = int(maps[x][y])
#         maps[x][y] = 'X'  # 방문 처리
        
#         # 상하좌우로 재귀 호출하여 연결된 섬의 식량 합산
#         food += dfs(x + 1, y)
#         food += dfs(x - 1, y)
#         food += dfs(x, y + 1)
#         food += dfs(x, y - 1)
        
#         return food

#     answer = []
    
#     # 모든 격자를 탐색
#     for i in range(len(maps)):
#         for j in range(len(maps[0])):
#             if maps[i][j] != 'X':  # 섬을 찾았을 때
#                 island_food = dfs(i, j)  # 섬의 식량 합산
#                 answer.append(island_food)

    # 결과 정렬
    if answer:
        answer.sort()
    else:
        return [-1]

    return answer