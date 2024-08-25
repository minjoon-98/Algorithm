def solution(tickets):
    answer = []
    graph = {}
    
    for start, end in sorted(tickets):
        if start not in graph: # 새로운 출발지가 그래프에 없을 경우, 먼저 빈 리스트를 초기화
            graph[start] = []
        graph[start].append(end)
    
    def dfs(airport):
        while airport in graph and graph[airport]:
            next_airport = graph[airport].pop(0) # 알파벳 순서대로 가장 앞에 있는 목적지를 탐색하기 위해 pop(0)
            dfs(next_airport)
        answer.append(airport)
    
    dfs("ICN")
    return answer[::-1]  # DFS 결과가 역순으로 쌓이기 때문에 경로를 뒤집어서 반환