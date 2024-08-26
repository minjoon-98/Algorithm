def solution(edges):
    # 모든 노드의 최대 번호를 찾기 위해 최대 노드를 구함
    max_node = max(max(u, v) for u, v in edges)

    # 각 노드의 in_degree, out_degree를 기록할 리스트를 생성
    in_degree = [0] * (max_node + 1)
    out_degree = [0] * (max_node + 1)

    # 각 노드의 간선 정보를 기록
    for u, v in edges:
        out_degree[u] += 1
        in_degree[v] += 1

    generated_node = None
    bar_count = 0
    eight_count = 0

    # 모든 노드를 순회하며 그래프 유형을 판별
    for node in range(1, max_node + 1):
        if out_degree[node] >= 2 and in_degree[node] == 0:
            generated_node = node
        elif out_degree[node] == 0 and in_degree[node] > 0:
            bar_count += 1
        elif out_degree[node] >= 2 and in_degree[node] >= 2:
            eight_count += 1

    # 도넛 모양 그래프의 수는 생성된 노드에서 나가는 간선의 개수에서 
    # bar_count와 eight_count를 뺀 값
    donut_count = out_degree[generated_node] - bar_count - eight_count

    return [generated_node, donut_count, bar_count, eight_count]