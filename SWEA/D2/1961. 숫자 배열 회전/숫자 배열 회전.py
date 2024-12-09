def rotate_90(matrix, N):
    # 90도 시계방향 회전
    rotated = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            rotated[j][N - i - 1] = matrix[i][j]
    return rotated

T = int(input())  # 테스트 케이스 수
for t in range(1, T + 1):
    N = int(input())  # 행렬의 크기 N
    matrix = [list(map(int, input().split())) for _ in range(N)]  # 행렬 입력
    
    # 90도, 180도, 270도 회전된 행렬을 구한다.
    rot_90 = rotate_90(matrix, N)
    rot_180 = rotate_90(rot_90, N)
    rot_270 = rotate_90(rot_180, N)
    
    # 결과 출력
    print(f"#{t}")
    for i in range(N):
        # 각 회전된 행렬을 공백을 기준으로 한 줄로 출력
        print(''.join(map(str, rot_90[i])), ''.join(map(str, rot_180[i])), ''.join(map(str, rot_270[i])))