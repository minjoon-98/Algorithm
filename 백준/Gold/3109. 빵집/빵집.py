import sys
input = sys.stdin.readline

def dfs(r):
    stack = [(r, 0)]
    while stack:
        x, y = stack.pop()
        visited[x][y] = True
        if y == C - 1:
            return True
        # 세 갈래 길을 stack에 넣고 DFS로 탐색 (맨 윗길이 존재한다면 아래 길은 탐색하지 않고 끝남 -> 겹칠 수 없기 때문에 가능)
        if 0 <= x+1 < R and 0 <= y+1 < C and area[x+1][y+1] != 'x' and not visited[x+1][y+1]:
            stack.append((x+1, y+1))    # 맨 아랫길
        if 0 <= x < R and 0 <= y+1 < C and area[x][y+1] != 'x' and not visited[x][y+1]:
            stack.append((x, y+1))  # 중간길
        if 0 <= x-1 < R and 0 <= y+1 < C and area[x-1][y+1] != 'x' and not visited[x-1][y+1]:
            stack.append((x-1, y+1))    # 맨 윗길
    return False    # 다 탐색했는데도 길이 없을 경우

R, C = map(int, input().split())
area = [list(input().strip()) for _ in range(R)]
visited = [[False] * C for _ in range(R)]

count = 0
for i in range(R):  # 각 행마다 마지막 열에 도달할 수 있는지 확인
    if dfs(i):
        count += 1
print(count)