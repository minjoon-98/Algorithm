def solution(targets):
    answer = 0
    targets.sort(key=lambda x:x[1])
    current_coverage = 0
    for s, e in targets:
        if s >= current_coverage: # = 도 포함되어야함, 끝 점과 시작점이 같으면 범위는 공유하지 않기 때문
            answer += 1
            current_coverage = e
    return answer