from collections import deque

def bfs(r, c, board, directions):    
    q = deque()
    q.append((r, c))
    count = 1  # 처음 지뢰가 있는 칸을 세면서 시작
    board[r][c] = -1  # 시작 위치를 이미 방문한 지뢰로 마킹
    while q:
        x, y = q.popleft()
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < len(board) and 0 <= ny < len(board):
                if board[nx][ny] == 0:  # 안전한 칸이 있으면
                    board[nx][ny] = -2  # 위험지역 마킹
                    count += 1
                if board[nx][ny] == 1:  # 새로운 지뢰를 발견하면
                    board[nx][ny] = -1  # 방문한 지뢰로 마킹
                    q.append((nx, ny))  # 폭탄 주변으로 BFS 탐색 이어나감
                    count += 1
    return count

def solution(board):
    n = len(board)
    answer = n * n   # 전체 칸 수로 초기화
    directions = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] == 1:    # 지뢰를 발견하면
                answer -= bfs(i, j, board, directions)  # BFS로 지뢰와 주변 위험 지역의 칸 수를 빼줌
    return answer