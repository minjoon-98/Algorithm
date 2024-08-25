def solution(land):
    n, m = len(land), len(land[0])
    visited = [[False] * m for _ in range(n)]
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def dfs(x, y):
        stack = [(x, y)]
        visited[x][y] = True
        size = 0
        cols = set()
        while stack:
            cx, cy = stack.pop()
            size += 1
            cols.add(cy) # DFS로 돌면서 연결되어있는 열을 전부 기록
            for dx, dy in directions:
                nx, ny = cx + dx, cy + dy
                if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and land[nx][ny] == 1:
                    visited[nx][ny] = True
                    stack.append((nx, ny))
        return size, cols

    column_sums = [0] * m

    for i in range(n):
        for j in range(m):
            if not visited[i][j] and land[i][j] == 1:
                size, cols = dfs(i, j)
                for col in cols:
                    column_sums[col] += size # 해당 열에서 시추했을 경우 얻을 수 있는 석유의 양을 기록

    return max(column_sums)