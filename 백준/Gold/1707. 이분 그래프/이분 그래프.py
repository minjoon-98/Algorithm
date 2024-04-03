from collections import deque
import sys

def bfs(start, color):
    color[start] = 1
    que = deque([start])
    while que:
        v = que.popleft()
        for i in graph[v]:
            if color[i] == 0:
                que.append(i)
                color[i] = color[v] * -1
            elif color[i] == color[v]:
                return False
    return True

K = int(sys.stdin.readline())

for _ in range(K):
    V, E = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(V+1)]
    color = [0] * (V+1)

    for _ in range(E):
        u, v = map(int, sys.stdin.readline().split())
        graph[u].append(v)
        graph[v].append(u)
    
    is_correct = True

    for i in range(1, V+1):
        if color[i] != 0: continue
        if not bfs(i, color):
            is_correct = False
            break

    if is_correct == True:
        print('YES')
    else:
        print('NO')       