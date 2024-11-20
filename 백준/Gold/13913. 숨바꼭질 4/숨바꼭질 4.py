from collections import deque
import sys
input = sys.stdin.readline

N, K = map(int, input().split())

def bfs(start, end):
    visited = [-1] * 100001
    visited[start] = start
    
    q = deque([start])
    
    while q:
        v = q.popleft()
        
        if v == end:
            break
        
        for next_pos in (v-1, v+1, 2*v):
            if 0 <= next_pos <= 100000 and visited[next_pos] == -1:
                visited[next_pos] = v   # visited로 이전의 위치를 기억
                q.append(next_pos)
    
    path = []
    current = end
    while current != start:
        path.append(current)
        current = visited[current]  # visited를 통해 역으로 추적
    path.append(start)
    path.reverse()
    
    return len(path) - 1, path

time, path = bfs(N, K)

print(time)
print(*path)

# 시간이 지나며 다음 위치를 찾기 때문에 이미 왔었던 위치이면 전에 먼저 온 경우가 있으니, 또 볼 필요 없음
# 따라서 visited 필요


# # 메모리 초과
# # 모든 경우의 수의 path를 다 만들어서...
# from collections import deque
# import sys
# input = sys.stdin.readline

# N, K = map(int, input().split())

# def bfs(start):
#     q = deque()
#     q.append((start, 0, [start]))
    
#     while q:
#         v, t, path = q.popleft()
#         if v == K:
#             return (t, path)
#         q.append((v-1, t+1, path + [v-1]))
#         q.append((v+1, t+1, path + [v+1]))
#         q.append((2*v, t+1, path + [2*v]))

# time, path = bfs(N)

# print(time)
# print(*path)