import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def dfs(x, y):
    # 이미 계산된 값이 있으면 반환
    if dp[x][y] != -1:
        return dp[x][y]
    
    # 현재 노드가 방문 중임을 표시
    if in_stack[x][y]:
        return -1  # 사이클 발견 시 -1 반환
    
    in_stack[x][y] = True  # 현재 노드를 스택에 추가
    step = int(board[x][y])  # 현재 위치에서 이동할 단계 수
    max_moves = 0  # 최대 이동 횟수 초기화
    
    # 상하좌우 이동 탐색
    for dx, dy in [(step, 0), (-step, 0), (0, step), (0, -step)]:
        nx, ny = x + dx, y + dy
        
        # 이동한 위치가 보드 내에 있는지, 구멍('H')이 아닌지 확인
        if 0 <= nx < N and 0 <= ny < M and board[nx][ny] != 'H':
            moves = dfs(nx, ny)  # DFS를 재귀적으로 호출하여 이동 횟수 계산
            if moves == -1:
                return -1  # 사이클이 발견된 경우, -1 반환
            max_moves = max(max_moves, moves)
    
    in_stack[x][y] = False  # 현재 노드를 스택에서 제거
    dp[x][y] = max_moves + 1  # 현재 위치에서 가능한 최대 이동 횟수를 저장
    return dp[x][y]

N, M = map(int, input().split())
board = [list(input().strip()) for _ in range(N)]

dp = [[-1] * M for _ in range(N)]
in_stack = [[False] * M for _ in range(N)]

result = dfs(0, 0)
print(result)