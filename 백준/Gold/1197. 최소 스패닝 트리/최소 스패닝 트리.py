# 프림 알고리즘

import heapq
import sys

def prim(start):
    mst = []
    visited[start] = True
    total_weight = 0

    # 시작 정점에서부터 탐색
    for edge in graph[start]:
        heapq.heappush(pq, edge)  # 시작 정점과 연결된 간선들을 우선순위 큐에 삽입

    while pq:
        weight, u, v = heapq.heappop(pq)  # 가중치가 가장 적은 간선 추출
        if not visited[v]:
            visited[v] = True
            mst.append((u,v))
            total_weight += weight

            # 방문하지 않은 정점과 연결된 간선들을 우선순위 큐에 삽입
            for edge in graph[v]:
                if not visited[edge[2]]:
                    heapq.heappush(pq, edge)

    return total_weight

V, E = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(V + 1)]
visited = [False] * (V + 1)
pq = [] #우선순위 큐

for _ in range(E):
    u, v, weight = map(int, sys.stdin.readline().split())
    graph[u].append([weight, u, v])
    graph[v].append([weight, v, u])

# 시작 정점을 1로 설정하고 최소 신장 트리 알고리즘 실행
print(prim(1))


# # 크루스칼 알고리즘

# import sys

# # 특정 원소가 속한 집합을 찾기
# def find_parent(parent, x):
#     if parent[x] != x:
#         parent[x] = find_parent(parent, parent[x])
#     return parent[x]

# # 두 원소가 속한 집합을 합치기
# def union_parent(parent, a, b):
#     a = find_parent(parent, a)
#     b = find_parent(parent, b)
#     if a < b:
#         parent[b] = a
#     else:
#         parent[a] = b

# # 노드의 개수와 간선(union 연산)의 개수 입력 받기
# V, E = map(int, sys.stdin.readline().split())
# parent = [0] * (V + 1)

# # 부모 테이블상에서, 부모를 자기 자신으로 초기화
# for i in range(1, V + 1):
#     parent[i] = i

# edges = []
# total_weight = 0

# # 간선을 입력받아 coat를 기준으로 오름차순 정렬
# for _ in range(E):
#     u, v, weight = map(int, sys.stdin.readline().split())
#     edges.append((weight, u, v))

# edges.sort()

# # 정렬된 간선을 하나씩 확인
# for edge in edges:
#     weight, u, v = edge
#     # 두 노드의 루트 노드가 서로 다르다면 사이클이 발생하지 않은것이므로  
#     if find_parent(parent, u) != find_parent(parent, v):
#         # 신장 트리에 간선 추가
#         union_parent(parent, u, v)
#         total_weight += weight

# print(total_weight)