def solution(people, limit):
    # 몸무게를 정렬
    people.sort()
    
    # 포인터와 답변 변수를 초기화
    left = 0  # 가장 가벼운 사람의 포인터
    right = len(people) - 1  # 가장 무거운 사람의 포인터
    answer = 0
    
    # 투 포인터
    while left <= right:
        if people[left] + people[right] <= limit:
            # 두 사람이 함께 보트를 탈 수 있습니다
            left += 1  # 가벼운 사람 포인터 이동
        # 무거운 사람은 반드시 보트를 타야 합니다
        right -= 1  # 무거운 사람 포인터 이동
        # 보트 하나 사용 카운트
        answer += 1
        
    return answer