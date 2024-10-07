def solution(d, budget):
    # 신청 금액을 오름차순으로 정렬
    d.sort()
    
    answer = 0
    total_spent = 0
    
    # 각 부서에 대해
    for cost in d:
        # 예산에서 현재 부서의 신청 금액을 지원할 수 있는지 확인
        if total_spent + cost <= budget:
            total_spent += cost  # 금액을 더하고
            answer += 1  # 지원 가능한 부서 수 증가
        else:
            break  # 더 이상 지원할 수 없으면 종료
    
    return answer