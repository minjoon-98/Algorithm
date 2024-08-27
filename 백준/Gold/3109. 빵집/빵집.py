import sys
input = sys.stdin.readline

def dfs(r):
    stack = [(r, 0)]
    while stack:
        x, y = stack.pop()
        visited[x][y] = True
        if y == C - 1:
            return True
        if 0 <= x+1 < R and 0 <= y+1 < C and area[x+1][y+1] != 'x' and not visited[x+1][y+1]:
            stack.append((x+1, y+1))
        if 0 <= x < R and 0 <= y+1 < C and area[x][y+1] != 'x' and not visited[x][y+1]:
            stack.append((x, y+1))
        if 0 <= x-1 < R and 0 <= y+1 < C and area[x-1][y+1] != 'x' and not visited[x-1][y+1]:
            stack.append((x-1, y+1))
    return False

R, C = map(int, input().split())
area = [list(input().strip()) for _ in range(R)]
visited = [[False] * C for _ in range(R)]

count = 0
for i in range(R):
    if dfs(i):
        count += 1
print(count)