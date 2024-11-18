import sys
input = sys.stdin.readline

def detect_cycle(start_idx):
    path = []
    current_idx = start_idx

    while not visited[current_idx]:
        visited[current_idx] = True
        path.append(current_idx)
        current_idx = students_pick[current_idx]
    
    # cycle이 발생한 위치 찾기
    if current_idx in path:
        cycle_start_idx = path.index(current_idx)
        return set(path[cycle_start_idx:])
    return set()


T = int(input())
for _ in range(T):
    n = int(input())
    students_pick = list(map(int, input().split()))
    students_pick.insert(0, 0)  # 1-based indexing에 맞추기 위해 삽입. (0번째 인덱스는 사용하지 않음)

    visited = [False] * (n+1)
    cycle = [False] * (n+1)
    for student in range(1, n+1):
        if not visited[student]:
            cycle_part = detect_cycle(student)
            if cycle_part:
                for idx in cycle_part:
                    cycle[idx] = True
    
    result = sum(1 for student in range(1, n+1) if not cycle[student])  # 사이클에 포함되지 않은 학생 수
    print(result)
