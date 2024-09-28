def solution(rows, columns, queries):
    # 초기 행렬 생성
    grid = [[(i * columns + j + 1) for j in range(columns)] for i in range(rows)]
    
    answer = []
    
    for x1, y1, x2, y2 in queries:
        # 0-indexing으로 조정
        x1 -= 1
        y1 -= 1
        x2 -= 1
        y2 -= 1
        
        # 테두리 숫자 저장
        border = []
        
        # 상단 행
        for j in range(y1, y2 + 1):
            border.append(grid[x1][j])
        # 우측 열
        for i in range(x1 + 1, x2 + 1):
            border.append(grid[i][y2])
        # 하단 행
        for j in range(y2 - 1, y1 - 1, -1):
            border.append(grid[x2][j])
        # 좌측 열
        for i in range(x2 - 1, x1, -1):
            border.append(grid[i][y1])
        
        # 테두리 숫자를 시계방향으로 회전
        rotated = [border[-1]] + border[:-1]    # 마지막 숫자(border[-1])가 가장 앞에 오고 나머지 숫자들(border[:-1])이 뒤따르는 새로운 리스트 -> 시계 방향으로 회전된 테두리
        
        # 회전된 값으로 행렬 업데이트
        idx = 0
        
        # 상단 행
        for j in range(y1, y2 + 1):
            grid[x1][j] = rotated[idx]
            idx += 1
        # 우측 열
        for i in range(x1 + 1, x2 + 1):
            grid[i][y2] = rotated[idx]
            idx += 1
        # 하단 행
        for j in range(y2 - 1, y1 - 1, -1):
            grid[x2][j] = rotated[idx]
            idx += 1
        # 좌측 열
        for i in range(x2 - 1, x1, -1):
            grid[i][y1] = rotated[idx]
            idx += 1
        
        # 회전된 테두리 중 최솟값 추출
        answer.append(min(rotated))
    
    return answer