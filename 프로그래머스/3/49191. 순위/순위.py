from collections import deque

def solution(n, results):
    # 그래프 및 차수 초기화
    graph = [[] for _ in range(n + 1)]  # 순방향 그래프
    reverse_graph = [[] for _ in range(n + 1)]  # 역방향 그래프
    indegree = [0] * (n + 1)    # 각 선수의 진입 차수
    outdegree = [0] * (n + 1)   # 각 선수의 진출 차수
    
    # 경기 결과로 그래프 및 차수 업데이트
    for A, B in results:
        graph[A].append(B)
        reverse_graph[B].append(A)
        indegree[B] += 1
        outdegree[A] += 1

    # 선수 순서가 확정된 선수의 수를 세기 위한 변수 (정답)
    definite_count = 0

    # 각 선수 i에 대해
    for i in range(1, n + 1):
        # 선수 i가 이길 수 있는 모든 선수 찾기 (선수 i에서 순방향 BFS 수행)
        queue = deque([i])
        visited = [False] * (n + 1)
        visited[i] = True
        reachable_from_i = -1 # 자기 자신을 제외하고 계산해야하니깐 -1로 초기화

        while queue:
            node = queue.popleft()
            reachable_from_i += 1
            for neighbor in graph[node]:
                if not visited[neighbor]:
                    visited[neighbor] = True
                    queue.append(neighbor)
        
        # 선수 i를 이길 수 있는 모든 선수 찾기 (선수 i에서 역방향 BFS 수행)
        queue = deque([i])
        visited = [False] * (n + 1)
        visited[i] = True
        reachable_to_i = -1 # 자기 자신을 제외하고 계산해야하니깐 -1로 초기화
        
        while queue:
            node = queue.popleft()
            reachable_to_i += 1
            for prev in reverse_graph[node]:
                if not visited[prev]:
                    visited[prev] = True
                    queue.append(prev)
        
        # i가 이길 수 있는 선수의 수와 i를 이길 수 있는 선수의 수를 합쳐서 전체 선수 수에서 자신을 뺀 n-1과 같으면 순서가 확정됨
        if reachable_from_i + reachable_to_i == n - 1:
            definite_count += 1
    
    return definite_count