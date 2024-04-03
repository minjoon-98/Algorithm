import heapq
import sys

inf = float('inf')

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[] for _ in range(n+1)]
distance = [inf] * (n+1)

for _ in range(m):
    a, b, cost = map(int, sys.stdin.readline().split())
    graph[a].append((b,cost))

start, finish = map(int, sys.stdin.readline().split())

def dijkstra(start):
    pq = []
    distance[start] = 0
    heapq.heappush(pq, (0, start))    # 가중치 순으로 정렬해야하기 떄문에 가중치를 앞에 오도록 작성
    while pq:
        dist, curr = heapq.heappop(pq)
        if distance[curr] < dist : continue # 현재 노드까지의 저장된 거리가 우선순위 큐에서 꺼낸 거리보다 작다면 이미 작은 값을 저장하고 있기 때문에, 값을 갱신하지 않고 넘김
        for next_node, next_cost in graph[curr]:
            cost = dist + next_cost
            if cost < distance[next_node]:
                distance[next_node] = cost
                heapq.heappush(pq, (cost, next_node))
    return distance[finish]

print(dijkstra(start))