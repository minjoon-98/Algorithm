def solution(n, times):
    
    low = min(times)
    high = max(times) * n
    
    while low <= high:
        mid = (low + high) // 2
        passed_people = 0   # mid 시간 동안 심사관들이 처리할 수 있는 총 인원 수
        
        for time in times:
            passed_people += mid // time    # 각 time에 대해, mid 시간 동안 심사관이 처리할 수 있는 인원의 수
        
        if passed_people >= n:
            high = mid - 1  # 충분히 심사할 수 있으면, 최소 시간을 찾기 위해 high를 감소
            answer = mid
        else:
            low = mid + 1   # 심사할 수 없으면, 시간을 늘리기 위해 low를 증가
            
    return answer