def calculate_flies_plus(matrix, N, M, i, j):
    flies = matrix[i][j]  # 중심
    # 상
    for k in range(1, M):
        if i - k >= 0:
            flies += matrix[i - k][j]
    # 하
    for k in range(1, M):
        if i + k < N:
            flies += matrix[i + k][j]
    # 좌
    for k in range(1, M):
        if j - k >= 0:
            flies += matrix[i][j - k]
    # 우
    for k in range(1, M):
        if j + k < N:
            flies += matrix[i][j + k]
    return flies

def calculate_flies_x(matrix, N, M, i, j):
    flies = matrix[i][j]  # 중심
    # 대각선: 왼쪽 위
    for k in range(1, M):
        if i - k >= 0 and j - k >= 0:
            flies += matrix[i - k][j - k]
    # 대각선: 오른쪽 아래
    for k in range(1, M):
        if i + k < N and j + k < N:
            flies += matrix[i + k][j + k]
    # 대각선: 왼쪽 아래
    for k in range(1, M):
        if i + k < N and j - k >= 0:
            flies += matrix[i + k][j - k]
    # 대각선: 오른쪽 위
    for k in range(1, M):
        if i - k >= 0 and j + k < N:
            flies += matrix[i - k][j + k]
    return flies


T = int(input())  # 테스트 케이스 수
for test_case in range(1, T + 1):
    N, M = map(int, input().split())  # N과 M 입력 받기
    matrix = [list(map(int, input().split())) for _ in range(N)]  # 행렬 입력 받기

    max_flies = 0  # 최대 파리 수

    # +형태와 x형태의 스프레이를 적용하여 최댓값 구하기
    for i in range(N):
        for j in range(N):
            max_flies = max(max_flies, calculate_flies_plus(matrix, N, M, i, j))
            max_flies = max(max_flies, calculate_flies_x(matrix, N, M, i, j))
    
    print(f"#{test_case} {max_flies}")
