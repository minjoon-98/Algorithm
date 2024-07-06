from collections import deque
import sys
input = sys.stdin.readline

R, C = map(int, input().split())
alphabets = [list(map(str, input().strip())) for _ in range(R)]
visited = [False]*26

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def backtracking(x, y):
    global answer
    if x == R and y == C:
        answer = max(answer, visited.count(True))
        return
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0<=nx<R and 0<=ny<C and not visited[ord(alphabets[nx][ny])-65]:
            visited[ord(alphabets[nx][ny])-65] = True
            backtracking(nx, ny)
            visited[ord(alphabets[nx][ny])-65] = False
    answer = max(answer, visited.count(True))
    return answer

answer = 0
visited[ord(alphabets[0][0])-65] = True
backtracking(0, 0)
print(answer)