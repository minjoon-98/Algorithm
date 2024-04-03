import heapq
import sys

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

INF = float('inf')

graph = [[] for _ in range(N+1)]
distance = [INF] * (N+1)

for _ in range(M):
    a, b, cost = map(int, sys.stdin.readline().split())
    graph[a].append([b, cost])

start, finish = map(int, sys.stdin.readline().split())

def dijkstra(start):
    priority_queue = []
    heapq.heappush(priority_queue, (0, start))
    distance[start] = 0
    while priority_queue:
        dist, curr = heapq.heappop(priority_queue)
        if distance[curr] < dist: continue
        for i in graph[curr]:
            cost = dist + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(priority_queue, (cost, i[0]))
    return distance[finish]

print(dijkstra(start))